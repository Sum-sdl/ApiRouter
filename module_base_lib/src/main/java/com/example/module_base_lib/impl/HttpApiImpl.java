package com.example.module_base_lib.impl;

import android.util.Log;

import com.example.module_base_lib.api.IHttpApi;

/**
 * Created by sdl on 2020/3/21
 */
class HttpApiImpl implements IHttpApi {

    @Override
    public void post(String apiName) {
        Log.i(getClass().getSimpleName(), "post->" + apiName);
    }

    @Override
    public void get(String apiName) {
        Log.i(getClass().getSimpleName(), "get->" + apiName);

    }

    @Override
    public void uploadFile(String fileName) {

        Log.i(getClass().getSimpleName(), "upload->" + fileName);
    }
}
