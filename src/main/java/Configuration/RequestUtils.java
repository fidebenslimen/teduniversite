package Configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestUtils {
    @Autowired
    private HttpServletRequest request;

    private static final String IP_SERVICE_URL = "https://api.ipify.org";

    public String getClientIpAddress() {
        try {
            URL url = new URL(IP_SERVICE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String ip = in.readLine();
            in.close();

            return ip;
        } catch (IOException e) {
            // Handle exception
        }

        return null;
    }

}
