package games;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

@Getter
@ToString
public class Variant {
    private String name;
    private String shortName;
    private String key;

    public Variant(JSONObject variantObject) {
        name = variantObject.getString("name");
        try {
            shortName = variantObject.getString("short");
        } catch (Exception ignored) {
        }
        key = variantObject.getString("key");
    }
}
