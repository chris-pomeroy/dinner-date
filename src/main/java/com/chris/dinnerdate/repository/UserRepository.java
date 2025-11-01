package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findBySessionId(String sessionId);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE users u SET u.sessionId = NULL WHERE u.id = :userId")
    void clearSession(Long userId);

    @Modifying
    @Query("UPDATE users u SET u.lobbyId = :newLobbyId where u.id = :userId")
    void updateCurrentLobbyId(Long newLobbyId, Long userId);

    @Modifying
    @Query("UPDATE users u SET u.lobbyId = :newLobbyId WHERE u.id IN :userIds")
    void updateCurrentLobbyId(Long newLobbyId, List<Long> userIds);

    List<User> findByLobbyId(Long lobbyId);
}