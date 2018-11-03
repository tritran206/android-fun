package com.example.tritran.simpleasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the state of textview
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);

        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
        private WeakReference<TextView> mTextView;

        SimpleAsyncTask(TextView tv) {
            mTextView = new WeakReference<>(tv);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //sets the max value for pb
            mProgressBar.setMax(100);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(Void... voids) {
            // Generate a random number between 0 and 10
            Random r = new Random();
            int n = r.nextInt(11);

            // Make the task take long enough that we have
            // time to rotate the phone while it is running
            int s = n * 2000;

            try {
                // Sleep for the random amount of time
                for (int i = 1; i < 11; i++) {
                    Thread.sleep(s/10);
                    publishProgress(i*10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            // Return a String result
            return "Awake at last after sleeping for " + s + " milliseconds!";
        }

        protected void onPostExecute(String result) {
            mTextView.get().setText(result);

            Toast.makeText(getApplicationContext(), "Finished napping", Toast.LENGTH_SHORT).show();
            //resets progress bar to 0
            mProgressBar.setProgress(0);
        }
    }

    public void startTask(View view) {
        //puts method in textview
        mTextView.setText(R.string.napping);

        //start asynctask
        new SimpleAsyncTask(mTextView).execute();
    }
}
