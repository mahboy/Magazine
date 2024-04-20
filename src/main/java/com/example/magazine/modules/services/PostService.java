package com.example.magazine.modules.services;

import com.example.magazine.modules.entity.Posts;
import com.example.magazine.modules.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepo postRepo;

    @Autowired
    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    //make a post and some code for upload url photo in database
    @Transactional
    public Posts savePost(Posts posts)  {
        String path= null;
        try {
            path = ResourceUtils.getFile("classpath:static/images/me/").getAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = new byte[0];
        try {
            bytes = posts.getFile().getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String name = UUID.randomUUID() + "." + Objects.requireNonNull(posts.getFile().getContentType()).split("/")[1];
        try {
            Files.write(Paths.get(path + File.separator + name), bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        posts.setPhoto(name);
        return this.postRepo.save(posts);
    }

    //show a list of posts
    public List<Posts> findAllPost(){
        return this.postRepo.findAll();
    }

    //find a list of pageable posts
    public Page<Posts> findAllPost2(Pageable pageable){
        return this.postRepo.findAll(pageable);
    }


    //find a post by id
    public Object findByid(Long id) {
        return this.postRepo.findById(id);
    }

    //find a post by id
    public Posts getOnePost(Long id) {
        return postRepo.getOne(id);
    }

    //find a post by subject1
    public Page<Posts> findBySubject1(Posts posts, Pageable pageable) {
        return this.postRepo.SearchSub1(posts,pageable);
    }
    //find a post by subject2
    public Page<Posts> findBySubject2(Posts posts,Pageable pageable) {
        return this.postRepo.SearchSub2(posts,pageable);
    }
    //find a post by subject3
    public Page<Posts> findBySubject3(Posts posts,Pageable pageable) {
        return this.postRepo.SearchSub3(posts,pageable);
    }
    //find a post by subject4
    public Page<Posts> findBySubject4(Posts posts,Pageable pageable) {
        return this.postRepo.SearchSub4(posts,pageable);
    }
    //find a post by subject5
    public Page<Posts> findBySubject5(Posts posts,Pageable pageable) {
        return this.postRepo.SearchSub5(posts,pageable);
    }
}
