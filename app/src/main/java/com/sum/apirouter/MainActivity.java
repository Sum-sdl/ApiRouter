package com.sum.apirouter;

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

//                Toast.makeText(MainActivity.this, "1", 1).show();

                Class router = Router.getInstance().findClassByRouter("5119/A1");

                if (router == null) {
                    return;
                }
//                Toast.makeText(MainActivity.this, "2", 1).show();
            }
        });

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                test1();
            }
        });

    }

    private void test1() {
        //package可见，不能方法
        try {
            InterA o = (InterA) Class.forName("com.sum.apirouter.api.impl.ImplA2").newInstance();
            o.read();
            Toast.makeText(MainActivity.this, o.read(), Toast.LENGTH_SHORT).show();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
