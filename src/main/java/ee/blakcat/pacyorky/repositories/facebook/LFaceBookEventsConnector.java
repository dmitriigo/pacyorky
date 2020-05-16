package ee.blakcat.pacyorky.repositories.facebook;

import com.restfb.FacebookClient;
import com.restfb.types.Event;

import java.util.List;

public interface LFaceBookEventsConnector {
    List<Event> getEventList(FacebookClient client);
}
