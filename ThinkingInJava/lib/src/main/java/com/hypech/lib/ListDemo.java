package com.hypech.lib;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args){
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(0,1);
        l1.add(1,2);
        System.out.println(l1);

        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(1);
        l2.add(2);
        l2.add(3);
        System.out.println(l2);

        l1.addAll(1,l2);
        System.out.println(l1);

        l1.remove(1);
        System.out.println(l1);
        System.out.println(l1.get(3));
        l1.set(0,5);
        System.out.println(l1);
    }
}

class GFG {
    public static void main(String args[]) {
        List<String> al = new ArrayList<>();

        al.add("Geeks");
        al.add("Geeks");
        al.add(1, "For");
        System.out.println(al);

        for (int i = 0; i < al.size(); i++){
            System.out.print(al.get(i)+":  ");
        }
        System.out.println();

        // Using the for each loop
        for (String str : al)
            System.out.print(str + " ");
    }
}


