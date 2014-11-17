package laoathsolutions.noyogurt.api;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

/**
 * Created by ckrishna on 11/16/14.
 */
public class Transaction {
    private String mId;
    private Size mSize;
    private long mTimeStamp;
    private String mUser;

    public Transaction(String id, Size size, long timeStamp, String user) {
        mId = id;
        mSize = size;
        mTimeStamp = timeStamp;
        mUser = user;
    }

    public static enum Size { O, OC, OO}

    public String getId() {
        return mId;
    }

    public Transaction.Size getSize() {
        return mSize;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public String getUser() {
        return mUser;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "mId='" + mId + '\'' +
                ", mSize=" + mSize +
                ", mTimeStamp=" + new SimpleDateFormat().format(mTimeStamp) +
                ", mUser='" + mUser + '\'' +
                '}';
    }
}
