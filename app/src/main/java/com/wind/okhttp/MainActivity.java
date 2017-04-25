package com.wind.okhttp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wind.okhttp.model.NewsBean;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private OkHttpClient client=new OkHttpClient();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.lv_loaddata);
        loadData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,NewsDetailActivity.class);
                String url=NewsListBaseAdapter.list.get(position).url;
                intent.putExtra("url",url);
                startActivity(intent);
                Log.i("Simon",url);
            }
        });
    }

    private void loadData() {
        final Gson gson =new Gson();
      final   Request request=new Request.Builder()
                .get()
                .url("http://v.juhe.cn/toutiao/index?type=keji&key=65d4c89f2460e131bd8b288f3f70bff6")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response;
                try {
                    response=client.newCall(request).execute();
                    if (response.isSuccessful())
                    {
                          String content=response.body().string();
                         NewsBean newsBean=gson.fromJson(content,NewsBean.class);

                         final String reason=newsBean.reason;

                                NewsBean.Second second=newsBean.result;
                                final List<NewsBean.Second.Third> list=second.data;
                                //在主线程更新UI
                                runOnUiThread(new Runnable() {
                                        @Override
                                         public void run() {
                                       listView.setAdapter(new NewsListBaseAdapter(list,MainActivity.this));
                                    }
                                });


                                String title=list.get(1).title;
                              Log.i(">>>>>>>>>>>>>>>>>>",title);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
