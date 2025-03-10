package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Dislike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikeRepository extends CrudRepository<Dislike, Long> {
}
