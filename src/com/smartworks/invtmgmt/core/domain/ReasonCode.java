package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.smartworks.invtmgmt.core.transaction.ReasonCodeEnum;

@Entity
@Table(name="REASON_CODE")
@Proxy(lazy=false)
public class ReasonCode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="REASON_ID")
	Integer reasonId;
	
	@Column(name="REASON_CODE")
	@Enumerated(EnumType.STRING)
	private ReasonCodeEnum reasonCode;
	
	
	@Column(name="REASON_CODE_DESC")
	private String reasonDesc;

	public ReasonCodeEnum getReasonCode() {
		return reasonCode;
	}

	public Integer getreasonId() {
		return reasonId;
	}

	public String getReasonCodeDesc() {
		return reasonDesc;
	}
	public void setReasonId(Integer Id) {
			this.reasonId = Id;
	}
	public void setReasonCode(ReasonCodeEnum reasonCode) {
		this.reasonCode = reasonCode;
	}

	public void setReasonCodeDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	
	
}
