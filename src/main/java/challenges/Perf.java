package challenges;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

@ToString
@Getter
public class Perf {
    private String icon;
    private String name;

    public Perf(JSONObject perfObject) {
        icon = perfObject.getString("icon");
        name = perfObject.getString("name");
    }
}
