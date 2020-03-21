package com.sum.apirouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.module_base_lib.api.IHttpApi;
import com.sum.router.api.Router;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Class router = Router.getInstance().findClassByRouter("/base/http");
                Class router = Router.getInstance().findClassByRouter("/login/main");
                if (router == null) {
                    return;
                }
                Toast.makeText(MainActivity.this, "[" + router.getCanonicalName() + "]", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class shop = Router.getInstance().findClassByRouter("/login/main");
//                Class shop = Router.getInstance().findClassByRouter("/shop/main");
                if (shop == null) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, shop);
                startActivity(intent);
            }
        });

        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //调用公共模块的接口功能
                IHttpApi apiImpl = Router.getInstance().findApiImpl(IHttpApi.class);
                if (apiImpl == null) {
                    return;
                }
                apiImpl.post("hello post");

            }
        });

        findViewById(R.id.bt4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        findViewById(R.id.bt5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
