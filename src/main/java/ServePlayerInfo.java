import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by k79689 on 15.12.16.
 */
public class ServePlayerInfo {

    String api_endpoint = "http://smashranking.eu/api/smashers/v-dogg-no/";

    public String getApi_endpoint() {
        return api_endpoint;
    }


    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ServePlayerInfo playainfo = new ServePlayerInfo();
        Player playa = new Player();


      //  Staff obj = mapper.readValue(new URL("http://mkyong.com/api/staff.json"), Staff.class);
        Player object = mapper.readValue(new URL(playainfo.getApi_endpoint()), Player.class);
        System.out.println(object.getName());
    }


    public String getAllinfo(String json){

        return "";
    }


}
