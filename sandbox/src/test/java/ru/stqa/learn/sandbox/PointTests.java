package ru.stqa.learn.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance(){

        Point p1 = new Point(5,7);
        Point p2 = new Point(7,10);
        Assert.assertEquals(p1.distance(p2),3.605551275463989);


    }
    public void testDistance1(){
        Point p1 = new Point(7,10);
        Point p2 = new Point(10,14);
        Assert.assertEquals(p1.distance(p2),5.0);
    }
}
