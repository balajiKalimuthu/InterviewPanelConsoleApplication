package com.zsgs.interviewpanelapp.managecandidates;

import com.zsgs.interviewpanelapp.datastorage.DataBase;
import com.zsgs.interviewpanelapp.validator.ValueValidator;

public class ManageCandidateViewModel {
	private DataBase dataBase = DataBase.getInstance();
	private ManageCandidateView manageCandidateView;

	public ManageCandidateViewModel(ManageCandidateView manageCandidateView) {
		this.manageCandidateView = manageCandidateView;
	}

	public void isInterviewCompleted() {
		if (dataBase.checkInterviewStatus()) {
			manageCandidateView.showMessage("\nSorry interview process is already completed...");
		} else {
			manageCandidateView.isRole();
		}
	}

	public String interviewRole() {
		return dataBase.getInterviewRole();
	}

	public void isRoleYes(String type) {
		if (type.equalsIgnoreCase("yes")) {
			manageCandidateView.create();
		}
	}

	public void isAvailablePhoneNo(String phoneNo) {
		if (!dataBase.isEmptyCandidatesDetails()) {
			if (dataBase.isAvailablePhoneNo(phoneNo)) {
				manageCandidateView.showMessage("\nCandidate is already exits...");
				return;
			} else {
				manageCandidateView.createDetails(phoneNo);
			}
		} else {
			manageCandidateView.createDetails(phoneNo);
		}

	}

	public void addCandidate(String name, String phoneNo, String emailId, String dob, String location) {
		dataBase.addCandidate(name, phoneNo, emailId, dob, location);
		manageCandidateView.showMessage("\nCandidate is added");
	}

	public boolean validName(String name) {
		if (!ValueValidator.validateName(name)) {
			manageCandidateView.showMessage("\n  Name format must follows:");
			manageCandidateView.showMessage("   - contains only alphabets, dot, space.");
			manageCandidateView.showMessage("   - maximum length: 15 characters.");
		}
		return ValueValidator.validateName(name);
	}

	public boolean validDate(String date) {
		if (!ValueValidator.validateDate(date)) {
			manageCandidateView.showMessage("\n  Date format must dd/MM/YYYY");
		}
		return ValueValidator.validateDate(date);
	}

	public boolean validPhoneNo(String phoneNo) {
		if (!ValueValidator.validatePhoneNo(phoneNo)) {
			manageCandidateView.showMessage("\n  Phone No contains only 0-9 with length 10 digits.");
		}
		return ValueValidator.validatePhoneNo(phoneNo);
	}

	public boolean validEmailId(String emailId) {
		if (!ValueValidator.validateEmailId(emailId)) {
			manageCandidateView.showMessage("\n  Email format must follows:");
			manageCandidateView.showMessage("   - contains only small alphabets, numbers, dot, hyphen.");
			manageCandidateView.showMessage("   - must ends with @gmail.com.");
			manageCandidateView.showMessage("   - maximum length: 20 characters.");
		}
		return ValueValidator.validateEmailId(emailId);
	}

	public boolean validLocation(String location) {
		if (!ValueValidator.validateLocation(location)) {
			manageCandidateView.showMessage("\n  Location format must follows:");
			manageCandidateView.showMessage("   - contains only alphabets, space.");
			manageCandidateView.showMessage("   - maximum length: 15 characters.");
		}
		return ValueValidator.validateLocation(location);
	}

	public void listAppliedCandidate() {
		if (!dataBase.isEmptyCandidatesDetails()) {
			dataBase.listAppliedCandidates();
		} else {
			manageCandidateView.showMessage("\nCandidate details list is empty...");
		}
	}

	public void isCandidateToRemove(int id) {
		if (!dataBase.isEmptyCandidatesList()) {
			if (dataBase.isAvailableCandidate(id)) {
				dataBase.removeCandidate(id);
				manageCandidateView.showMessage("\nCandidate is removed...");
			} else {
				manageCandidateView.showMessage("\nCandidate Id is unavailable...");
			}
		} else {
			manageCandidateView.showMessage("\nCandidate list is empty...");
		}
	}

	public void candidateResult(int id) {
		if (dataBase.isAvailableCandidate(id)) {
			manageCandidateView.showMessage("\nCandidate Result ID: " + id);
			dataBase.viewCandidateResult(id);
		} else {
			manageCandidateView.showMessage("\nCandidate Id is unavailable...");
		}
	}

	public void isEmptyCandidatesDetails() {
		if (!dataBase.isEmptyCandidatesDetails()) {
			manageCandidateView.viewResult();
		} else {
			manageCandidateView.showMessage("\nCandidate details list is empty..");
		}
	}

	public void toExportData() {
		dataBase.exportData();
	}
}