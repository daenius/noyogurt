package laoathsolutions.noyogurt.api;

import java.util.List;

/**
 * Created by ckrishna on 11/16/14.
 */
public class GroupInfo {
    private String mGroupId;
    private List<User> mUsers;
    private List<Transaction> mTransactions;

    private GroupInfo(){}
    public GroupInfo(String groupId, List<User> users,List<Transaction> transactions) {
        mGroupId = groupId;
        mUsers = users;
        mTransactions = transactions;
    }

    public String getGroupId() {
        return mGroupId;
    }

    public List<User> getUsers() {
        return mUsers;
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
                ", mTransactions=" + mTransactions +
                '}';
    }
}
