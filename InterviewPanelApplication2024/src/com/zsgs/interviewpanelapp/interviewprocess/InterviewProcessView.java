package com.zsgs.interviewpanelapp.interviewprocess;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.zsgs.interviewpanelapp.InterviewPanelApp;
import com.zsgs.interviewpanelapp.login.LoginView;
import com.zsgs.interviewpanelapp.managecandidates.ManageCandidateView;
import com.zsgs.interviewpanelapp.modules.InterviewDetails;
import com.zsgs.interviewpanelapp.updateinterview.UpdateInterviewView;

public class InterviewProcessView {
	private InterviewPanelApp interviewPanelApp = InterviewPanelApp.getInstance();
	private InterviewProcessViewModel interviewProcessViewModel;
	private Scanner sc = new Scanner(System.in);

	public InterviewProcessView() {
		interviewProcessViewModel = new InterviewProcessViewModel(this);
	}

	// Interview Details Method
	public void init() {
		interviewProcessViewModel.startSetup();
	}

	public void initiateInterviewSetup() {
		String id, name, role, date;
		boolean valid = false;
		System.out.println("\n\t+--------------------------+" + "\n" + "\t|     INTERVIEW DETAILS    |" + "\n"
				+ "\t+--------------------------+");
		do {
			System.out.print("\n  Id      : ");
			id = sc.nextLine();
			valid = interviewProcessViewModel.vaildId(id);
			if (!valid) {
				interviewProcessViewModel.invalidId();
			}
		} while (!valid);
		do {
			System.out.print("  HR Name : ");
			name = sc.nextLine();
			valid = interviewProcessViewModel.validName(name);
			if (!valid) {
				interviewProcessViewModel.invalidName();
			}
		} while (!valid);
		do {
			System.out.print("  Role    : ");
			role = sc.nextLine();
			valid = interviewProcessViewModel.validName(name);
			if (!valid) {
				interviewProcessViewModel.invalidName();
			}
		} while (!valid);
		do {
			System.out.print("  Date    : ");
			date = sc.nextLine();
			valid = interviewProcessViewModel.validDate(date);
			if (!valid) {
				interviewProcessViewModel.invalidDate();
			}
		} while (!valid);
		interviewProcessViewModel.setInterviewDetails(id, name, role, date);
	}

	public void onSetupComplete(InterviewDetails interviewDetails) {
		int choice = 0;
		while (true) {
			System.out.println("\n      +---------------------------------+" + "\n"
					+ "      |           MAIN MENU             |" + "\n" + "      +---------------------------------+"
					+ "\n" + "      | 1 | Current Status Of Interview |" + "\n"
					+ "      | 2 | List The Candidates         |" + "\n" + "      | 3 | Update Interview Status     |"
					+ "\n" + "      | 4 | Manage Candidates           |" + "\n"
					+ "      | 5 | Show Interview Result       |" + "\n" + "      | 9 | Logout                      |"
					+ "\n" + "      | 0 | Exit Application            |" + "\n"
					+ "      +---------------------------------+");
			try {
				System.out.print("\n  Your Choice: ");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					interviewProcessViewModel.toShowStatus();
					break;
				case 2:
					interviewProcessViewModel.toCandidateList();
					break;
				case 3:
					new UpdateInterviewView().init();
					break;
				case 4:
					new ManageCandidateView().init();
					break;
				case 5:
					interviewProcessViewModel.toShowResult();
					break;
				case 9:
					System.out.println("\n--- Logout successful ---");
					System.out.println("\nPlease login to proceed...");
					interviewProcessViewModel.toExportData();
					new LoginView().proceedLogin();
					return;
				case 0:
					interviewProcessViewModel.toExportData();
					exit();
					return;
				default:
					System.out.println("\nPlease enter valid choice...");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter valid choice...");
				sc.nextLine();
				onSetupComplete(interviewDetails);
			}
		}
	}

	private void exit() {
		System.out.println("\n--- Logout successful ---\n");
		System.out.print("\n--- Thanks for using " + interviewPanelApp.getAppName());
		System.out.println(" v" + interviewPanelApp.getAppVersion() + " ---");

	}

	public void showMessage(String showMessage) {
		System.out.println(showMessage);
	}
}