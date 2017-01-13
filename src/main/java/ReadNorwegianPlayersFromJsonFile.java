import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by k79689 on 06.01.17.
 */
public class ReadNorwegianPlayersFromJsonFile {

    static ObjectMapper mapper;

    public ReadNorwegianPlayersFromJsonFile(){}

    public static void main(String[]args) throws IOException {
        ReadNorwegianPlayersFromJsonFile norwegianPlayas = new ReadNorwegianPlayersFromJsonFile();

        mapper = new ObjectMapper();
        List<Player> players = mapper.readValue(new File("norwegianplayers.json"), new TypeReference<List<Player>>(){});


        norwegianPlayas.searchForPlayer(players,"Zorc");

        Player player = norwegianPlayas.searchForPlayerUsingStreams(players,"Zorc");
        System.out.println(player);

    }

    public List<Player> listNorwegianPlayers() throws IOException {
        mapper = new ObjectMapper();
        return mapper.readValue(new File("norwegianplayers.json"), new TypeReference<List<Player>>(){});
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
                System.out.println("Found player with tag(s): "+player.getName());
            }
        }
    }


}
