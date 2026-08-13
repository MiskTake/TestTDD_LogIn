package com.socialapp.domain.repository;

import com.socialapp.domain.entity.User;
import com.socialapp.domain.valueobject.Email;

public interface UserRepository {
    boolean existsByEmail(Email email);
    void save(User user);
    User findByEmail(Email email);
}
