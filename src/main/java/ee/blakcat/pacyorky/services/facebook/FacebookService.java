package ee.blakcat.pacyorky.services.facebook;

import java.util.Set;

public interface FacebookService<ENT> {
    Set<ENT> getAllowedData();
}
