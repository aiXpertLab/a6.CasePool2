package com.hypech.lib;

import java.util.*;

public class This1{
    public static void main(String[] args){
        Banana  a = new Banana(),
                b = new Banana();
        a.peel(1);
        b.peel(2);
    }
}

class Banana {
    void peel(int i){
        System.out.println(i+i+i);
    }
}
