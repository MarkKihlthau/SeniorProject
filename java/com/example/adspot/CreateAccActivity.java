package com.example.adspot;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.concurrent.ExecutionException;

public class CreateAccActivity extends AppCompatActivity {
    private static MobileServiceClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        AzureService.initCon(this);
        mClient = AzureService.getClient();

        TextView welcomeText = findViewById(R.id.textView3);
        welcomeText.setText("Please enter your info to create a user account.");

    }

    public void newUserAcc(View view)
    {
        String username;
        String password1;
        String password2;
        Switch consumer = (Switch) findViewById(R.id.switch1);
        Switch provider = (Switch) findViewById(R.id.switch2);
        Boolean consumerState = consumer.isChecked();
        Boolean providerState = provider.isChecked();

        EditText editText1 = (EditText) findViewById(R.id.usernameText);
        username = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.password1Text);
        password1 = editText2.getText().toString();

        EditText editText3 = (EditText) findViewById(R.id.password2Text);
        password2 = editText3.getText().toString();
        if (username.equals("")){
            Toast toast=Toast.makeText(getApplicationContext(), "Please enter a username", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,150);
            toast.show();
        }
        if (!username.equals("") && password1.equals(password2) && !password1.equals("") && !password2.equals("")) {
            if(consumer.isChecked() ^ provider.isChecked()){
                //consumer XOR provider created account
                enterTableVal(username, password1);
                Toast toast=Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,150);
                toast.show();
                Intent acctCreatedIntent = new Intent(this, MainActivity.class);
                startActivity(acctCreatedIntent);
            }
            else{
                Toast toast=Toast.makeText(getApplicationContext(), "Please select an account type", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,150);
                toast.show();
            }
        }

        if (!(password1.equals(password2)) || password1.equals("") || password2.equals("")){
            Toast toast=Toast.makeText(getApplicationContext(), "Error! Passwords do not match.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,150);
            toast.show();
        }

    }

    public static void enterTableVal(String username, String password){
        final MobileServiceTable<DummyTable> mDummyTable = mClient.getTable(DummyTable.class);
        final DummyTable item = new DummyTable(username, password);

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mDummyTable.insert(item).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
        runAsyncTask(task);
    }

    private static AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }
}