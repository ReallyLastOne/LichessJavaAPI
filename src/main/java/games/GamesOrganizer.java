package games;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static secret.Token.token;
import static utilities.URLs.*;

public class GamesOrganizer {
    private GamesOrganizer() {
        throw new AssertionError();
    }

    public static List<Game> extractOngoingGames() throws IOException, InterruptedException {
        HttpClient client = HttpClients.createDefault();
        HttpUriRequest request = Utilities.createAuthenticatedGetRequest(ongoingGames);
        HttpResponse response = client.execute(request);

        List<Game> list = new ArrayList<>();
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        JSONObject obj = new JSONObject(content);
        JSONArray in = obj.getJSONArray("nowPlaying");

        for (int i = 0; i < in.length(); i++) {
            JSONObject gameObject = (JSONObject) in.get(i);
            list.add(new Game(gameObject));
        }

        return list;
    }

    public static int abortGame(String id) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(abortGame.replace("{gameId}", id));
        request.setHeader("Authorization", " Bearer " + token);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode();
    }

    public static int resignAGame(String id) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(resignAGame.replace("{gameId}", id));
        request.setHeader("Authorization", " Bearer " + token);
        HttpResponse response = client.execute(request);
        return response.getStatusLine().getStatusCode();
    }
}
