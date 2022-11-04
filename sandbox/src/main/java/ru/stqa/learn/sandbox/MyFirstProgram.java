package ru.stqa.learn.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("Danya");
        Square s= new Square(5);
        System.out.println("Square area with side " + s.l+ " equals " + s.area ());
        Rectangle r = new Rectangle(4,5);

        System.out.println("Square rectangle with sides "+ r.a+ " and "+r.b +" equals "+ r.area());

    }

    public static void hello(String somebody) {
        System.out.println("Hello " + somebody + "!");
    }
    



}