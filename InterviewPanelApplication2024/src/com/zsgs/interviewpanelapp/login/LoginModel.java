package com.zsgs.interviewpanelapp.login;

import com.zsgs.interviewpanelapp.datastorage.DataBase;

public class LoginModel {
	private DataBase dataBase = DataBase.getInstance();
	private LoginView loginView;

	public LoginModel(LoginView loginView) {
		this.loginView = loginView;
	}

	public void checkLoginDetails(String adminName, String adminPassword) {
		if (dataBase.checkAdminName(adminName)) {
			if (dataBase.checkAdminPassword(adminName, adminPassword)) {
				loginView.showMessage("\nLogin successfully...");
				loginView.nextPage();
			} else {
				loginView.showMessage("\nInvalid admin password...");
				loginView.checkForLogin();
			}
		} else {
			loginView.showMessage("\nInvalid admin name...");
			loginView.checkForLogin();
		}
	}

	public void isType(String type) {
		if (type.equalsIgnoreCase("yes")) {
			loginView.proceedLogin();
		} else if (type.equalsIgnoreCase("no")) {
			dataBase.exportData();
			loginView.end();
		} else {
			loginView.showMessage("\nPlease enter valid type...");
			loginView.checkForLogin();
		}
	}
}