package laoathsolutions.noyogurt.api;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by ckrishna on 11/16/14.
 */
public class RestGetContentApi implements ContentApi {

    public static final int DAYS_IN_MILLISECONDS = 24 * 60 * 60 * 1000;
    private static RestGetContentApi sInstance;

    public static ContentApi getInstance(){
        if(sInstance==null) {
            sInstance = new RestGetContentApi();
        }
        return sInstance;
    }

    private static String sActiveGroup;
    private RestGetContentApi() {}


    @Override
    public String join(String user) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://10.0.2.2:8080/yogurtstore/api/join/" + user);
        try {
            HttpResponse response = client.execute(get);
            Log.e("LET_IT_BURN_JOIN", user + " " + getHttpContent(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sActiveGroup = "groupId1234";
        return sActiveGroup;
    }
    protected static final String readStream(InputStream in) throws IOException {
        Reader reader = new InputStreamReader(in, HTTP.UTF_8);
        StringBuilder sb = new StringBuilder();

        try {
            char[] buf = new char[256];
            int l;

            while ((l = reader.read(buf)) != -1) {
                sb.append(buf, 0, l);
            }
        } finally {
            reader.close();
        }

        return sb.toString();
    }
    protected static final String getHttpContent(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream in = null;
            try {
                in = entity.getContent();
                return readStream(in);
            }
            catch (IllegalStateException e) {
            }
            catch (IOException e) {
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    }
                    catch (IOException e) {
                    }
                }
            }

        }
        return null;
    }

    @Override
    public GroupInfo getGroupInfo(String groupId) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://10.0.2.2:8080/yogurtstore/api/groupinfo/" + groupId);
        try {
            HttpResponse response = client.execute(get);
            Log.e("LET_IT_BURN_GROUP", groupId + " " + getHttpContent(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
