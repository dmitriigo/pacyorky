package ee.blakcat.pacyorky.services.pacyorky;

import ee.blakcat.pacyorky.repositories.database.PacyorkyUserRepository;

public class UserServiceImpl implements UserService {
    private PacyorkyUserRepository pacyorkyUserRepository;

    public UserServiceImpl(PacyorkyUserRepository pacyorkyUserRepository) {
        this.pacyorkyUserRepository = pacyorkyUserRepository;
    }

    
}
