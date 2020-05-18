package ee.blakcat.pacyorky.services.facebook;

import java.util.Collection;

public interface FacebookConnector<ENT> {
    ENT getOne();

    Collection<ENT> getData();
}
