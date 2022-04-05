package com.example.movierental.service;

import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Scanner;

public class UserLocationServiceImpl implements UserLocationService{
    Country country;

    public UserLocationServiceImpl() throws IOException, GeoIp2Exception {
        URL url = new URL("https://ip4only.me/api//");
        //Retrieving the contents of the specified page
        Scanner sc = new Scanner(url.openStream());
        //Instantiating the StringBuffer class to hold the result
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            sb.append(sc.next());
        }
        //Retrieving the String from the String Buffer object
        String result = sb.toString();
        String[] a = result.split(",");

        WebServiceClient client = new WebServiceClient.Builder(700850, "2aTLMY4H1sQGjT44").host("geolite.info")
                .build();
        InetAddress ipAddress = InetAddress.getByName((a[1]));
        // Do the lookup
        CountryResponse response = client.country(ipAddress);
        this.country = response.getCountry();

    }

    public String getLocation() {
        return this.country.getName();
    }
}
