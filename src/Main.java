import java.io.BufferedReader;
import java.net.URL;
/**
 * Created by 21brooksc on 5/23/2019.
 */
public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://md5db.net/api/5d41402abc4b2a76b9719d911017c592");
            BufferedReader br = new BufferedReader(newInputStreamReader(url.openStream()));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                System.out.println(strTemp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
