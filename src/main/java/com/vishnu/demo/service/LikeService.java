package com.vishnu.demo.service;



import com.vishnu.demo.repository.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    ILikeRepo likeRepo;




}
