package service.user;

import org.springframework.boot.security.autoconfigure.SecurityProperties;
import org.springframework.stereotype.Service;
import repository.user.JpaUserRepository;

@Service
public class UserCreatorService {
    private final JpaUserRepository jpaUserRepository;

    public UserCreatorService(JpaUserRepository jpaUserRepository){
        this.jpaUserRepository = jpaUserRepository;
    }

    public User create(UserRequest request){
        return jpaUserRepository.save(request);
    }
}
