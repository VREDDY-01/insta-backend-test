package com.vishnu.demo.repository;


import com.vishnu.demo.model.Follow;
import com.vishnu.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFollowRepo extends JpaRepository<Follow,Integer> {
    List<Follow> findByCurrentUserAndCurrentUserFollower(User targetUser, User follower);
}
