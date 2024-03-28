package com.zsgs.interviewpanelapp;

import com.zsgs.interviewpanelapp.datastorage.DataBase;
import com.zsgs.interviewpanelapp.login.LoginView;

public class InterviewPanelApp {
	private static InterviewPanelApp interviewPanelApp;
	private DataBase dataBase = DataBase.getInstance();
	private String appName = "Interview Panel Application";
	private String appVersion = "0.0.1";

	private InterviewPanelApp() {
	}

	public static void main(String[] args) {
		InterviewPanelApp.getInstance().create();
	}

	private void create() {
		dataBase.importData();
		LoginView loginView = new LoginView();
		loginView.init();
	}

	public static InterviewPanelApp getInstance() {
		if (interviewPanelApp == null) {
			interviewPanelApp = new InterviewPanelApp();
		}
		return interviewPanelApp;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}
}