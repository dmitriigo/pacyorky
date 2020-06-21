package ee.blakcat.pacyorky.services.pacyorky.servicesimpl;

import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.services.pacyorky.LocationPointService;
import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.request.NominatimSearchRequest;
import fr.dudie.nominatim.model.Address;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class LocationPointServiceImpl implements LocationPointService {
    private final Logger logger = LoggerFactory.getLogger(LocationPointServiceImpl.class);
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
            logger.warn("can't updateLocationPoint for pacyorkyEvent id: " + pacyorkyEvent.getId() + ", exception: " + e.toString());
            pacyorkyEvent.setLng(0);
            pacyorkyEvent.setLat(0);
        }
        return pacyorkyEvent;
    }
}
