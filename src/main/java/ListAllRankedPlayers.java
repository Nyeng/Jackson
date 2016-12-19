import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by k79689 on 16.12.16.
 */

public class ListAllRankedPlayers {

    private String getApi_endpoint() {
        return "http://smashranking.eu/api/ranking/";
    }

    private static ObjectMapper mapper = new ObjectMapper();

    public String getJsonFromUrl(String apiEndpoint) throws IOException {
        return new JSONArray(IOUtils.toString(new URL(apiEndpoint), Charset.forName("UTF-8"))).toString();
    }

    public static void main(String[] args) throws IOException {
        ListAllRankedPlayers listOfPlayersClass = new ListAllRankedPlayers();
        List<RankingList> playersToBeModified = listOfPlayersClass.ranks();
        listOfPlayersClass.getCountryDataForEachPlayer(playersToBeModified);

        listOfPlayersClass.generateListOfNorwegianPlayers(playersToBeModified);



    }

    public List<RankingList> ranks() throws IOException {
        String jsonArray = getJsonFromUrl(getApi_endpoint());
        List<RankingList> jsonObjectsOfRanks = Arrays.asList(mapper.readValue(jsonArray, RankingList[].class));

        //Todo: keeping this for learning purposes
//        List<RankingList> players = jsonObjectsOfRanks.stream()
//            .filter(p -> p.getSlug().equals("armada"))
//            .collect(Collectors.toList());

        return jsonObjectsOfRanks;
    }

    public void getCountryDataForEachPlayer(List<RankingList> rankedPlayers){

//        for(RankingList player : rankedPlayers){
//            System.out.println(player.getSlug());
//        }

    }

    public void generateListOfNorwegianPlayers(List<RankingList> playerRanks) throws IOException {
        ServePlayerInfo playerInfo = new ServePlayerInfo();


        int i = 0;
        for(RankingList player : playerRanks){
            //System.out.println(player.getSlug());

            //playerInfo.consumePlayer("R3D");

            if (playerInfo.consumePlayer(player.getSlug()).getCountry().equals("Norway")){
                System.out.println("Player: "+ player.getSlug());
            }
            i += 1;
            if (i > 2000){
                break;
            }
            System.out.println(i);
        }
// 487


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
