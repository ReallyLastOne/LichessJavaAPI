package challenges;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

@Getter
@ToString
class Challenger {
    private String id;
    private String name;
    private Object title;
    private int rating;
    private boolean provisional;
    private boolean online;

    Challenger(JSONObject challengerObject) {
        id = challengerObject.getString("id");
        name = challengerObject.getString("name");
        title = challengerObject.get("title");
        rating = challengerObject.getInt("rating");
        provisional = challengerObject.getBoolean("provisional");
        try {
            online = challengerObject.getBoolean("online");
        } catch (Exception ignored) {}
    }
}
