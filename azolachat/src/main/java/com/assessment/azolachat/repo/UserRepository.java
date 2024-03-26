package com.assessment.azolachat.repo;

import com.assessment.azolachat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select usr from User usr where usr.username = :username")
    Optional<User> findByUsername(String username);
}
