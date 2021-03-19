package ru.sber.lesson2;

public class Main {

    public static void main(String[] args) {
        Integer Value1 = 2048;
        Integer Value2 = 1024;
        Integer Result1 = Calculator.add(Value1, Value2);
        int Result2 = Calculator.add(500, 700);
        System.out.println(Result1);
        System.out.println(Result2);
        Result1 = Calculator.subtract(Value1, Value2);
        Result2 = Calculator.subtract(500, 700);
        System.out.println(Result1);
        System.out.println(Result2);
    }
}
