package com.apps.bjorn.beartimer;

import java.util.*;

public class Track {

    int no, sec;
    String passname, length, hrt, rpm, pos;

    Track(int no, String passname, String length, String hrt, String rpm, String pos) {
        int a;
        String strMin, strSec;

        this.no = no;
        this.passname = passname;
        this.length = length;
        this.hrt = hrt;
        this.rpm = rpm;
        this.pos = pos;

        a = this.length.indexOf(":");
        strMin = this.length.substring(0, a);
        strSec = this.length.substring(a + 1);
        this.sec = Integer.valueOf(strMin) * 60 + Integer.valueOf(strSec);
    }
}

