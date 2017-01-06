import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by k79689 on 06.01.17.
 */
public class ReadNorwegianPlayersFromJsonFile {

    public ReadNorwegianPlayersFromJsonFile(){}

    public static void main(String[]args) throws IOException {
        ReadNorwegianPlayersFromJsonFile norwegianPlayas = new ReadNorwegianPlayersFromJsonFile();

        ObjectMapper mapper = new ObjectMapper();
        List<Player> players = mapper.readValue(new File("norwegianplayers.json"), new TypeReference<List<Player>>(){});

        long start_time = System.currentTimeMillis();

        norwegianPlayas.searchForPlayer(players,"Zorc");

        long endTimeForLoop = System.currentTimeMillis();

        Player player = norwegianPlayas.searchForPlayerUsingStreams(players,"Zorc");
        System.out.println(player);

        long endStream = System.currentTimeMillis();

        System.out.println("For loop time taken: " + (endTimeForLoop - start_time));
        System.out.println("Stream time taken: " + (endStream - endTimeForLoop));
    }

    private Player searchForPlayerUsingStreams(List<Player> players, String zorc) {
        System.out.println("Find Zorc using streams: ");

        Player result = players.stream().
            filter(x -> x.tag.contains(zorc))
            .findAny()
            .orElse(null);

        return result;
    }

    public void searchForPlayer(List<Player> players, String searchTerm){
        for (Player player : players){
            if (player.tag.contains(searchTerm)){
                System.out.println("Found player with tag(s): "+player.toString());
            }
        }

    }


}
