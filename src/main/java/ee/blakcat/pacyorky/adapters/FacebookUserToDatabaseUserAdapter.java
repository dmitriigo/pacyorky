package ee.blakcat.pacyorky.adapters;

import com.restfb.types.User;
import ee.blakcat.pacyorky.models.FaceBookUser;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class FacebookUserToDatabaseUserAdapter implements Adapter<User, FaceBookUser> {
    @Override
    public FaceBookUser convert(User user) {
        FaceBookUser faceBookUser = new FaceBookUser();
        faceBookUser.setId(user.getId());
        faceBookUser.setName(user.getName());
        return faceBookUser;
    }

    @Override
    public Collection<FaceBookUser> convertAll(Collection<User> users) {
        return users.stream().map(this::convert).collect(Collectors.toSet());
    }
}
