import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Created by k79689 on 10.01.17.
 */
public class Webclient {


    // Crap og hacky tjeneste, drop it og bruk Jersey /

    static ReadNorwegianPlayersFromJsonFile norwegianPlayas = new ReadNorwegianPlayersFromJsonFile();


    public static void main(String[]args) throws IOException {
//        List<Player> players =  norwegianPlayas.listNorwegianPlayers();
//        for (Player player : players){
//            System.out.println(player.toString());
//            break;
//        }



        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {


        @Override
        public void handle(HttpExchange t) throws IOException {

            String response = "";

            for (Player player : norwegianPlayas.listNorwegianPlayers()){
                response+=player.toString() +"\n";
                break;
            }


            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
