package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends JpaRepository<Invite, String> {
}
