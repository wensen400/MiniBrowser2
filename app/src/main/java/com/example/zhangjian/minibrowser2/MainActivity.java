package com.example.zhangjian.minibrowser2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {

    EditText url;
    WebView showWebView;
    Button searchbutton;
    String urlstr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //浏览器部分
        url = (EditText) findViewById(R.id.url);
        showWebView = (WebView) findViewById(R.id.show);
        searchbutton = (Button) findViewById(R.id.searchbutton);

        String ustr = "http://wap.sogou.com/web/searchList.jsp?&keyword=";
        //url.setText(ustr);
        //showWebView.loadUrl(ustr);

        showWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //enable javascript
        showWebView.getSettings().setJavaScriptEnabled(true);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlstr = url.getText().toString();
                showWebView.loadUrl("http://wap.sogou.com/web/searchList.jsp?&keyword="+urlstr);
            }
        });


    }

    public  void clickNewWindow(View view){
        startActivity(new Intent(this,NewActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
