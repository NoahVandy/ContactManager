package com.example.contactmanager.dataAccess;

import com.example.contactmanager.businesServices.BusinessService;

public interface DataAccessService {

	public BusinessService readAllData();
	
	public void writeAllData(BusinessService contactApp);
	
}
