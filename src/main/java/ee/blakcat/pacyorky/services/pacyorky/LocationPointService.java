package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.request.NominatimSearchRequest;
import fr.dudie.nominatim.model.Address;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class LocationPointService {

    public PacyorkyEvent updateLocationPoint(PacyorkyEvent pacyorkyEvent) {
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
        }
        return pacyorkyEvent;
    }
}
