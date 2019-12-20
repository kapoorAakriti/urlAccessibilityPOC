import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class responsiveURL {
    String error404=" No";
    String accessible="Accessible";
    HttpURLConnection huc = null;
    int respCode = 200;
    public String responcive(String url) throws IOException {

        huc = (HttpURLConnection)(new URL(url).openConnection());
        huc.setRequestMethod("HEAD");
        huc.connect();
        respCode = huc.getResponseCode();

        if(respCode >= 400 ){
            return error404;
        }
        else{
           return accessible;
        }
    }


}
