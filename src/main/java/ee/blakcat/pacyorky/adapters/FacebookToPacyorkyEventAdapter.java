package ee.blakcat.pacyorky.adapters;

import com.restfb.types.CoverPhoto;
import com.restfb.types.Event;
import com.restfb.types.Place;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.models.PacyorkyEventOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class FacebookToPacyorkyEventAdapter implements Adapter<Event, PacyorkyEvent> {

    final Adapter<Event.Owner, PacyorkyEventOwner> pacyorkyEventOwnerAdapter;

    @Autowired
    public FacebookToPacyorkyEventAdapter(Adapter<Event.Owner, PacyorkyEventOwner> pacyorkyEventOwnerAdapter) {
        this.pacyorkyEventOwnerAdapter = pacyorkyEventOwnerAdapter;
    }

    @Override
    public PacyorkyEvent convert(Event event) {
        if (event.getPlace() == null) {
            Place place = new Place();
            place.setName("");
            event.setPlace(place);
        }
        if (event.getCover() == null) {
            CoverPhoto coverPhoto = new CoverPhoto();
            coverPhoto.setSource("");
            event.setCover(coverPhoto);
        }
        return new PacyorkyEvent(
                event.getPlace().getName(),
                event.getDescription(),
                event.getName(),
                event.getId(),
                event.getStartTime(),
                event.getEndTime(),
                pacyorkyEventOwnerAdapter.convert(event.getOwner()),
                event.getCover().getSource()
        );
    }

    @Override
    public Collection<PacyorkyEvent> convertAll(Collection<Event> events) {
        return events.stream().map(this::convert).collect(Collectors.toList());
    }
}
