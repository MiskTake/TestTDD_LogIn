package com.socialapp.domain;

public interface UserRepository {
    boolean existsByEmail(String email);
    void save(User user);
    User findByEmail(String email);
}
