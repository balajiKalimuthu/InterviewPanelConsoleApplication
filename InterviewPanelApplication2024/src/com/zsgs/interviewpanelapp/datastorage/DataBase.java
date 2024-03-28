package com.zsgs.interviewpanelapp.datastorage;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.reflect.TypeToken;
import com.zsgs.interviewpanelapp.datalayer.DataLayer;
import com.zsgs.interviewpanelapp.modules.Candidates;
import com.zsgs.interviewpanelapp.modules.InterviewDetails;


public class DataBase {
	private static DataBase dataBase;
	private static int uniqueId = 101;
	private static DataLayer dataLayer;
	private int lastCandidate;
	private InterviewDetails interviewDetails;
	private Map<Integer, InterviewDetails> interviewDetailsList = new HashMap<Integer, InterviewDetails>();
	private Map<Integer, Candidates> candidateDetails = new HashMap<Integer, Candidates>();
	private Map<Integer, String> candidateList = new HashMap<Integer, String>();

	private DataBase() {
	}

	public static DataBase getInstance() {
		if (dataBase == null) {
			dataBase = new DataBase();
			dataLayer = new DataLayer();
		}
		return dataBase;
	}

	public InterviewDetails getInterviewDetails() {
		for (Map.Entry<Integer, InterviewDetails> map : interviewDetailsList.entrySet()) {
			interviewDetails = map.getValue();
			break;
		}
		return interviewDetails;
	}

	public boolean checkAdminName(String adminName) {
		return (adminName.equals("zsgs")) || (adminName.equals("zsgsadmin"));
	}

	public boolean checkAdminPassword(String adminName, String adminPassword) {
		return (adminName.equals("zsgs") && adminPassword.equals("admin"))
				|| (adminName.equals("zsgsadmin") && adminPassword.equals("admin1"));
	}

	public void setInterviewDetails(String id, String name, String role, String date) {
		interviewDetails = new InterviewDetails();
		interviewDetails.setId(id);
		interviewDetails.setHrName(name);
		interviewDetails.setRole(role);
		interviewDetails.setDate(date);
		int num = Integer.parseInt(id);
		interviewDetailsList.put(num, interviewDetails);
		dataBase.serializeInterviewDetailsList();
	}

	public void toShowInterviewStatus() {
		interviewDetails.showStatus();
	}

	public boolean checkInterviewStatus() {
		return interviewDetails.getStatus().equals("COMPLETED");
	}

	public void toShowInterviewResult() {
		interviewDetails.showResult();
	}

	public String getInterviewStatus() {
		return interviewDetails.getStatus();
	}

	public String getInterviewRole() {
		return interviewDetails.getRole();
	}
	public boolean isEmptyCandidatesDetails() {
		return candidateDetails.isEmpty();
	}

	public void addCandidate(String name, String phoneNo, String emailId, String dob, String location) {
		Candidates candidate = new Candidates();
		candidate.setId(uniqueId);
		candidate.setName(name);
		candidate.setPhoneNo(phoneNo);
		candidate.setEmailId(emailId);
		candidate.setDateOfBirth(dob);
		candidate.setLocation(location);
		candidateDetails.put(uniqueId, candidate);
		candidateList.put(uniqueId, name);
		interviewDetails.setTotalCandidates(interviewDetails.getTotalCandidates()+1);
		interviewDetails.setRemaining(interviewDetails.getRemaining()+1);
		uniqueId++;
		exportData();
	}

	public boolean isAvailablePhoneNo(String phoneNo) {
		for (Map.Entry<Integer, Candidates> entry : candidateDetails.entrySet()) {
			if (entry.getValue().getPhoneNo().equals(phoneNo)) {
				return true;
			}
		}
		return false;
	}

	public void listAppliedCandidates() {
		System.out.format("\n | %-3s | %-15s | %-20s | %-10s | %-13s | %-15s | %-12s | %-12s |\n", "Id", "NAME",
				"EMAIL ID", "PHONE NO", "DATE OF BIRTH", "LOCATION", "STATUS", "RESULT");
		for (Map.Entry<Integer, Candidates> entry : candidateDetails.entrySet()) {
			entry.getValue().showCandidateDetail();
		}
	}

	public boolean isAvailableCandidate(int id) {
		return candidateDetails.containsKey(id);
	}

	public void viewCandidateResult(int id) {
		candidateDetails.get(id).showCandidateResult();
	}

	public void removeCandidate(int id) {
		candidateList.remove(id);
		interviewDetails.setTotalCandidates(interviewDetails.getTotalCandidates() - 1);
		interviewDetails.setRemaining(interviewDetails.getRemaining()-1);
		exportData();
	}

	public boolean isEmptyCandidatesList() {
		return candidateList.isEmpty();
	}

	public boolean readyToStart() {
		return interviewDetails.getStatus().equals("NOT YET STARTED");
	}

	public boolean isCandidatesAvailable() {
		return interviewDetails.getTotalCandidates() > 0;
	}

	public boolean checkInterviewOnProcess() {
		return interviewDetails.getStatus().equals("ON PROCESS");
	}

	public int getLastCandidate() {
		return lastCandidate;
	}

	public void setLastCandidate(int lastCandidate) {
		this.lastCandidate = lastCandidate;
	}

	public void passed(int id) {
		interviewDetails.setCompleted(interviewDetails.getCompleted() + 1);
		interviewDetails.setPassed(interviewDetails.getPassed() + 1);
		for (Entry<Integer, String> entry : candidateList.entrySet()) {
			if (entry.getKey() == id) {
				candidateDetails.get(entry.getKey()).setStatus("COMPLETED");
				candidateDetails.get(entry.getKey()).setResult("PASSED");
				candidateList.remove(entry.getKey());
				break;
			}
		}
		exportData();
	}

	public void rejected(int id) {
		interviewDetails.setCompleted(interviewDetails.getCompleted() + 1);
		interviewDetails.setRejected(interviewDetails.getRejected() + 1);
		for (Entry<Integer, String> entry : candidateList.entrySet()) {
			if (entry.getKey() == id) {
				candidateDetails.get(entry.getKey()).setStatus("COMPLETED");
				candidateDetails.get(entry.getKey()).setResult("REJECTED");
				candidateList.remove(entry.getKey());
				break;
			}
		}
		exportData();
	}

	public void onHold(int id) {
		interviewDetails.setCompleted(interviewDetails.getCompleted() + 1);
		interviewDetails.setOnHold(interviewDetails.getOnHold() + 1);
		for (Entry<Integer, String> entry : candidateList.entrySet()) {
			if (entry.getKey() == id) {
				candidateDetails.get(entry.getKey()).setStatus("COMPLETED");
				candidateDetails.get(entry.getKey()).setResult("ON HOLD");
				candidateList.remove(entry.getKey());
				break;
			}
		}
		exportData();
	}

	public boolean checkNextCandidate() {
		return candidateList.size() > 0;
	}

	public void getNextCandidate() {
		interviewDetails.setStatus("ON PROCESS");
		for (Map.Entry<Integer, String> entry : candidateList.entrySet()) {
			candidateDetails.get(entry.getKey()).setStatus("ON PROCESS");
			setLastCandidate(entry.getKey());
			break;
		}
		interviewDetails.setRemaining(candidateList.size() - 1);
		exportData();
	}

	public boolean readyToEnd() {
		return interviewDetails.getStatus().equals("ON PROCESS");
	}

	public void endInterview() {
		interviewDetails.setStatus("COMPLETED");
	}

	public int getRemainingCandidates() {
		return interviewDetails.getRemaining();
	}

	public void showCandidateList() {
		System.out.format("\n | %-3s | %-15s | %-20s | %-10s |\n", "Id", "NAME", "EMAIL ID", "PHONE NO");
		for (Map.Entry<Integer, String> entry : candidateList.entrySet()) {
			candidateDetails.get(entry.getKey()).showCandidateList();
		}
	}
	
	public void serializeInterviewDetailsList() {
        dataLayer.serializeData(interviewDetailsList, "InterviewDetailsList.txt");
    }
	public void deserializeInterviewDetailsList() {
		Type interviewDetailsListType = new TypeToken<HashMap<Integer, InterviewDetails>>(){
		}.getType();
		interviewDetailsList = dataLayer.deserializeData("InterviewDetailsListList.txt", interviewDetailsListType, HashMap.class);
	}
	public void serializeCandidateList() {
        dataLayer.serializeData(candidateList, "CandidateList.txt");
    }
	public void deserializeCandidateList() {
		Type candidateListType = new TypeToken<HashMap<Integer, Candidates>>(){
		}.getType();
		candidateDetails = dataLayer.deserializeData("CandidateList.txt", candidateListType, HashMap.class);
	}
	public void serializeCandidateDetails() {
        dataLayer.serializeData(candidateList, "CandidateDetails.txt");
    }
	public void deserializeCandidateDetails() {
		Type candidateDetailsType = new TypeToken<HashMap<Integer, Candidates>>(){
		}.getType();
		candidateDetails = dataLayer.deserializeData("CandidateDetails.txt", candidateDetailsType, HashMap.class);
	}
	
	public void exportData() {
		serializeInterviewDetailsList();
		serializeCandidateList();
		serializeCandidateDetails();
	}
	
	public void importData() {
		deserializeInterviewDetailsList();
		deserializeCandidateList();
		deserializeCandidateDetails();
	}
}