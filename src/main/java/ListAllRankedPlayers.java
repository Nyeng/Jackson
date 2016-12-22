import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by k79689 on 16.12.16.
 */

public class ListAllRankedPlayers {

    private String getGeneralRankPath() {
        return "/ranking";
    }

    ServePlayerInfo playerInfo = new ServePlayerInfo();

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        ListAllRankedPlayers listOfPlayersClass = new ListAllRankedPlayers();

        List<RankingList> playersToBeModified = listOfPlayersClass.ranks();
        listOfPlayersClass.generateListOfNorwegianPlayers(playersToBeModified);
    }

    public List<RankingList> ranks() throws Exception {
        String json = playerInfo.consumeApi(getGeneralRankPath());
        return Arrays.asList(mapper.readValue(json,RankingList[].class));

//        Todo: keeping this for learning purposes
//        List<RankingList> players = jsonObjectsOfRanks.stream()
//            .filter(p -> p.getSlug().equals("armada"))
//            .collect(Collectors.toList());
    }


    public void generateListOfNorwegianPlayers(List<RankingList> playerRanks) throws Exception {
        ServePlayerInfo playerInfo = new ServePlayerInfo();


        //Lambda attempt
        Stream<RankingList> dude = playerRanks.stream()
            .filter(p -> p.getSlug().contains("Zorc"));

        System.out.println(dude.findFirst());

        int i = 0;
        for (RankingList player : playerRanks) {

            String playerJson = playerInfo.consumeApi("/smashers/"+ player.getSlug());
            Player smasher = playerInfo.returnPlayerObject(playerJson);

            if (smasher.getCountry() != null &&
                smasher.getCountry().equals("Norway")) {
                System.out.println(smasher.toString());
                i+=1;
            }
            if (i > 50) {
                break;
            }
        }
    }

    public void NorwegianPlayers(){
        String rankApi = getGeneralRankPath();
    }
}

class RankingList{

    public RankingList(){}

    public String toString(){
        return "\nName: " + getSlug() +
            "\nEurank: " + getEurank()
            ;
    }

    private int eurank;
    private String slug;

    private int getEurank() {
        return eurank;
    }

    public String getSlug() {return slug;    }

    public void setEurank(int eurank) {this.eurank = eurank;}

    public void setSlug(String slug) { this.slug = slug; }
}
