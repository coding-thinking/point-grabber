package com.sss.point.grabber.manager;

import org.springframework.stereotype.Service;

@Service
public class TimeManager {

    private int smallBase = 1000;
    private int midBase = 3000;
    private int highBase = 5000;
    private int longerBase = 8000;

    public  int small() {
        return smallBase + (int) (Math.random() * smallBase);
    }

    public  int mid() {
        return midBase + (int) (Math.random() * smallBase);
    }

    public  int high() {
        return highBase + (int) (Math.random() * smallBase);
    }

    public  int longer() {
        return longerBase + (int) (Math.random() * smallBase);
    }


}
