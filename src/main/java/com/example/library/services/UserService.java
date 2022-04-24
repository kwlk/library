package com.example.library.services;

import com.example.library.security.UserPrincipal;
import com.example.library.beans.User;
import com.example.library.enums.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements UserDetailsService{
    Map<String, User> users = Map.of(
            "test", new User("test", "test"),
            "user", new User("user", "password"),
            "user2", new User("user2", "1234"),
            "admin", new User("admin", "password", Role.ADMIN)
            );

    public User getUser(String username) {
        if (users.containsKey(username)) {
            System.out.println("users contains " + username);
            return users.get(username);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in load user by username = " + username);
        User user = getUser(username);
        if (user == null) {
            System.out.println("username not found");
            throw new UsernameNotFoundException(username);
        }
        System.out.println("found user:\n" + user);
        return new UserPrincipal(user);
    }
}
