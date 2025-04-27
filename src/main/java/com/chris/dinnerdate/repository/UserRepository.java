package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findBySessionId(String sessionId);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.sessionId = NULL WHERE u.id = :userId")
    void clearSession(Long userId);

    @Modifying
    @Query("UPDATE User u SET u.currentLobbyId = :currentLobbyId where u.id = :id")
    void updateCurrentLobbyIdById(Long currentLobbyId, Long id);
}
