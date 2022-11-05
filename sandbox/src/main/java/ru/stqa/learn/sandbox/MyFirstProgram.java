package ru.stqa.learn.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("Danya");
        Square s= new Square(5);
        System.out.println("Square area with side " + s.l+ " equals " + s.area ());
        Rectangle r = new Rectangle(4,5);

        System.out.println("Square rectangle with sides "+ r.a+ " and "+r.b +" equals "+ r.area());


        Point p1 = new Point(5,7);
        Point p2 = new Point(7,10);
        System.out.println("Distance between two points = " + p1.distance(p2));
    }

    public static void hello(String somebody) {
        System.out.println("Hello " + somebody + "!");
    }




}