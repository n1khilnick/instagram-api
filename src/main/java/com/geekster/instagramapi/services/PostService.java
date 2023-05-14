package com.geekster.instagramapi.services;

import com.geekster.instagramapi.models.Post;
import com.geekster.instagramapi.models.User;
import com.geekster.instagramapi.repositories.IPostDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    IPostDao postDao;

    @Autowired
    UserService userService;


    public void savePost(Post post) {
        postDao.save(post);
    }

    public Iterable<Post> fetchAllPosts() {

        return postDao.findAll();
    }


}
