package com.vishnu.demo.repository;


import com.vishnu.demo.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ILikeRepo extends JpaRepository<Like,Integer> {


}
