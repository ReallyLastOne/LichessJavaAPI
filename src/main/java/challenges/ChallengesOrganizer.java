package challenges;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.Utilities;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static secret.Token.token;
import static utilities.URLs.*;


public class ChallengesOrganizer {

    private ChallengesOrganizer() {
        throw new AssertionError();
    }

    public static List<Challenge> extractChallengesList() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = Utilities.createAuthenticatedRequest(challengesURI);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        List<Challenge> list = new ArrayList<>();
        JSONObject obj = new JSONObject(response.body());
        JSONArray in = obj.getJSONArray("in");
        for (int i = 0; i < in.length(); i++) {
            JSONObject challengeObject = (JSONObject) in.get(i);
            list.add(new Challenge(challengeObject));
        }

        JSONArray out = obj.getJSONArray("out");
        for (int i = 0; i < out.length(); i++) {
            JSONObject challengeObject = (JSONObject) out.get(i);
            list.add(new Challenge(challengeObject));
        }

        return list;
    }

    public static int acceptChallenge(String id) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(acceptChallenge.replace("{challengeId}", id));
        httpPost.setHeader("Authorization", " Bearer " + token);
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        System.out.println(response.getStatusLine());
        return response.getStatusLine().getStatusCode();
    }

    public static int challengeTheAI(
            String level, String limit, String increment, String days, String color,
            String variant, String fen)
            throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(challengeAI);
        httpPost.setHeader("Authorization", " Bearer " + token);
        httpPost.setHeader("Content-Type", "application/json");

        String json = """
                {"level":"{level}",
                "limit":"{limit}",
                "increment":"{increment}",
                "days":"{days}",
                "color":"{color}",
                "variant":"{variant}",
                "fen":"{fen}"
                }
                """.replace("{level}", level).replace("{limit}", limit).replace("{increment}", increment)
                .replace("{days}", days).replace("{color}", color).replace("{variant}", variant)
                .replace("{fen}", fen);

        StringEntity requestEntity = new StringEntity(
                json,
                ContentType.APPLICATION_FORM_URLENCODED);
        httpPost.setEntity(requestEntity);

        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        return response.getStatusLine().getStatusCode();
    }

    public static void acceptFirstChallenge() throws AuthenticationException, IOException, InterruptedException {
        List<Challenge> activeChallenges = ChallengesOrganizer.extractChallengesList();
        System.out.println(activeChallenges);
        System.out.println(activeChallenges.get(0).getId());
        ChallengesOrganizer.acceptChallenge(activeChallenges.get(0).getId());
    }
}
