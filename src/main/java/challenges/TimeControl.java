package challenges;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

@Getter
@ToString
public class TimeControl {
    private String type;
    private int limit;
    private int incrementation;
    private int daysPerTurn;

    public TimeControl(JSONObject timeControl) {
        type = timeControl.getString("type");
        if (type.equalsIgnoreCase("correspondence")) {
            daysPerTurn = timeControl.getInt("daysPerTurn");
        } else if (!type.equalsIgnoreCase("unlimited")) {
            limit = timeControl.getInt("limit");
            incrementation = timeControl.getInt("increment");
        }
    }
}
