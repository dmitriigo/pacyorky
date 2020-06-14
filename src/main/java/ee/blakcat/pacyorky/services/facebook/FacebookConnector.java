package ee.blakcat.pacyorky.services.facebook;

import com.restfb.types.User;

import java.util.Collection;
import java.util.Set;

public interface FacebookConnector<ENT> {

    ENT getOne(String id);

    Collection<ENT> getData();
}
