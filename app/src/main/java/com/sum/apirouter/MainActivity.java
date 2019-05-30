package com.sum.apirouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sum.apirouter.api.InterA;
import com.sum.router.api.Router;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Class router = Router.getInstance().findClassByRouter("/m5119/A3");

                if (router == null) {
                    return;
                }
                Toast.makeText(MainActivity.this, "[" + router.getCanonicalName() + "]", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class shop = Router.getInstance().findClassByRouter("/shop/main");
                Intent intent = new Intent(MainActivity.this, shop);
                startActivity(intent);
            }
        });

        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test3();
            }
        });

        findViewById(R.id.bt31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterA interA = Router.getInstance().findApiImpl("/m960x/A2");
                interA.fun1();
            }
        });

        findViewById(R.id.bt32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterA interA = Router.getInstance().findApiImpl("/m9601x/A33");
                if (interA != null) {
                    interA.fun1();
                }
            }
        });
    }

    private void test3() {
        InterA interA = Router.getInstance().findApiImpl("/m5119/A1");
        interA.fun1();
    }
}
