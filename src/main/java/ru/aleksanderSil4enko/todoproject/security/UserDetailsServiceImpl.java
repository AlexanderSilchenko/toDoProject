package ru.aleksanderSil4enko.todoproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aleksanderSil4enko.todoproject.model.User;
import ru.aleksanderSil4enko.todoproject.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User does not exist!"));
        return SecurityUser.fromUser(user);
    }

    public boolean hasUserId(Authentication authentication, int userId) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        return user.getId() == userId;
    }
}
