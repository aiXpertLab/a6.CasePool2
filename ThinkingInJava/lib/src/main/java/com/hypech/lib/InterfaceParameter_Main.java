package com.hypech.lib;

public class InterfaceParameter_Main implements InterfaceParameter_Interface {
    public static void main(String[] args) {
        InterfaceParameter_Main o = new InterfaceParameter_Main();
        new InterfaceParameter_ClassA(o);
    }

    @Override
    public void codeMsg1(String str) {
        System.out.println(str);
    }
}

