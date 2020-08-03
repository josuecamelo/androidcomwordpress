package com.josuecamelo.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Jaink on 14-09-2017.
 */

public class WPPostDetails extends AppCompatActivity {
    TextView title;
    WebView webView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetails);

        //title = (TextView) findViewById(R.id.title);
        webView = (WebView) findViewById(R.id.postwebview);
        Intent i = getIntent();
        int position = i.getExtras().getInt("itemPosition");

      //  title.setText( MainActivity.mListPost.get(position).getTitle().getRendered());
        Log.e("WpPostDetails ", "  title is " + MainActivity.mListPost.get(position).getTitle().getRendered());

        /*webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(MainActivity.mListPost.get(position).getLink());
        // to open webview inside app -- otherwise It will open url in device browser
        webView.setWebViewClient(new WebViewClient());*/

        /*System.out.println(MainActivity.mListPost.get(position).getExcerpt().getRendered());
        System.out.println(MainActivity.mListPost.get(position).getContent().getRendered());*/


        /*TextView textView = findViewById(R.id.textView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(MainActivity.mListPost.get(position).getContent().getRendered(), Html.FROM_HTML_MODE_COMPACT));
        }else {
            textView.setText(Html.fromHtml(MainActivity.mListPost.get(position).getContent().getRendered()));
        }*/

        /*webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(MainActivity.mListPost.get(position).getContent().getRendered(), null, null);
        // to open webview inside app -- otherwise It will open url in device browser
        webView.setWebViewClient(new WebViewClient());*/
    }
}
