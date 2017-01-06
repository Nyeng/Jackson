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
        ReadNorwegianPlayersFromJsonFile readJson = new ReadNorwegianPlayersFromJsonFile();
        readJson.readJson();

    }

    ObjectMapper mapper = new ObjectMapper();


    private void readJson() throws IOException {
        //convert players array of players
        //Player[] playerObjects = mapper.readValue(new File("norwegianplayers.json"),Player[].class);

        List<Player> myObjects = mapper.readValue(new File("norwegianplayers.json"), new TypeReference<List<Player>>(){});

        for (Player player : myObjects){
            System.out.println("\n" + player.tag);
        }

    }
}
