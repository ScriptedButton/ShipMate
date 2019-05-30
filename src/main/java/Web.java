/**
 * Created by 21brooksc on 5/30/2019.
 */
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;

public class Web {
    public static String getTrackingInfo(TrackingNumber number) throws UnirestException{
        System.out.println("<TrackRequest USERID=\"994COLEB1165\">\n" +
                "<TrackID ID=" + number.getNumber() + "></TrackID>\n" +
                "</TrackRequest>");
        HttpResponse<String> req = Unirest.post("https://secure.shippingapis.com/ShippingAPI.dll")
                .field("API", "TrackV2").field("XML",  "<TrackRequest USERID=\"994COLEB1165\">\n" +
                        "<TrackID ID=" + "\"" + number.getNumber() + "\"" + "></TrackID>\n" +
                        "</TrackRequest>").asString();
        return req.getBody();
    }
}
