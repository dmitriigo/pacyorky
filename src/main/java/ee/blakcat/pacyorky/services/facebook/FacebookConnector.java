package ee.blakcat.pacyorky.services.facebook;

import java.util.Collection;
import java.util.Set;

public interface FacebookConnector<ENT> {

    Collection<ENT> getData();
}
