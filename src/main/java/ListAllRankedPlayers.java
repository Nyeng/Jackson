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

        List<RankedEuropeanPlayers> playersToBeModified = listOfPlayersClass.getEuropeanRankedPlayers();
        listOfPlayersClass.generateListOfNorwegianPlayers(playersToBeModified);
    }

    public List<RankedEuropeanPlayers> getEuropeanRankedPlayers() throws Exception {
        String json = playerInfo.consumeApi(getGeneralRankPath());
        return Arrays.asList(mapper.readValue(json, RankedEuropeanPlayers[].class));

//        Todo: keeping this for learning purposes
//        List<RankedEuropeanPlayers> players = jsonObjectsOfRanks.stream()
//            .filter(p -> p.getSlug().equals("armada"))
//            .collect(Collectors.toList());
    }

    public ArrayList generateListOfNorwegianPlayers(List<RankedEuropeanPlayers> playerRanks) throws Exception {
        ServePlayerInfo playerInfo = new ServePlayerInfo();
        List<Player> playerObjects = new ArrayList<>();

        int i = 0;


        for (RankedEuropeanPlayers player : playerRanks) {

            String playerJson = playerInfo.consumeApi("/smashers/" + player.getSlug());
            Player smasher = playerInfo.returnPlayerObject(playerJson);

            if (smasher.getCountry() != null &&
                smasher.getCountry().equals("Norway")) {
                System.out.println(smasher.toString());
                playerObjects.add(smasher);

                //Save json to file
                // add to list, then save to file
                i += 1;
            }
            if (i > 3) {
                break;
            }
        }

        return (ArrayList) playerObjects;
    }

    private String saveToFile(String playerJson) {
        playerJson += "," +
        "players.{"
            + "Player{}, \n"
            + "Player{} \n";

        return playerJson;
    }

    private class NorwegianPlayers{

        ArrayList<Player> players;

        public NorwegianPlayers(){
            players = new ArrayList<>();
        }

        public void addPlayer(ArrayList<Player> players){
            Player player = new Player();
            players.add(player);
        }

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
