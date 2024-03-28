package com.zsgs.interviewpanelapp.modules;

public class InterviewDetails {
	private String id;
	private String hrName;
	private String role;
	private String date;
	private int totalCandidates;
	private int completed;
	private int remaining;
	private int passed;
	private int rejected;
	private int onHold;
	private String status = "NOT YET STARTED";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHrName() {
		return hrName;
	}

	public void setHrName(String hrName) {
		this.hrName = hrName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTotalCandidates() {
		return totalCandidates;
	}

	public void setTotalCandidates(int totalCandidates) {
		this.totalCandidates = totalCandidates;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	public int getPassed() {
		return passed;
	}

	public void setPassed(int passed) {
		this.passed = passed;
	}

	public int getRejected() {
		return rejected;
	}

	public void setRejected(int rejected) {
		this.rejected = rejected;
	}

	public int getOnHold() {
		return onHold;
	}

	public void setOnHold(int onHold) {
		this.onHold = onHold;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void showResult() {
		System.out.println("\nINTERVIEW RESULT: ");
		System.out.format("\n | %-7s | %-15s | %-15s | %-10s | %-5s | %-6s | %-8s | %-6s |\n", "Id", "HR NAME", "ROLE",
				"DATE", "TOTAL", "PASSED", "REJECTED", "ONHOLD");
		System.out.format(" | %-7s | %-15s | %-15s | %-10s | %-5s | %-6s | %-8s | %-6s |\n", getId(), getHrName(),
				getRole(), getDate(), getTotalCandidates(), getPassed(), getRejected(), getOnHold());

	}

	public void showStatus() {
		System.out.println("\nINTERVIEW STATUS: ");
		System.out.println("\n Current status of Interview: " + getStatus());
		System.out.format("\n | %-7s | %-15s | %-15s | %-10s | %-5s | %-9s | %-9s |\n", "Id", "HR NAME", "ROLE", "DATE",
				"TOTAL", "COMPLETED", "REMAINING");
		System.out.format(" | %-7s | %-15s | %-15s | %-10s | %-5s | %-9s | %-9s |\n", getId(), getHrName(), getRole(),
				getDate(), getTotalCandidates(), getCompleted(), getRemaining());
	}
}