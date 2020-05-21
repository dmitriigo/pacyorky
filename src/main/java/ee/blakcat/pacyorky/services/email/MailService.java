package ee.blakcat.pacyorky.services.email;

import java.util.Set;

public interface MailService <ENT> {
    void updateTask(Set<ENT> events);
}
