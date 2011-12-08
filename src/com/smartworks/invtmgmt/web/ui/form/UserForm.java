package com.smartworks.invtmgmt.web.ui.form;

import com.smartworks.invtmgmt.core.domain.User;

public class UserForm {
	boolean edit = false;
	User user;

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
