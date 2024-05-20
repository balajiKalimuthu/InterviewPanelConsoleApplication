package com.zsgs.interviewpanelapp.updateinterview;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateInterviewView {
	private Scanner sc = new Scanner(System.in);
	private UpdateInterviewViewModel updateInterviewViewModel;

	public UpdateInterviewView() {
		updateInterviewViewModel = new UpdateInterviewViewModel(this);
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
					updateInterviewViewModel.toStart();
					break;
				case 2:
					updateInterviewViewModel.checkInterviewStatus();
					break;
				case 3:
					updateInterviewViewModel.toEnd();
					break;
				case 0:
					updateInterviewViewModel.toExportData();
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
		updateInterviewViewModel.isAvailableCurrentCandidate();
		showMessage("\nResult updated...");
		updateInterviewViewModel.checkNextCandidate();
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
					updateInterviewViewModel.passed(lastCandidate);
					return;
				case 2:
					updateInterviewViewModel.rejected(lastCandidate);
					return;
				case 3:
					updateInterviewViewModel.onHold(lastCandidate);
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