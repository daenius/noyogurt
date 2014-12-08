package com.laoathsolutions.yogurtstore.api.data;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ckrishna on 11/16/14.
 */
@XmlRootElement(name = "GroupInfo")
public class GroupInfo {
    private String groupId;
    private List<User> users;
	private List<Transaction> transactions;

    public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public GroupInfo() {
	}
	public GroupInfo(String groupId, List<User> users,List<Transaction> transactions) {
        this.groupId = groupId;
        this.users = users;
        this.transactions = transactions;
    }

    public String getGroupId() {
        return groupId;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int getSize() {
        return users.size();
    }
    @Override
    public String toString() {
        return "GroupInfo{" +
                "mGroupId='" + groupId + '\'' +
                ", mUsers=" + users +
                ", mTransactions=" + transactions +
                '}';
    }
}
