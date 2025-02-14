package com.zxwang.zexx.pojo;

import java.math.BigDecimal;

public class demo {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(4041.67);
        System.out.println(a.multiply(new BigDecimal(23)).add(new BigDecimal(567.68)));
    }
}
