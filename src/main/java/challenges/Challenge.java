package challenges;

import games.Variant;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

@Getter
@ToString
class Challenge {
    private boolean rated;
    private String color;
    private Variant variant;
    private Challenger challenger;
    private TimeControl timeControl;
    private String id;
    private Perf perf;
    private String url;
    private Challenger destUser;
    private String speed;
    private String status;
    private String direction;

    Challenge(JSONObject challengeObject) {
        rated = challengeObject.getBoolean("rated");
        color = challengeObject.getString("color");
        variant = new Variant(challengeObject.getJSONObject("variant"));
        challenger = new Challenger(challengeObject.getJSONObject("challenger"));
        timeControl = new TimeControl(challengeObject.getJSONObject("timeControl"));
        id = challengeObject.getString("id");
        perf = new Perf(challengeObject.getJSONObject("perf"));
        url = challengeObject.getString("url");
        destUser = new Challenger(challengeObject.getJSONObject("destUser"));
        speed = challengeObject.getString("speed");
        status = challengeObject.getString("status");
        direction = challengeObject.getString("direction");
    }
}
