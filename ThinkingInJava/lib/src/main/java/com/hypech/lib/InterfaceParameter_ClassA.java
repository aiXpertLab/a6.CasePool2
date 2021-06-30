package com.hypech.lib;

public class InterfaceParameter_ClassA {
    private InterfaceParameter_Main ip;

    public InterfaceParameter_ClassA(InterfaceParameter_Main ip2) {
        this.ip = ip2;
        set();
    }

    public void set() {
        ip.codeMsg1("A class");
    }
}
