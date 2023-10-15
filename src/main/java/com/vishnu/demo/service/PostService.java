package com.vishnu.demo.service;



import com.vishnu.demo.model.Post;
import com.vishnu.demo.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;


    public void createInstaPost(Post instaPost) {
        postRepo.save(instaPost);
    }

    public List<Post> getPosts() {
      return  postRepo.findAll();

    }
}
