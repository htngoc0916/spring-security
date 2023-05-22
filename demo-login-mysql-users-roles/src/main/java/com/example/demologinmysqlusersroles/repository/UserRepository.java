package com.example.demologinmysqlusersroles.repository;

import com.example.demologinmysqlusersroles.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
