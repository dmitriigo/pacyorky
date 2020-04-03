package ee.blakcat.pacyorky.services.updateData;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.repositories.database.EventRepositoryJPA;
import ee.blakcat.pacyorky.repositories.facebook.FaceBookConnector;
import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.request.NominatimSearchRequest;
import fr.dudie.nominatim.model.Address;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UpdateService {
    private EventRepositoryJPA eventRepositoryJPA;
    private FaceBookConnector faceBookConnector;

    @Autowired
    public UpdateService(EventRepositoryJPA eventRepositoryJPA, FaceBookConnector faceBookConnector) {
        this.eventRepositoryJPA = eventRepositoryJPA;
        this.faceBookConnector = faceBookConnector;
    }

    public void updateAll() {
        updateEvents();
    }

    private void updateEvents() {

        List<PacyorkyEvent> pacyorkyEvents = faceBookConnector.getPacyorkyEvents();
        List<PacyorkyEvent> eventsAtDB = eventRepositoryJPA.findAll();
        List<String> eventsIDs = eventsAtDB.stream().map(PacyorkyEvent::getId).collect(Collectors.toList());
        pacyorkyEvents.forEach(pacyorkyEvent -> {
            if (!eventsIDs.contains(pacyorkyEvent.getId())) updateLocationPoint(pacyorkyEvent);
            else {
                PacyorkyEvent eventAtDB = eventRepositoryJPA.findById(pacyorkyEvent.getId()).orElseThrow(RuntimeException::new);
                pacyorkyEvent.setLat(eventAtDB.getLat());
                pacyorkyEvent.setLng(eventAtDB.getLng());
                if (!eventAtDB.getPlace().equals(pacyorkyEvent.getPlace())) {
                    updateLocationPoint(pacyorkyEvent);
                }
            }
       });
        eventRepositoryJPA.saveAll(pacyorkyEvents);
    }
    private void updateLocationPoint (PacyorkyEvent pacyorkyEvent) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("c77d1809783f493b84914729c7d476e4");
        HttpClient httpClient = HttpClientBuilder.create().build();
        JsonNominatimClient jsonNominatimClient = new JsonNominatimClient(httpClient, "info@pacyorky.ee");
        NominatimSearchRequest nominatimSearchRequest = new NominatimSearchRequest();
            try {
                nominatimSearchRequest.setQuery(pacyorkyEvent.getPlace());
                Address tempaddress = jsonNominatimClient.search(nominatimSearchRequest).get(0);
                pacyorkyEvent.setLat(tempaddress.getLatitude());
                pacyorkyEvent.setLng(tempaddress.getLongitude());
            } catch (Exception e) {
                pacyorkyEvent.setLng(0);
                pacyorkyEvent.setLat(0);
                /*try {
                    JOpenCageForwardRequest request = new JOpenCageForwardRequest(pacyorkyEvent.getPlace());
                    JOpenCageResponse response = jOpenCageGeocoder.forward(request);
                    pacyorkyEvent.setLat(response.getFirstPosition().getLat());
                    pacyorkyEvent.setLng(response.getFirstPosition().getLng());
                } catch (Exception n) {
                    pacyorkyEvent.setLng(0);
                    pacyorkyEvent.setLat(0);
                }*/
            }
    }
}
