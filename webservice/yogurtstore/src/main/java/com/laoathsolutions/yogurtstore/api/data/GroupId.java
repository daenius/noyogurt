package com.laoathsolutions.yogurtstore.api.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GroupId")
public class GroupId {
	public GroupId() {
	}
	public GroupId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String id;
}
