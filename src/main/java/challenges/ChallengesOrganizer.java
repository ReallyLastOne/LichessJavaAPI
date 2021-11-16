package challenges;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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


public class ChallengesOrganizer {
    private ChallengesOrganizer() {
        throw new AssertionError();
    }

    public static List<Challenge> extractChallengesList() throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpUriRequest request = Utilities.createAuthenticatedGetRequest(challengesURI);
        HttpResponse response = client.execute(request);

        List<Challenge> list = new ArrayList<>();
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        JSONObject obj = new JSONObject(content);

        JSONArray in = obj.getJSONArray("in");
        in.forEach(x -> list.add(new Challenge((JSONObject) x)));

        JSONArray out = obj.getJSONArray("out");
        out.forEach(x -> list.add(new Challenge((JSONObject) x)));

        return list;
    }

    public static int acceptChallenge(String id) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(acceptChallenge.replace("{challengeId}", id));
        httpPost.setHeader("Authorization", " Bearer " + token);
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
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

    public static void acceptFirstChallenge() throws IOException {
        List<Challenge> activeChallenges = ChallengesOrganizer.extractChallengesList();
        ChallengesOrganizer.acceptChallenge(activeChallenges.get(0).getId());
    }
}
