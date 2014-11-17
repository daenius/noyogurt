package laoathsolutions.noyogurt.api;

/**
 * Created by ckrishna on 11/16/14.
 */
public class ContentApiFactory {
    public static ContentApi getApi() {
        return StubContentApi.getInstance();
    }
}
