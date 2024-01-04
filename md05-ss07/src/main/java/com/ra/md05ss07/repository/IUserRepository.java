package com.ra.md05ss07.repository;

import com.ra.md05ss07.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    Boolean existsByUserName(String userName);
}
