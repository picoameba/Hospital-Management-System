package project;
//The purpose of this class is to ease up the transfer of arguments between functions
//Its all purpose, so it can be used to write a history log for anyone
public class HistoryHolder {
private String pId, dId, time, date, status, eventType, diagnosis, prescription, labRequest;

public String getpId() {
	return pId;
}

public void setpId(String pId) {
	this.pId = pId;
}

public String getdId() {
	return dId;
}

public void setdId(String dId) {
	this.dId = dId;
}

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getEventType() {
	return eventType;
}

public void setEventType(String eventType) {
	this.eventType = eventType;
}

public String getDiagnosis() {
	return diagnosis;
}

public void setDiagnosis(String diagnosis) {
	this.diagnosis = diagnosis;
}

public String getPrescription() {
	return prescription;
}

public void setPrescription(String prescription) {
	this.prescription = prescription;
}

public String getLabRequest() {
	return labRequest;
}

public void setLabRequest(String labRequest) {
	this.labRequest = labRequest;
}
}
