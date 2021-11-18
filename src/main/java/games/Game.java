package games;

import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

@Getter
@ToString
public
class Game {
    private String fullId;
    private String gameId;
    private String fen;
    private String color;
    private String lastMove;
    private Variant variant;
    private String perf;
    private String speed;
    private boolean rated;
    private boolean hasMoved;
    private Opponent opponent;
    private boolean isMyTurn;
    private int secondsLeft;

    Game(JSONObject gameObject) {
        fullId = gameObject.getString("fullId");
        gameId = gameObject.getString("gameId");
        fen = gameObject.getString("fen");
        color = gameObject.getString("color");
        lastMove = gameObject.getString("lastMove");
        variant = new Variant(gameObject.getJSONObject("variant"));
        perf = gameObject.getString("perf");
        speed = gameObject.getString("speed");
        rated = gameObject.getBoolean("rated");
        hasMoved = gameObject.getBoolean("hasMoved");
        opponent = new Opponent(gameObject.getJSONObject("opponent"));
        isMyTurn = gameObject.getBoolean("isMyTurn");
        if (!speed.equalsIgnoreCase("correspondence")) {
            secondsLeft = gameObject.getInt("secondsLeft");
        }
    }
}
