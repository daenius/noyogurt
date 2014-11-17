package laoathsolutions.noyogurt.api;

import android.util.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by ckrishna on 11/16/14.
 */
public class StubContentApi implements ContentApi {

    public static final int DAYS_IN_MILLISECONDS = 24 * 60 * 60 * 1000;
    private static StubContentApi sInstance;

    public static ContentApi getInstance(){
        if(sInstance==null) {
            sInstance = new StubContentApi();
        }
        return sInstance;
    }

    private static String sActiveGroup;
    private StubContentApi() {}


    @Override
    public String join(String user) {
        sActiveGroup = "groupId1234";
        return sActiveGroup;
    }

    @Override
    public GroupInfo getGroupInfo(String groupId) {
        int transactionsSize = 30;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>(transactionsSize);
        long curTime = System.currentTimeMillis();
        for (int i = 0; i < transactionsSize; i++) {
            int rand = (int)((Math.random() * 100) % 3);
            Transaction.Size size;
            switch (rand) {
                case 2: size = Transaction.Size.OO;
                    break;
                case 1: size = Transaction.Size.OC;
                    break;
                default: size = Transaction.Size.O;
            }
            rand = (int)((Math.random() * 1000) % 4);
            Transaction t = new Transaction("" + i, size, curTime - (TimeUnit.DAYS.toMillis(transactionsSize - i)), "" + (rand + 1));
            transactions.add(t);
        }
        Float[] scoresCal = new Float[]{0.0f, 0.0f, 0.0f};
        for(Transaction t : transactions) {
            if(t.getId().equals("1")) {
                scoresCal[0] += getFloatSize(t.getSize());
            }
        }
        Float min = findMin(scoresCal[0],scoresCal[1],scoresCal[2]);
        ArrayList<User> users = new ArrayList<User>(3);
        users.add(new User("1", "Chandra", scoresCal[0] - min));
        users.add(new User("2", "Dennis", scoresCal[1] - min));
        users.add(new User("3", "Chris", scoresCal[2] - min));
        users.add(new User("4", "Chris", scoresCal[2] - min));
        return new GroupInfo(groupId, users, transactions);
    }

    public Float findMin(Float a, Float b, Float c) {
        return Math.min(Math.min(a,b),c);
    }
    public Float getFloatSize(Transaction.Size size) {
        if(size == Transaction.Size.O) return  1.0f;
        if(size == Transaction.Size.OC) return  1.5f;
        return  2.0f;
    }
    @Override
    public boolean pay(String groupId) {
        return false;
    }

    @Override
    public String getActiveGroupId() {
        return sActiveGroup;
    }

    @Override
    public GroupInfo getActiveGroupInfo() {
        return getGroupInfo(sActiveGroup);
    }
}
