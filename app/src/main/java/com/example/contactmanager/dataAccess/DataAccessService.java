package com.example.contactmanager.dataAccess;

import com.example.contactmanager.businesServices.BusinessService;
import com.example.contactmanager.model.AddressBook;

public interface DataAccessService {

	public AddressBook readAllData();
	
	public void writeAllData(AddressBook contactApp);
	
}
