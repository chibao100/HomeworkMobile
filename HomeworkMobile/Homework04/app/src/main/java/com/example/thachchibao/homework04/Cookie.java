package com.example.thachchibao.homework04;

import java.util.Random;

/**
 * Created by ThachChiBao on 2/25/2017.
 */

public class Cookie {

    public int cookie;

    public Cookie(int cookie) {
        this.cookie = cookie;
    }


    public int getCookie() {
        return cookie;
    }

    public void setCookie(int cookie) {
        this.cookie = cookie;
    }

    //


    public int takeCookie(){
        Random rand = new Random();
        int amount = rand.nextInt(cookie + 1); //take cookie
        cookie = cookie - amount; // a rest of cookie
        return amount;
    }


    public void bakeCookie(){
        Random rand = new Random();
        int max = 10;
        int min = 1;
        int amount = rand.nextInt((max - min) + 1) + min; // Max is 10 cookie
        cookie = cookie + amount;

    }

}
