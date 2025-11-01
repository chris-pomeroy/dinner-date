package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Lobby;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LobbyRepository extends CrudRepository<Lobby, Long> {
}
