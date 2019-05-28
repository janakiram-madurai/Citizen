package com.citizen.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UserReport {
	
	private String reportTittle;
	private String reportDesc;
	private String reportType;
	private String imageURL;
	private int user_id;
	private Date reportDate;
	private Date modifiedDate;
	private String comments;
	private String status;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long ID;
	public String getReportTittle() {
		return reportTittle;
	}
	public UserReport setReportTittle(String reportTittle) {
		this.reportTittle = reportTittle;
		return this;
	}
	public String getReportDesc() {
		return reportDesc;
	}
	public UserReport setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
		return this;
	}
	public String getReportType() {
		return reportType;
	}
	public UserReport setReportType(String reportType) {
		this.reportType = reportType;
		return this;
	}
	public String getImageURL() {
		return imageURL;
	}
	public UserReport setImageURL(String imageURL) {
		this.imageURL = imageURL;
		return this;
	}
	public long getID() {
		return ID;
	}
	public UserReport setID(long iD) {
		ID = iD;
		return this;
	}
	public int getUser_id() {
		return user_id;
	}
	public UserReport setUser_id(int user_id) {
		this.user_id = user_id;
		return this;
	}
	public Date getReportDate() {
		return reportDate;
		
	}
	public UserReport setReportDate(Date reportDate) {
		this.reportDate = reportDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public UserReport setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
	public String getComments() {
		return comments;
	}
	public UserReport setComments(String comments) {
		this.comments = comments;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public UserReport setStatus(String status) {
		this.status = status;
		return this;
	}
	
}
