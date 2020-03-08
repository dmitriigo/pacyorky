package ee.blakcat.pacyorky.repositories.facebook;

import com.restfb.types.Account;
import com.restfb.types.Event;
import com.restfb.types.Place;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.models.PacyorkyEventOwner;


public class AdapterToEvent {


    public static PacyorkyEvent convertFaceBookEventToPacyorkyEvent(Event event, Account account) {
        if (event.getPlace() == null) {
            Place place = new Place();
            place.setName("");
            event.setPlace(place);
        }
        return new PacyorkyEvent(
                event.getPlace().getName(),
                event.getDescription(),
                event.getName(),
                event.getId(),
                event.getStartTime(),
                event.getEndTime(),
                convertFaceBookAccountToPacyorkyOwner(account)
        );
    }

    public static PacyorkyEventOwner convertFaceBookOwnerToPacyorkyOwner(Event.Owner owner) {
        return new PacyorkyEventOwner(owner.getName(), owner.getId());
    }

    public static PacyorkyEventOwner convertFaceBookAccountToPacyorkyOwner(Account account) {
        return new PacyorkyEventOwner(account.getName(), account.getId());
    }
}
