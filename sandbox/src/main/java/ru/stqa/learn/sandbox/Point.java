package ru.stqa.learn.sandbox;

public class Point {
    public double x2;
    public double x1;
    public double y1;
    public double y2;
    public Point(double x1, double y1){
        this.x1=x1;

        this.y1=y1;

    }

   public double distance(Point p2){
       return Math.sqrt((p2.x1- this.x1)*(p2.x1-this.x1)+(p2.y1-this.y1)*(p2.y1-this.y1));
   }



}
