package ee.blakcat.pacyorky.adapters;

import com.restfb.types.Event;
import ee.blakcat.pacyorky.models.PacyorkyGroup;
import ee.blakcat.pacyorky.services.pacyorky.PacyorkyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class FacebookEventOwnerToPacyorkyEventOwnerAdapter implements Adapter<Event.Owner, PacyorkyGroup> {
    private final PacyorkyGroupService pacyorkyGroupService;

    @Autowired
    public FacebookEventOwnerToPacyorkyEventOwnerAdapter(PacyorkyGroupService pacyorkyGroupService) {
        this.pacyorkyGroupService = pacyorkyGroupService;
    }

    @Override
    public PacyorkyGroup convert(Event.Owner owner) {
        return pacyorkyGroupService.findOne(owner.getId());
    }

    @Override
    public Collection<PacyorkyGroup> convertAll(Collection<Event.Owner> owners) {
        return owners.stream().map(this::convert).collect(Collectors.toList());
    }
}
