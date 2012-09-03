package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="CLASS")
@Proxy(lazy=false)
public class Class implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3454681505877948346L;
	@Id
	@Column(name="CLASS_NAME", length=50)
	String className;
	
	@Column(name="CLASS_DESC",length=100)
	String classDesc;
	
	@Column(name="TRAINEE_ID")
	Integer traineeId;
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
	public Integer getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Integer trianeeId) {
		this.traineeId = trianeeId;
	}
	
}
