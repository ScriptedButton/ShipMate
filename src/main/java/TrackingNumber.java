import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

/**
 * Created by 21brooksc on 5/30/2019.
 */
public class TrackingNumber {
    private String trackingNumber;

    public TrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getNumber() {
        return this.trackingNumber;
    }

    public List<String> getTrackingInfo () {
        return Web.getTrackingInfo(this);
    }

}
