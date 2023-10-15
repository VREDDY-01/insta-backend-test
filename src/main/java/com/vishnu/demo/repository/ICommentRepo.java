package com.vishnu.demo.repository;


import com.vishnu.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment,Integer> {
}
