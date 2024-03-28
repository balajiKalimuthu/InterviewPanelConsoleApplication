package com.zsgs.interviewpanelapp.updateinterview;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateInterviewView {
	private Scanner sc = new Scanner(System.in);
	private UpdateInterviewModel updateInterviewModel;

	public UpdateInterviewView() {
		updateInterviewModel = new UpdateInterviewModel(this);
	}

	public void init() {
		while (true) {
			try {
				System.out.println("\n      +---------------------------------+" + "\n"
						+ "      |           UPDATE MENU           |" + "\n"
						+ "      +---------------------------------+" + "\n"
						+ "      | 1 | Start the Interview         |" + "\n"
						+ "      | 2 | Update Candidate Status     |" + "\n"
						+ "      | 3 | End the Interview           |" + "\n"
						+ "      | 0 | Back to Main Menu           |" + "\n"
						+ "      +---------------------------------+");
				System.out.print("\n  Your Choice: ");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					updateInterviewModel.toStart();
					break;
				case 2:
					updateInterviewModel.checkInterviewStatus();
					break;
				case 3:
					updateInterviewModel.toEnd();
					break;
				case 0:
					updateInterviewModel.toExportData();
					return;
				default:
					showMessage("\nPlease enter valid choice...");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter valid choice...");
				sc.nextLine();
				init();
			}
		}
	}

	public void toUpdate() {
		updateInterviewModel.isAvailableCurrentCandidate();
		showMessage("\nResult updated...");
		updateInterviewModel.checkNextCandidate();
	}

	public void showMessage(String showMessage) {
		System.out.println(showMessage);
	}

	public void toResult(int lastCandidate) {
		while (true) {
			try {
				System.out.println("\nUpdate Candidate's Result - ID " + lastCandidate);
				System.out.println("\n\t+------------------------+" + "\n" + "\t|    CANDIDATE RESULT    |" + "\n"
						+ "\t+------------------------+" + "\n" + "\t| 1 | Passed             |" + "\n"
						+ "\t| 2 | Rejected           |" + "\n" + "\t| 3 | On Hold            |" + "\n"
						+ "\t+------------------------+");
				System.out.print("\n  Your Choice: ");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					updateInterviewModel.passed(lastCandidate);
					return;
				case 2:
					updateInterviewModel.rejected(lastCandidate);
					return;
				case 3:
					updateInterviewModel.onHold(lastCandidate);
					return;
				default:
					System.out.println("\nPlease enter valid choice...");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter valid choice...");
				sc.nextLine();
				toResult(lastCandidate);
			}
		}
	}
}