package ru.stqa.learn.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("Danya");
        double len = 5;
        System.out.println("Square area with side " + len + " equals " + area (len));
        double a = 4;
        double b = 5;
        System.out.println("Square rectangle with sides "+ a+ " and "+b +" equals "+ area(a,b));

    }

    public static void hello(String somebody) {
        System.out.println("Hello " + somebody + "!");
    }
    
    public static double area(double l){
        return l*l;
    }
    public static double area(double a, double b){
        return a*b;
    }

}