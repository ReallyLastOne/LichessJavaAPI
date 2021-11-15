package board;

import utilities.Utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static utilities.URLs.eventStreamURL;
import static utilities.URLs.makeAMoveURL;

public class BoardOrganizer {

    private BoardOrganizer() {
        throw new AssertionError();
    }

    public static void streamIncomingEvents() throws IOException, InterruptedException { // doesnt work
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = Utilities.createAuthenticatedRequest(eventStreamURL);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static boolean makeABoardMove(String gameId, String move) throws IOException { // not tested
        URL url = new URL(makeAMoveURL.replace("{gameId}", gameId).replace("{move}", move));
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        return false;
    }
}
