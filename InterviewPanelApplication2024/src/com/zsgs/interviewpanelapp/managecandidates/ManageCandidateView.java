package com.zsgs.interviewpanelapp.managecandidates;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageCandidateView {
	private boolean valid;
	private ManageCandidateModel manageCandidateModel;
	private Scanner sc = new Scanner(System.in);

	public ManageCandidateView() {
		manageCandidateModel = new ManageCandidateModel(this);
	}

	public void init() {
		while (true) {
			try {
				System.out.println("\n      +---------------------------------+" + "\n"
						+ "      |      MANAGE CANDIDATE MENU      |" + "\n"
						+ "      +---------------------------------+" + "\n"
						+ "      | 1 | Add New Candidate           |" + "\n"
						+ "      | 2 | Remove Candidate            |" + "\n"
						+ "      | 3 | List the Applied Candidates |" + "\n"
						+ "      | 4 | View Candidate's Result     |" + "\n"
						+ "      | 0 | Back to Main Menu           |" + "\n"
						+ "      +---------------------------------+");
				System.out.print("\n  Your Choice: ");
				int choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					manageCandidateModel.isInterviewCompleted();
					break;
				case 2:
					isRemove();
					break;
				case 3:
					manageCandidateModel.listAppliedCandidate();
					break;
				case 4:
					manageCandidateModel.isEmptyCandidatesDetails();
					break;
				case 0:
					manageCandidateModel.toExportData();
					return;
				default:
					System.out.println("\nPlease enter valid choice...");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter valid choice...");
				sc.nextLine();
				init();
			}
		}
	}

	private void isRemove() {
		try {
			System.out.print("\n  Enter Candidate Id: ");
			int id = sc.nextInt();
			sc.nextLine();
			manageCandidateModel.isCandidateToRemove(id);
		} catch (InputMismatchException e) {
			System.out.println("\nPlease enter valid input...");
			sc.nextLine();
			isRemove();
		}
	}

	public void viewResult() {
		try {
			System.out.print("\n  Enter Candidate Id: ");
			int id = sc.nextInt();
			sc.nextLine();
			manageCandidateModel.candidateResult(id);
		} catch (InputMismatchException id) {
			System.out.println("\nPlease enter valid input...");
			sc.nextLine();
			isRemove();
		}
	}
	
	public void isRole() {
		System.out.println("\nDid you came for " + manageCandidateModel.interviewRole() + " role?");
		System.out.println("Type: Yes/No");
		System.out.print("\n  Type : ");
		String type = sc.nextLine();
		manageCandidateModel.isRoleYes(type);
	}

	public void showMessage(String showMessage) {
		System.out.println(showMessage);
	}

	public void create() {
		System.out.println("\n\t+--------------------------+" + "\n" + "\t|     CANDIDATE DETAILS    |" + "\n"
				+ "\t+--------------------------+");
		String phoneNo;
		do {
			System.out.print("\n  Phone No : ");
			phoneNo = sc.nextLine();
			valid = manageCandidateModel.validPhoneNo(phoneNo);
		} while (!valid);
		manageCandidateModel.isAvailablePhoneNo(phoneNo);
	}

	public void createDetails(String phoneNo) {
		String name, emailId, dob, location;
		do {
			System.out.print("\n  Name          : ");
			name = sc.nextLine();
			valid = manageCandidateModel.validName(name);
		} while (!valid);
		do {
			System.out.print("  Email Id      : ");
			emailId = sc.nextLine();
			valid = manageCandidateModel.validEmailId(emailId);
		} while (!valid);
		do {
			System.out.print("  Date of Birth : ");
			dob = sc.nextLine();
			valid = manageCandidateModel.validDate(dob);
		} while (!valid);
		do {
			System.out.print("  Location      : ");
			location = sc.nextLine();
			valid = manageCandidateModel.validLocation(location);
		} while (!valid);
		manageCandidateModel.addCandidate(name, phoneNo, emailId, dob, location);
	}
}