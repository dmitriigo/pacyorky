package ee.blakcat.pacyorky.repositories.facebook;

import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LFaceBookEventsConnectorImpl implements LFaceBookEventsConnector {
    public List<Event> getEventList(FacebookClient client){
        return new ArrayList<>(client.fetchConnection("me/events", Event.class,
                Parameter.with("fields",
                        "cover,description,end_time,name,owner,place,start_time"
                )).getData());
    }
}
