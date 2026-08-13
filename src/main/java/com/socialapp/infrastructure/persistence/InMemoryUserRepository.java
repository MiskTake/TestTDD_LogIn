package com.socialapp.infrastructure.persistence;

import com.socialapp.domain.entity.User;
import com.socialapp.domain.exception.UserNotFoundException;
import com.socialapp.domain.repository.UserRepository;
import com.socialapp.domain.valueobject.Email;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

    private final Map<Email, User> users = new ConcurrentHashMap<>();

    @Override
    public boolean existsByEmail(Email email) {
        return users.containsKey(email);
    }

    @Override
    public void save(User user) {
        users.put(user.email(), user);
    }

    @Override
    public User findByEmail(Email email) {
        User user = users.get(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
}
