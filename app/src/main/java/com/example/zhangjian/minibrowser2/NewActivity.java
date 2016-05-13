package com.example.zhangjian.minibrowser2;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.FormBody;
/**
 * Created by zhangjian on 2016/5/13.
 */
public class NewActivity extends AppCompatActivity implements OnClickListener {
    private Button bt_get;
    private Button bt_post;

    final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        bt_get = (Button) findViewById(R.id.button_get);
        bt_post = (Button) findViewById(R.id.button_post);

        bt_get.setOnClickListener(this);
        bt_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_get:
                getRequest();
                break;
            case R.id.button_post:
                postRequest();
                break;
        }
    }

    private void getRequest() {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url("http://www.sogou.com")
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("zhangjian", "打印输出Get响应数据：" + response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void postRequest() {
        FormBody body = new FormBody.Builder()
                .add("your_param_1", "your_value_1")
                .add("your_param_2", "your_value_2")
                .build();

        final Request request = new Request.Builder()
                .url("http://www.sogou.com")
                .post(body)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("zhangjian","打印POST响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
