package com.chris.dinnerdate.repository;

import com.chris.dinnerdate.model.Swipe;
import com.chris.dinnerdate.model.SwipeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwipeRepository extends JpaRepository<Swipe, Long> {

    List<Swipe> findByUserIdAndSwipeType(Long userId, SwipeType swipeType, Pageable pageable);
}
