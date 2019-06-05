
import javax.swing.*;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import java.beans.XMLEncoder;

/**
 * Created by 21brooksc on 5/23/2019.
 */

public class Test {
    public static void main(String[] args) throws UnirestException, ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        HttpResponse<String> req = Unirest.post("https://secure.shippingapis.com/ShippingAPI.dll")
                .field("API", "TrackV2").field("XML",  "<TrackRequest USERID=\"994COLEB1165\">\n" +
                        "<TrackID ID=\"9410809699938463850777\"></TrackID>\n" +
                        "</TrackRequest>").asString();
        Document document = builder.parse(new InputSource(new StringReader(req.getBody())));
        System.out.println(req.getBody());
        NodeList summary = document.getDocumentElement().getElementsByTagName("TrackInfo").item(0).getChildNodes();
        for (int i = 0; i < summary.getLength(); i++){
            System.out.println(summary.item(i).getTextContent());
        }
        System.out.println(summary);
    }
}
