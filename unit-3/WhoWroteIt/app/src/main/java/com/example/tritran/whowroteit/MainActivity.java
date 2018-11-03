package com.example.tritran.whowroteit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    EditText mBookInput;
    TextView mTitleText;
    TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);
    }

    public void searchBooks(View view) {
        //gets searched query text
        String queryString = mBookInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            new FetchBook(mTitleText, mAuthorText).execute(queryString);
            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
        } else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            } else {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }

        //creates async task with provided params
        new FetchBook(mTitleText, mAuthorText).execute(queryString);
        new FetchBook(mTitleText, mAuthorText).execute(queryString);
        mAuthorText.setText("");
        mTitleText.setText(R.string.loading);

    }

    public class FetchBook extends AsyncTask<String, Void, String> {
        WeakReference<TextView> mTitleText;
        WeakReference<TextView> mAuthorText;
        int i = 0;
        String title = null;
        String authors = null;

        FetchBook(TextView titleText, TextView authorText) {
            this.mTitleText = new WeakReference<>(titleText);
            this.mAuthorText = new WeakReference<>(authorText);
        }

        @Override
        protected String doInBackground(String... strings) {

            return NetworkUtils.getBookInfo(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                // Convert the response into a JSON object.
                JSONObject jsonObject = new JSONObject(s);
                // Get the JSONArray of book items.
                JSONArray itemsArray = jsonObject.getJSONArray("items");

                // Initialize iterator and results fields.
                int i = 0;
                String title = null;
                String authors = null;

                // Look for results in the items array, exiting
                // when both the title and author
                // are found or when all items have been checked.
                while (i < itemsArray.length() &&
                        (authors == null && title == null)) {
                    // Get the current item information.
                    JSONObject book = itemsArray.getJSONObject(i);
                    JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                    // Try to get the author and title from the current item,
                    // catch if either field is empty and move on.
                    try {
                        title = volumeInfo.getString("title");
                        authors = volumeInfo.getString("authors");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Move to the next item.
                    i++;
                }

                // If both are found, display the result.
                if (title != null && authors != null) {
                    mTitleText.get().setText(title);
                    mAuthorText.get().setText(authors);
                } else {
                    // If none are found, update the UI to
                    // show failed results.
                    mTitleText.get().setText(R.string.no_results);
                    mAuthorText.get().setText("");
                }

            } catch (Exception e) {
                // If onPostExecute does not receive a proper JSON string,
                // update the UI to show failed results.
                mTitleText.get().setText(R.string.no_results);
                mAuthorText.get().setText("");
            }
        }


    }
}
