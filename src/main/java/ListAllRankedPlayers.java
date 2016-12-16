import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by k79689 on 16.12.16.
 */


//List<MyClass> myObjects = Arrays.asList(mapper.readValue(json, MyClass[].class))

public class ListAllRankedPlayers {
    private static ObjectMapper mapper = new ObjectMapper();

    public String getJsonFromUrl(String apiEndpoint) throws IOException {
        return new JSONArray(IOUtils.toString(new URL(apiEndpoint), Charset.forName("UTF-8"))).toString();
    }

    public static void main(String[] args) throws IOException {
        ListAllRankedPlayers listOfPlayersClass = new ListAllRankedPlayers();
        String jsonArray = listOfPlayersClass.getJsonFromUrl(listOfPlayersClass.getApi_endpoint());

        System.out.println(jsonArray);
        List<RankingList> objectsOfRanks = Arrays.asList(mapper.readValue(jsonArray, RankingList[].class));

//        Stream<RankingList> players = objectsOfRanks.stream()
//            .filter(p -> p.getSlug().equals("armada"));
//        System.out.println(players.toString());

        for (RankingList playah : objectsOfRanks){
            if (playah.getSlug().equals("ice")){
                System.out.println(playah.toString());
            }
        }

        List<RankingList> figureheads = objectsOfRanks.stream()
            .filter("armada"::equals)
            .collect(Collectors.toList());

        System.out.println(figureheads.toString());

        //.collect(Collectors.toList());

    }


    private String getApi_endpoint() {
        return "http://smashranking.eu/api/ranking/";
    }
}


class RankingList{

    public RankingList(){}

    public String toString(){
        return "Name: " + getSlug() +
            "\nEurank: " + getEurank()
            ;
    }

    int eurank;
    String slug;

    public int getEurank() {
        return eurank;
    }

    public String getSlug() {
        return slug;
    }
}
