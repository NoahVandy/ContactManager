package com.example.contactmanager;

import android.app.Application;

import com.example.contactmanager.model.AddressBook;

import java.util.List;

public class GlobalList extends Application {

    private AddressBook globalList = new AddressBook();

    public AddressBook getGlobalList() {
        return globalList;
    }

    public void setGlobalList(AddressBook globalList) {
        this.globalList = globalList;
    }
}
