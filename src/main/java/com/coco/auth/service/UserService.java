package com.coco.auth.service;

import com.coco.auth.model.User;
import com.coco.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("USER.NO_SINGED_IN_USER"));
//        LoginUser loginUser = user.toLoginUser(buildUserAuthority());
//
//        log.debug("loginUser: {}", loginUser);
//        return loginUser;
//
//        return null;
    }
}
