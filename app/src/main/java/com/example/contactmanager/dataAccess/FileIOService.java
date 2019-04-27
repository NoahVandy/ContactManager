package com.example.contactmanager.dataAccess;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import com.example.contactmanager.GlobalList;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import com.example.contactmanager.businesServices.BusinessService;
import com.example.contactmanager.model.AddressBook;

public class FileIOService implements DataAccessService {

	Context context;

	ObjectMapper om = new ObjectMapper();

	public FileIOService(){

	}


	@Override
	public AddressBook readAllData() {
		AddressBook a = GlobalList.getGlobalList();
		
		try {
			File path = GlobalList.getAppContext().getExternalFilesDir(null);
			Log.d("noah", "reading from path: " + path);
			a = new ObjectMapper().readerFor(AddressBook.class).readValue(new File(path,"Contacts.json.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}

	@Override
	public void writeAllData(AddressBook a) {
	ObjectMapper om = new ObjectMapper();
		
		try {
			File path = GlobalList.getAppContext().getExternalFilesDir(null);
			File f = new File(path, "Contacts.json.txt");

			Log.d("noah", f.getAbsolutePath().toString());
			Log.d("noah", "this is the path: " + path);
			om.writerWithDefaultPrettyPrinter().writeValue(f, a);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
