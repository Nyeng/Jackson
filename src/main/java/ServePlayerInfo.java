import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by k79689 on 15.12.16.
 */
public class ServePlayerInfo {

    private ObjectMapper mapper = new ObjectMapper();

    private Player getPlayerInfo() throws IOException {
        return mapper.readValue(new URL(getApi_endpoint()), Player.class);
    }

    private String getApi_endpoint() {
        return "http://smashranking.eu/api/smashers/v-dogg-no/";
    }

    public static void main(String[] args) throws IOException {
        ServePlayerInfo playainfo = new ServePlayerInfo();

        System.out.println(playainfo.getPlayerInfo().toString());
        //System.out.println(playainfo.returnPlayerInfo());
    }

}

/**
 * Created by k79689 on 15.12.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Player {

    boolean active;
    String tag;
    String team;
    String name;
    String nationality;
    String country;
    String city;
    String region;
    String main;
    String secondary;
    String tertiary;
    String quaternary;
    String tournaments;
    Integer wins;
    Integer losses;
    Double ratio;
    Double mu;
    Double sigma;
    Double ts;
    String facebook;
    String twitter;
    String twitch;
    String youtube;
    Integer eurank;
    Integer country_rank;
    Integer character_rank;

    public Player() { }

    public String toString() {
        return "Name: " + getName() +
            "EU Rank: " + getEurank() +
            "National rank " + getCountry_rank();
    }

    public boolean isActive() {
        return active;
    }

    public String getTag() {
        return tag;
    }

    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getMain() {
        return main;
    }

    public String getSecondary() {
        return secondary;
    }

    public String getTertiary() {
        return tertiary;
    }

    public String getQuaternary() {
        return quaternary;
    }

    public String getTournaments() {
        return tournaments;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public Double getRatio() {
        return ratio;
    }

    public Double getMu() {
        return mu;
    }

    public Double getSigma() {
        return sigma;
    }

    public Double getTs() {
        return ts;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getTwitch() {
        return twitch;
    }

    public String getYoutube() {
        return youtube;
    }

    public Integer getEurank() {
        return eurank;
    }

    public Integer getCountry_rank() {
        return country_rank;
    }

    public Integer getCharacter_rank() {
        return character_rank;
    }


}

