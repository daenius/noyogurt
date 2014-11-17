package laoathsolutions.noyogurt.api;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ckrishna on 11/16/14.
 */
public class GroupInfo {
    private String mGroupId;
    private List<User> mUsers;
    private Map<String, Float> mScores;
    private List<Transaction> mTransactions;

    private GroupInfo(){}
    public GroupInfo(String groupId, List<User> users,List<Transaction> transactions) {
        mGroupId = groupId;
        mUsers = users;
        mScores = new HashMap<String, Float>(users.size());
        for(User user : users) {
            mScores.put(user.getId(), user.getScore());
        }
        mTransactions = transactions;
    }

    public String getGroupId() {
        return mGroupId;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public Map<String, Float> getScores() {
        return mScores;
    }

    public List<Transaction> getTransactions() {
        return mTransactions;
    }

    public int getSize() {
        return mUsers.size();
    }
    @Override
    public String toString() {
        return "GroupInfo{" +
                "mGroupId='" + mGroupId + '\'' +
                ", mUsers=" + mUsers +
                ", mScores=" + mScores +
                ", mTransactions=" + mTransactions +
                '}';
    }
}
