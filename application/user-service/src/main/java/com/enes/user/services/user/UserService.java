package com.enes.user.services.user;

import com.enes.user.entity.UserEntity;
import com.enes.user.exceptions.BusinessLogicException;
import com.enes.user.model.CreateUserRequest;
import com.enes.user.repository.UserRepository;
import com.enes.user.services.greeting.GreetingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final GreetingService greetingService;

    public UserService(UserRepository userRepository, GreetingService greetingService) {
        this.userRepository = userRepository;
        this.greetingService = greetingService;
    }

    public String create(CreateUserRequest request) {

        checkUserExists(request.getEmailAddress());
        UserEntity user = userRepository.save(new UserEntity(request));
        greetUser(request.getFirstName());

        return user.getId();
    }

    private void checkUserExists(String emailAddress) {

        Optional<UserEntity> userOpt = userRepository.findByEmailAddress(emailAddress);
        if (userOpt.isPresent()) {
            throw new BusinessLogicException(BusinessLogicException.ERROR.USER_ALREADY_EXISTS).addParameter("emailAddress", emailAddress);
        }
    }

    private void greetUser(String name) {
        greetingService.greetUser(name);
    }
}
