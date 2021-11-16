import board.BoardOrganizer;
import org.apache.http.auth.AuthenticationException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, AuthenticationException {
       //System.out.println(ChallengesOrganizer.extractChallengesList());
       // System.out.println(GamesOrganizer.extractOngoingGames());
        BoardOrganizer.streamIncomingEvents();
    }
}
