package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="users")
@Proxy(lazy=false)

public class User implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="user_id")
	Integer userid;
	
	@Column(name="username", length=100)
	String userName;
	@Column(name="password", length=100)
	String password;
	@Column(name="enabled", length=50)
	Integer enabled;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
}