package com.davidparry.leaky;

import java.util.HashMap;
import java.util.Map;

public class LeakyConfig {

    private final Map<LeakyKey, LeakyBean> LEAKY_MAP = new HashMap<>();

    public void setLeak(LeakyKey key, LeakyBean bean) {
        LEAKY_MAP.put(key, bean);
    }


}
