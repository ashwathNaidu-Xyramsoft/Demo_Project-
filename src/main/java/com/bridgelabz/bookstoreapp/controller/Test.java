package com.bridgelabz.bookstoreapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    int i1 =0;
    public Test() {
    }
    public Test(int i) {

    }
    public Test(long i) {

    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<Integer>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        int temp=0;
        int max=0;
        int min= numbers.get(0);

        for (int i = 0; i < numbers.size(); i++) {
            if(numbers.get(i) < min){
                min=numbers.get(i);
            } else if(numbers.get(i)>min){
                max=numbers.get(i);
            }
        }
        System.out.println(min +" "+max);
    }
}
