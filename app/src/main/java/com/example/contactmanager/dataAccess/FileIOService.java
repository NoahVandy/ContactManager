package com.example.contactmanager.dataAccess;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import com.example.contactmanager.GlobalList;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.contactmanager.businesServices.BusinessService;
import com.example.contactmanager.model.AddressBook;

public class FileIOService implements DataAccessService {

	Context context;

	ObjectMapper om = new ObjectMapper();

	public FileIOService(){

	}


	@Override
	public BusinessService readAllData() {
		BusinessService bs = new BusinessService();
		
		try {
			bs = new ObjectMapper().readerFor(BusinessService.class).readValue(new File("Contacts.json.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bs;
	}

	@Override
	public void writeAllData(BusinessService bs) {
	ObjectMapper om = new ObjectMapper();
		
		try {
			File path = GlobalList.getAppContext().getExternalFilesDir(null);
			File f = new File(path, "Contacts.json.txt");
			Log.d("noah", f.getAbsolutePath().toString());
			om.writerWithDefaultPrettyPrinter().writeValue(f, bs);
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
