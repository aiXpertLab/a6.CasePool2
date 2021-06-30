package com.hypech.lib;

class Person22{
    public String getInfo(){
        System.out.println("Person2 class: "+this);
        return null;
    }
}

public class This3 {
    public static void main(String[] args){
        Person22 per1 = new Person22();
        Person22 per2 = new Person22();
        System.out.println("Main Fangfa: " + per1);
        per1.getInfo();
        System.out.println("Main: " + per2);
        per2.getInfo();
    }
}
