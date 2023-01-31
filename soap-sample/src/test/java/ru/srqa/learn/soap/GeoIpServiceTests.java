package ru.srqa.learn.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {
    @Test
    public void myIp(){
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.152");
        System.out.println(geoIp);
    }
}
