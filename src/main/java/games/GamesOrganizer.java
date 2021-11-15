package games;

import org.json.JSONArray;
import org.json.JSONObject;
import utilities.Utilities;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static utilities.URLs.ongoingGames;

public class GamesOrganizer {
    private GamesOrganizer() {
        throw new AssertionError();
    }

    public static List<Game> extractOngoingGames() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = Utilities.createAuthenticatedRequest(ongoingGames);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        List<Game> list = new ArrayList<>();
        JSONObject obj = new JSONObject(response.body());
        JSONArray in = obj.getJSONArray("nowPlaying");

        for (int i = 0; i < in.length(); i++) {
            JSONObject gameObject = (JSONObject) in.get(i);
            list.add(new Game(gameObject));
        }

        return list;
    }
}
