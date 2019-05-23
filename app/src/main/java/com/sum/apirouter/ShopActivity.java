package com.sum.apirouter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sum.router.annotation.ApiRouter;


@ApiRouter("/shop/main")
public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


    }
}
