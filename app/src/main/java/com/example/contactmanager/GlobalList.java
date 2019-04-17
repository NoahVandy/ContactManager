package com.example.contactmanager;

import android.app.Application;
import android.content.Context;

import com.example.contactmanager.model.AddressBook;

import java.util.List;

public class GlobalList extends Application {

    private static Context context;

    private static AddressBook globalList = new AddressBook();

    public static AddressBook getGlobalList() {
        return globalList;
    }


    public void onCreate() {
        super.onCreate();
        GlobalList.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return GlobalList.context;
    }

//    public static void setGlobalList(AddressBook addressBook){
//        addressBook = addressBook;
//    }
}
