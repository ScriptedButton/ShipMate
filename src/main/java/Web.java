/**
 * Created by 21brooksc on 5/30/2019.
 */
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Web {
    public static List<String> getTrackingInfo(TrackingNumber number) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            HttpResponse<String> req = Unirest.post("https://secure.shippingapis.com/ShippingAPI.dll")
                    .field("API", "TrackV2").field("XML",  "<TrackRequest USERID=\"994COLEB1165\">\n" +
                            "<TrackID ID=" + "\"" + number.getNumber() + "\"" + "></TrackID>\n" +
                            "</TrackRequest>").asString();
            Document document = builder.parse(new InputSource(new StringReader(req.getBody())));
            //System.out.println(req.getBody());
            NodeList summary = document.getDocumentElement().getElementsByTagName("TrackInfo").item(0).getChildNodes();
            List<String> trackingData = new ArrayList<String>();
            for (int i = 0; i < summary.getLength(); i++){
                System.out.println(summary.item(i).getTextContent());
                trackingData.add(summary.item(i).getTextContent());
            }
            return trackingData;
        }
        catch(Exception e){
            List<String> error = new ArrayList<String>();
            error.add("Error occured: " + e.getMessage());
            return error;
        }

    }
}
