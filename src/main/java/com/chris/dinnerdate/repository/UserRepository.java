package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
