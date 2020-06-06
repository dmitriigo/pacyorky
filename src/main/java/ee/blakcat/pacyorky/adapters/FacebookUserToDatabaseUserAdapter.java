package ee.blakcat.pacyorky.adapters;

import com.restfb.types.User;
import ee.blakcat.pacyorky.models.FacebookUser;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class FacebookUserToDatabaseUserAdapter implements Adapter<User, FacebookUser> {
    @Override
    public FacebookUser convert(User user) {
        FacebookUser faceBookUser = new FacebookUser();
        faceBookUser.setId(user.getId());
        faceBookUser.setName(user.getName());
        return faceBookUser;
    }

    @Override
    public Collection<FacebookUser> convertAll(Collection<User> users) {
        return users.stream().map(this::convert).collect(Collectors.toSet());
    }
}
