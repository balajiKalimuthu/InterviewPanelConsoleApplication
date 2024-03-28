package com.zsgs.interviewpanelapp.updateinterview;

import com.zsgs.interviewpanelapp.datastorage.DataBase;

public class UpdateInterviewModel {
	private DataBase dataBase = DataBase.getInstance();
	private UpdateInterviewView updateInterviewView;

	public UpdateInterviewModel(UpdateInterviewView updateInterviewView) {
		this.updateInterviewView = updateInterviewView;
	}

	public void toStart() {
		if (dataBase.readyToStart()) {
			if (dataBase.isCandidatesAvailable()) {
				dataBase.getNextCandidate();
				updateInterviewView.showMessage("\nInterView Status: " + dataBase.getInterviewStatus());
			} else {
				updateInterviewView.showMessage("\nNo Candidates to attend interview...");
			}
		} else {
			updateInterviewView.showMessage("\nInterView Status: " + dataBase.getInterviewStatus());
		}
	}

	public void checkInterviewStatus() {
		if (dataBase.isEmptyCandidatesList()) {
			updateInterviewView.showMessage("\nNo candidates to attend interview...");
		} else {
			if (!dataBase.checkInterviewOnProcess()) {
				updateInterviewView.showMessage("\nInterView Status: " + dataBase.getInterviewStatus());
			} else {
				updateInterviewView.toUpdate();
//				checkNextCandidate();
			}
		}

	}

	public void isAvailableCurrentCandidate() {
		updateInterviewView.toResult(dataBase.getLastCandidate());
	}

	public void passed(int id) {
		dataBase.passed(id);
	}

	public void rejected(int id) {
		dataBase.rejected(id);
	}

	public void onHold(int id) {
		dataBase.onHold(id);
	}

	public void checkNextCandidate() {
		if (dataBase.checkNextCandidate()) {
			dataBase.getNextCandidate();
		} else {
			updateInterviewView.showMessage("\nNo candidates to attend interview...");
		}
	}

	public void toEnd() {
		if (dataBase.readyToEnd()) {
			if (dataBase.isEmptyCandidatesList()) {
				dataBase.endInterview();
				updateInterviewView.showMessage("\nInterView Status: " + dataBase.getInterviewStatus());
			} else {
				updateInterviewView.showMessage("\nInterView Status: " + dataBase.getInterviewStatus());
				updateInterviewView.showMessage("\nRemaining Candidates: " + dataBase.getRemainingCandidates());
			}
		} else {
			updateInterviewView.showMessage("\nInterView Status: " + dataBase.getInterviewStatus());
		}
	}

	public void toExportData() {
		dataBase.exportData();	
	}
}