package laoathsolutions.noyogurt.api;

/**
 * Created by ckrishna on 11/16/14.
 */
public class User {
    private String mId;
    private String mName;
    private float mScore;
    public User(String id, String name, float score) {
        mId = id;
        mName = name;
        mScore = score;
    }

    public String getId() {
        return mId;
    }

    @Override
    public String toString() {
        return "User{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mScore=" + mScore +
                '}';
    }

    public float getScore() {
        return mScore;
    }

    public String getName() {
        return mName;
    }
}
