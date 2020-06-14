package ee.blakcat.pacyorky.adapters;

import com.restfb.types.Event;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class FacebookEventOwnerToPacyorkyEventOwnerAdapter implements Adapter<Event.Owner, PacyorkyGroup> {
    @Override
    public PacyorkyGroup convert(Event.Owner owner) {
        PacyorkyGroup pacyorkyGroup = new PacyorkyGroup();
        pacyorkyGroup.setId(owner.getId());
        pacyorkyGroup.setName(owner.getName());
        return pacyorkyGroup;
    }

    @Override
    public Collection<PacyorkyGroup> convertAll(Collection<Event.Owner> owners) {
        return owners.stream().map(this::convert).collect(Collectors.toList());
    }
}
