package games;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

@Getter
@ToString
class Opponent {
    private Object id;
    private String username;
    private int ai;
    private int rating;

    Opponent(JSONObject opponentObject) {
        id = opponentObject.get("id");
        username = opponentObject.getString("username");
        try {
            ai = opponentObject.getInt("ai");
        } catch (Exception e) {
            rating = opponentObject.getInt("rating");
        }
    }
}
