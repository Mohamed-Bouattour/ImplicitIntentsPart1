package com.example.implicitintents;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ShareCompat.IntentBuilder;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent= getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String uri_string = getString(R.string.uri_label)
                    + uri.toString();
            TextView textView = findViewById(R.id.text_uri_message);
            textView.setText(uri_string);
        }
    }
    @SuppressLint("QueryPermissionsNeeded")
    public void openWebsite (View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity (getPackageManager()) != null) {
            startActivity (intent);
        } else {
            Log.d("ImplicitIntents","Can't handle this intent!");
        }
    }
    @SuppressLint("QueryPermissionsNeeded")
    public void openLocation (View view) {
        String loc = mLocationEdit.getText().toString();
        Uri addressUri = Uri.parse("geo: 0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if(null != intent.resolveActivity(getPackageManager())) {
            startActivity (intent);
        } else {
            Log.d("ImplicitIntents","Can't handle this intent!");
        }
    }
    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.setText (txt);
    }
}
