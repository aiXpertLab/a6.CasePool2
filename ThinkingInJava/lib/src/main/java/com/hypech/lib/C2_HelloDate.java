package com.hypech.lib;

import java.util.*;
public class C2_HelloDate {
    public static float en = 235f;

    public static void main(String[] args){
        float re = ret();
        System.out.println(re);
        System.out.println("hellow it is :");
        System.out.println(new Date());
    }

    public static float ret(){
        return en*1000;
    }
}
