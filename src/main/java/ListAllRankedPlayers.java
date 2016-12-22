import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by k79689 on 16.12.16.
 */

public class ListAllRankedPlayers {

    public ListAllRankedPlayers() {}

    private String getGeneralRankPath() {
        return "/ranking";
    }

    private ServePlayerInfo playerInfo = new ServePlayerInfo();

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        ListAllRankedPlayers listOfPlayersClass = new ListAllRankedPlayers();
        List<RankedEuropeanPlayers> europeanPlayers = listOfPlayersClass.getEuropeanRankedPlayers();
        List<Player> norwegianRankedPlayers = listOfPlayersClass.fetchNorwegiansFromEuroRank(europeanPlayers,50);

        //Read all Norwegian players to json file
        mapper.writeValue(new File("norwegianplayers.json"), norwegianRankedPlayers);
        //NorwegianPlayers norwegianPlayers = new NorwegianPlayers()
    }


    private List<RankedEuropeanPlayers> getEuropeanRankedPlayers() throws Exception {
        String json = playerInfo.consumeApi(getGeneralRankPath());
        return Arrays.asList(mapper.readValue(json, RankedEuropeanPlayers[].class));
    }

    private List<Player> fetchNorwegiansFromEuroRank(List<RankedEuropeanPlayers> playerRanks,int numberOfPlayers) throws Exception {
        ServePlayerInfo playerInfo = new ServePlayerInfo();
        List<Player> playerObjects = new ArrayList<>();

        int i = 0;

        for (RankedEuropeanPlayers player : playerRanks) {

            String playerJson = playerInfo.consumeApi("/smashers/" + player.getSlug());
            Player smasher = playerInfo.returnPlayerObject(playerJson);

            if (smasher.getCountry() != null &&
                smasher.getCountry().equals("Norway")) {
                playerObjects.add(smasher);

                i += 1;
            }
            if (i > numberOfPlayers) {
                break;
            }
        }
        return playerObjects;
    }
}

class RankedEuropeanPlayers {

    public RankedEuropeanPlayers() {}

    public String toString() {
        return "\nName: " + getSlug() +
            "\nEurank: " + getEurank()
            ;
    }

    private int eurank;
    private String slug;

    private int getEurank() {
        return eurank;
    }

    public String getSlug() {return slug; }

    public void setEurank(int eurank) {this.eurank = eurank;}

    public void setSlug(String slug) { this.slug = slug; }
}
