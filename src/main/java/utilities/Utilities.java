package utilities;

import java.net.URI;
import java.net.http.HttpRequest;

import static secret.Token.token;


public class Utilities {

    public static HttpRequest createAuthenticatedRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", " Bearer " + token)
                .build();
    }
}
