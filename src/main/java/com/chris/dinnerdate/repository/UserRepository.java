package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findBySessionId(String sessionId);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.sessionId = NULL WHERE u.id = :userId")
    void clearSession(Long userId);
}
