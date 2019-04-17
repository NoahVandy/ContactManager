package com.example.contactmanager.dataAccess;

import android.content.Context;

import com.example.contactmanager.model.AddressBook;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataAccess {

    Context context;
    ObjectMapper om = new ObjectMapper();

    public DataAccess(Context context){
        this.context = context;

    }

    public void writeList(AddressBook addressBook, String fileName){


        File path = context.getExternalFilesDir(null);
        File file = new File(path, fileName);
        try {
            om.writerWithDefaultPrettyPrinter().writeValue(file, addressBook);
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public AddressBook readList(String fileName){
        File path = context.getExternalFilesDir(null);
        File file = new File(path, fileName);
        AddressBook returnList = new AddressBook(true);
        try {
            returnList = om.readValue(file, AddressBook.class);

        }catch (IOException e){
            e.printStackTrace();
        }
        return returnList;
    }
}
