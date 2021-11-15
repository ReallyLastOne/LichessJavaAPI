package utilities;

public class URLs {
    public static final String acceptChallenge = "https://lichess.org/api/challenge/{challengeId}/accept";
    public static final String challengeAI = "https://lichess.org/api/challenge/ai";
    public static final String eventStreamURL = "https://lichess.org/api/stream/event";
    public static final String makeAMoveURL = "https://lichess.org/api/board/game/{gameId}/move/{move}";
    public static final String challengesURI = "https://lichess.org/api/challenge";
    public static final String eventStream = "https://lichess.org/api/stream/event";
    public static final String responseExample = "{\"in\":[{\"id\":\"s911aLhJ\",\"url\":\"https://lichess.org/s911aLhJ\",\"status\":\"created\",\"challenger\":{\"id\":\"zubrtatra\",\"name\":\"ZubrTatra\",\"title\":null,\"rating\":1500,\"provisional\":true,\"online\":true},\"destUser\":{\"id\":\"centrallimit\",\"name\":\"centralLimit\",\"title\":\"BOT\",\"rating\":2000,\"provisional\":true},\"variant\":{\"key\":\"standard\",\"name\":\"Standard\",\"short\":\"Std\"},\"rated\":false,\"speed\":\"bullet\",\"timeControl\":{\"type\":\"clock\",\"limit\":30,\"increment\":0,\"show\":\"½+0\"},\"color\":\"white\",\"perf\":{\"icon\":\"\uE047\",\"name\":\"Bullet\"},\"direction\":\"in\"}],\"out\":[]}\n";
    public static final String ongoingGames = "https://lichess.org/api/account/playing";

    private URLs() {
        throw new AssertionError();
    }
}
