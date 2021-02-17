package com.davidparry.leaky;

public class LeakyBean {

    private final String leakMe;

    public LeakyBean(String leakMe) {
        this.leakMe = leakMe;
    }
}
