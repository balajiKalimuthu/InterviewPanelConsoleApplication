package com.zsgs.interviewpanelapp.modules;

public class Candidates {
	private int id;
	private String name;
	private String phoneNo;
	private String emailId;
	private String dateOfBirth;
	private String location;
	private String status = "NOT ATTENDED";
	private String result = "     -      ";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void showCandidateDetail() {
		System.out.format(" | %-3s | %-15s | %-20s | %-10s | %-13s | %-15s | %-12s | %-12s |\n", getId(), getName(),
				getEmailId(), getPhoneNo(), getDateOfBirth(), getLocation(), getStatus(), getResult());
	}

	public void showCandidateResult() {
		System.out.format("\n | %-3s | %-15s | %-20s | %-10s | %-12s | %-12s |", "Id", "NAME", "EMAIL ID", "PHONE NO",
				"STATUS", "RESULT");
		System.out.format("\n | %-3s | %-15s | %-20s | %-10s | %-12s | %-12s |", getId(), getName(), getEmailId(),
				getPhoneNo(), getStatus(), getResult());
	}

	public void showCandidateList() {
		System.out.format(" | %-3s | %-15s | %-20s | %-10s |\n", getId(), getName(), getEmailId(), getPhoneNo());
	}
}