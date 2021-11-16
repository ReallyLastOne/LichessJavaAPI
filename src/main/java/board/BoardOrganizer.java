package board;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import utilities.Utilities;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static secret.Token.token;
import static utilities.URLs.eventStreamURL;
import static utilities.URLs.makeAMoveURL;

public class BoardOrganizer {

    private BoardOrganizer() {
        throw new AssertionError();
    }

    public static void streamIncomingEvents() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpUriRequest request = Utilities.createAuthenticatedGetRequest(eventStreamURL);

        try (var stream = client.execute(request).getEntity().getContent()) {
            var buffered = new BufferedReader(new InputStreamReader(new BufferedInputStream(stream)));
            while (true) {
                String value = buffered.readLine();
                if (!value.isBlank()) {
                    System.out.println(value);
                }
            }
        }
    }

    public static int makeABotMove(String gameId, String move) throws IOException { // not tested
        return makeABotMove(gameId, move, false);
    }

    public static int makeABotMove(String gameId, String move, boolean offeringDraw) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(makeAMoveURL.replace("{gameId}", gameId).replace("{move}", move)
                + "?offeringDraw=" + Boolean.valueOf(offeringDraw).toString());
        httpPost.setHeader("Authorization", "Bearer " + token);

        CloseableHttpResponse response = client.execute(httpPost);
        client.close();

        return response.getStatusLine().getStatusCode();
    }
}
