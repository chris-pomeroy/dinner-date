package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.LobbyMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LobbyMembershipRepository extends JpaRepository<LobbyMembership, Long> {
}
