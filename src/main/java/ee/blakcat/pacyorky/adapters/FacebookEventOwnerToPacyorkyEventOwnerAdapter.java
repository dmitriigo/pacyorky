package ee.blakcat.pacyorky.adapters;

import com.restfb.types.Event;
import ee.blakcat.pacyorky.models.PacyorkyEventOwner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class FacebookEventOwnerToPacyorkyEventOwnerAdapter implements Adapter<Event.Owner, PacyorkyEventOwner> {
    @Override
    public PacyorkyEventOwner convert(Event.Owner owner) {
        return new PacyorkyEventOwner(owner.getName(), owner.getId());
    }

    @Override
    public Collection<PacyorkyEventOwner> convertAll(Collection<Event.Owner> owners) {
        return owners.stream().map(this::convert).collect(Collectors.toList());
    }
}
