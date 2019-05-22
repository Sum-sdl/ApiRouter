package com.sum.apirouter.api.impl;

import com.sum.apirouter.api.InterA;
import com.sum.router.annotation.ApiRouter;

/**
 * Created by sdl on 2019-05-22.
 */
@ApiRouter("5119/A2")
class ImplA2 implements InterA {
    @Override
    public void fun1() {
        System.out.println("ImplA2 fun1 ----");
    }

    @Override
    public String read() {
        return "ImplA2 read ----";
    }
}
