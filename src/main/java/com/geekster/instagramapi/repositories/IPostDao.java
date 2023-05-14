package com.geekster.instagramapi.repositories;

import com.geekster.instagramapi.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostDao extends CrudRepository<Post,Long> {

}
