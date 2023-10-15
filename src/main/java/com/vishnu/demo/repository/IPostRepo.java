package com.vishnu.demo.repository;


import com.vishnu.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Integer> {
}
