package com.example.contactmanager.businesServices;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.example.contactmanager.GlobalList;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.contactmanager.dataAccess.DataAccessService;
import com.example.contactmanager.dataAccess.FileIOService;
import com.example.contactmanager.model.AddressBook;

public class BusinessService {

	AddressBook addressBook;


	
	public BusinessService(AddressBook list) {
		super();
		this.setList(list);
	}
	
	public BusinessService() {
		super();
		this.setList(new AddressBook());
	}
	
	
	public void saveAllLists(AddressBook list) {

		addressBook = GlobalList.getGlobalList();
		DataAccessService das = new FileIOService();
		das.writeAllData(this);
		

	}
	
	public AddressBook loadAllLists() {

		DataAccessService das = new FileIOService();
		return das.readAllData().getList();
	}
	
	public AddressBook getList() {
		return addressBook;
	}
	
	public void setList(AddressBook list) {
		this.addressBook = list;
	}

	public String toString() {
		return addressBook.getTheList().toString();
		
	}
	
	
	
}
