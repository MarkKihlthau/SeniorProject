package com.example.adspot;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.concurrent.ExecutionException;

public class CreateAccActivity extends AppCompatActivity {
    private static MobileServiceClient mClient;
    private static String mssg = "Creation Failed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        AzureService.initCon(this);
        mClient = AzureService.getClient();

        TextView welcomeText = findViewById(R.id.textView3);
        welcomeText.setText("Please enter your info to create a user account.");

        TextView errMsg = findViewById(R.id.createErr);
        errMsg.setText("");

    }

    public void newUserAcc(View view)
    {
        String username;
        String password1;
        String password2;

        EditText editText1 = (EditText) findViewById(R.id.usernameText);
        username = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.password1Text);
        password1 = editText2.getText().toString();

        EditText editText3 = (EditText) findViewById(R.id.password2Text);
        password2 = editText3.getText().toString();

        if (password1.length() < 7)
        {
            TextView errMsg = findViewById(R.id.createErr);
            errMsg.setText("Error! Password must be greater than 7 characters");
        }
        else if (password1.length() > 16)
        {
            TextView errMsg = findViewById(R.id.createErr);
            errMsg.setText("Error! Password must be less than 16 characters");
        }
        else if (password1.equals(password2)) {
            enterTableVal(username, password1);
            TextView errMsg = findViewById(R.id.createErr);
            errMsg.setText("Account Created!");
        }
        else if (!(password1.equals(password2))){
            TextView errMsg = findViewById(R.id.createErr);
            errMsg.setText("Error! Passwords do not match.");
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
