package utilities;


import org.apache.http.client.methods.HttpGet;

import static secret.Token.token;


public class Utilities {

    public static HttpGet createAuthenticatedGetRequest(String uri) {
        HttpGet get = new HttpGet(uri);
        get.setHeader("Authorization", " Bearer " + token);
        return get;
    }
}
