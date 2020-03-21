package com.example.module_base_lib.api;

/**
 * Created by sdl on 2020/3/21
 */
public interface IHttpApi {

    void post(String apiName);

    void get(String apiName);

    void uploadFile(String fileName);
}
