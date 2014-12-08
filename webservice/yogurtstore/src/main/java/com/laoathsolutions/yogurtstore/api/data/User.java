package com.laoathsolutions.yogurtstore.api.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ckrishna on 11/16/14.
 */

@XmlRootElement(name = "User")
public class User {
    private String id;
    private String name;
    private float score;
    public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public User() {
	}

	public User(String id, String name, float score) {
    	this.id = id;
    	this.name = name;
    	this.score = score;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "mId='" + id + '\'' +
                ", mName='" + name + '\'' +
                ", mScore=" + score +
                '}';
    }

    public float getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}

