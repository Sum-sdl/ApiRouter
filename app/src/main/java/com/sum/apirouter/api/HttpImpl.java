package com.sum.apirouter.api;

import com.sum.router.annotation.ApiImpl;

/**
 * Created by sdl on 2020/3/22
 */
@ApiImpl(IHttp.class)
public class HttpImpl implements IHttp {
    @Override
    public void post(String fun) {

    }
}
