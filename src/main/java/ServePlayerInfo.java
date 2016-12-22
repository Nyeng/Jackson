import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by k79689 on 15.12.16.
 */
public class ServePlayerInfo {

    private ObjectMapper mapper = new ObjectMapper();

    public String consumeApi(String path) throws Exception {
        String responseBody;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(getBaseApiEndpoint() + path);

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException(
                        "Unexpected response status: " + status + "for path: " + path +
                    "\n whole path:" + getBaseApiEndpoint() + path);
                }
            };
            responseBody = httpclient.execute(httpget, responseHandler);
        }
        return responseBody;
    }

    public Player returnPlayerObject(String json) throws IOException {
        return mapper.readValue(json,Player.class);
    }
    public String getBaseApiEndpoint(){
        return "http://smashranking.eu/api";
    }
}

/**
 * Created by k79689 on 15.12.16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonSerialize.Inclusion.NON_NULL) // or JsonSerialize.Inclusion.NON_EMPTY
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

    @Override
    public String toString() {
        return "{ Tag:'" + tag + "{" +
            "active:" + active +
            ", team:'" + team + '\'' +
            ", name:'" + name + '\'' +
            ", nationality:'" + nationality + '\'' +
            ", country:'" + country + '\'' +
            ", city:'" + city + '\'' +
            ", region:'" + region + '\'' +
            ", main:'" + main + '\'' +
            ", secondary:'" + secondary + '\'' +
            ", tertiary:'" + tertiary + '\'' +
            ", quaternary:'" + quaternary + '\'' +
            ", tournaments:'" + tournaments + '\'' +
            ", wins=" + wins +
            ", losses=" + losses +
            ", ratio=" + ratio +
            ", mu=" + mu +
            ", sigma=" + sigma +
            ", ts=" + ts +
            ", facebook:'" + facebook + '\'' +
            ", twitter:'" + twitter + '\'' +
            ", twitch:'" + twitch + '\'' +
            ", youtube:'" + youtube + '\'' +
            ", eurank: '" + eurank +
            ", country_rank: '" + country_rank +
            ", character_rank: '" + character_rank +
            '}'
            ;
    }

    public Player() { }

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

    public String getCountry() {return country;   }

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

