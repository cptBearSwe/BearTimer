package com.apps.bjorn.beartimer;

import java.util.ArrayList;

public class GlobalParameters {
    public ArrayList<Integer> lstTimes;
    public ArrayList<String> lstPass;

    private GlobalParameters(){
        lstTimes = new ArrayList<Integer>();
        lstPass = new ArrayList<String>();
    }

    private static GlobalParameters instance;

    public static GlobalParameters getInstance() {
        if (instance == null) instance = new GlobalParameters();
        return instance;
    }
}
