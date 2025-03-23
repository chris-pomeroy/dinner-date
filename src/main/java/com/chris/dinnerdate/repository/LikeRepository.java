package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Like;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {

    public List<Like> findBy(Pageable pageable);
}
