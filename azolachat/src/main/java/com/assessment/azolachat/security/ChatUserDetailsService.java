package com.assessment.azolachat.security;

import com.assessment.azolachat.entity.User;
import com.assessment.azolachat.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ChatUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ChatUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Incorrect credentials provided for "+ username));
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority("ROLE_CHAT_USER"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
