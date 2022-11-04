package ru.stqa.learn.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("Danya");
        Square s= new Square(5);
        System.out.println("Square area with side " + s.l+ " equals " + s.area ());
        Rectangle r = new Rectangle(4,5);

        System.out.println("Square rectangle with sides "+ r.a+ " and "+r.b +" equals "+ r.area());
        Point p1 = new Point();
        Point p2 = new Point();
        p1.x1= 5;
        p1.y1=3;
        p2.x2=10;
        p2.y2=7;

        System.out.println("Distance between two points = " + distance(p1,p2));
    }

    public static void hello(String somebody) {
        System.out.println("Hello " + somebody + "!");
    }
    public static double distance(Point p1, Point p2){
return Math.sqrt((p2.x2- p1.x1)*(p2.x2-p1.x1)+(p2.y2-p1.y1)*(p2.y2-p1.y1));
    }



}