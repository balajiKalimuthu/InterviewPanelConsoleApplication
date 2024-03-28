package com.zsgs.interviewpanelapp.login;

import java.util.Scanner;

import com.zsgs.interviewpanelapp.InterviewPanelApp;
import com.zsgs.interviewpanelapp.interviewprocess.InterviewProcessView;

public class LoginView {
	private InterviewPanelApp interviewPanelApp = InterviewPanelApp.getInstance();
	private LoginModel loginModel;
	private Scanner sc = new Scanner(System.in);

	public LoginView() {
		loginModel = new LoginModel(this);
	}

	public void init() {
		System.out.println("\n +-----------------------------------------+\n |       " + interviewPanelApp.getAppName()
				+ "       |\n |                v " + interviewPanelApp.getAppVersion() + "                  |\n"
				+ " +-----------------------------------------+" + "");
		System.out.println("\nPlease login to proceed...");
		proceedLogin();
	}

	public void checkForLogin() {
		System.out.println("\nDo you want to continue login?");
		System.out.println("Type: Yes/No");
		System.out.print("\n  Type : ");
		String type = sc.nextLine();
		loginModel.isType(type);
	}

	public void end() {
		System.out.print("\n--- Thanks for using " + interviewPanelApp.getAppName());
		System.out.println(" v" + interviewPanelApp.getAppVersion() + " ---");
	}

	public void nextPage() {
		new InterviewProcessView().init();
	}

	public void proceedLogin() {
		String adminName, adminPassword;
		System.out.println("\n\t+--------------------------+" + "\n" + "\t|     ADMIN LOGIN PAGE     |" + "\n"
				+ "\t+--------------------------+" + "\n");
		System.out.print("  Name     : ");
		adminName = sc.nextLine();
		System.out.print("  Password : ");
		adminPassword = sc.nextLine();
		loginModel.checkLoginDetails(adminName, adminPassword);
	}

	public void showMessage(String showMessage) {
		System.out.println(showMessage);
	}
}