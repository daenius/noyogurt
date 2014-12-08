package com.laoathsolutions.yogurtstore.api.data;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ckrishna on 11/16/14.
 */
@XmlRootElement(name = "Transation")
public class Transaction {
    private String id;
	private Size size;
    private long timeStamp;
    private String user;

    public void setId(String id) {
		this.id = id;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Transaction() {
	}

	public Transaction(String id, Size size, long timeStamp, String user) {
        this.id = id;
        this.size = size;
        this.timeStamp = timeStamp;
        this.user = user;
    }

    public static enum Size { O, OC, OO}

    public String getId() {
        return id;
    }

    public Transaction.Size getSize() {
        return size;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "mId='" + id + '\'' +
                ", mSize=" + size +
                ", mTimeStamp=" + new SimpleDateFormat().format(timeStamp) +
                ", mUser='" + user + '\'' +
                '}';
    }
}
