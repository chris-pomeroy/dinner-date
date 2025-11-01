package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Invite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends CrudRepository<Invite, String> {
}
