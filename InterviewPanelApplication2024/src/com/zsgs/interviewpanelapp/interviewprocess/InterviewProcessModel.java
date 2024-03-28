package com.zsgs.interviewpanelapp.interviewprocess;

import com.zsgs.interviewpanelapp.datastorage.DataBase;
import com.zsgs.interviewpanelapp.validator.ValueValidator;

public class InterviewProcessModel {
	private DataBase dataBase = DataBase.getInstance();
	private InterviewProcessView interviewProcessView;

	public InterviewProcessModel(InterviewProcessView interviewProcessView) {
		this.interviewProcessView = interviewProcessView;
	}

	// Interview Schedule Method
	public void startSetup() {
		if (dataBase.getInterviewDetails() == null || dataBase.getInterviewDetails().getId().equals("0")) {
			interviewProcessView.showMessage("\nPlease schedule the interview to proceed...");
			interviewProcessView.initiateInterviewSetup();
		} else {
			interviewProcessView.showMessage("\nInterview is already scheduled...");
			interviewProcessView.onSetupComplete(dataBase.getInterviewDetails());
		}
	}

	// Validation Methods
	public boolean vaildId(String id) {
		return ValueValidator.validateId(id);
	}

	public void invalidId() {
		interviewProcessView.showMessage("\n  ID format must follows:");
		interviewProcessView.showMessage("   - contains only whole numbers.");
		interviewProcessView.showMessage("   - maximum length: 7 digits.");
	}

	public boolean validName(String name) {
		return ValueValidator.validateName(name);
	}

	public void invalidName() {
		interviewProcessView.showMessage("\n  Name format must follows:");
		interviewProcessView.showMessage("   - contains only alphabets, dot, space.");
		interviewProcessView.showMessage("   - maximum length: 15 characters.");
	}

	public boolean validRole(String role) {
		return ValueValidator.validateRole(role);
	}

	public void invalidRole() {
		interviewProcessView.showMessage("\n  Role format must follows:");
		interviewProcessView.showMessage("   - contains only alphabets, space, hyphen.");
		interviewProcessView.showMessage("   - maximum length: 15 characters.");
	}

	public boolean validDate(String date) {
		return ValueValidator.validateDate(date);
	}

	public void invalidDate() {
		interviewProcessView.showMessage("\n  Date format must dd/MM/YYYY");
	}

	public void setInterviewDetails(String id, String name, String role, String date) {
		dataBase.setInterviewDetails(id, name, role, date);
		interviewProcessView.showMessage("\nInterview is scheduled...");
		interviewProcessView.onSetupComplete(dataBase.getInterviewDetails());
	}

	public void toShowStatus() {
		dataBase.toShowInterviewStatus();
	}

	public void toShowResult() {
		if (dataBase.checkInterviewStatus()) {
			dataBase.toShowInterviewResult();
		} else {
			interviewProcessView.showMessage("\nInterview is " + dataBase.getInterviewStatus() + "...");
		}
	}

	public void toCandidateList() {
		if (!dataBase.isEmptyCandidatesList()) {
			dataBase.showCandidateList();
		} else {
			interviewProcessView.showMessage("\nCandidate list is empty...");
		}
	}

	public void toExportData() {
		dataBase.exportData();
	}
}