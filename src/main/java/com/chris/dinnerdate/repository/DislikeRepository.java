package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Dislike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DislikeRepository extends CrudRepository<Dislike, Long> {

    List<Dislike> findBy(Pageable pageable);
}
