package laoathsolutions.noyogurt.api;

/**
 * Created by ckrishna on 11/16/14.
 */
public interface ContentApi {
    // Join
    public String join(String user);

    // Get group info
    public GroupInfo getGroupInfo(String groupId);

    //pay
    public boolean pay(String groupId);

    // get active meal
    public String getActiveGroupId();

    public GroupInfo getActiveGroupInfo();

}
