package com.example.magazine.modules.repository;

import com.example.magazine.modules.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Posts,Long> {

    // @Query("select p from Posts p where :#{#posts} is null or p.subject like concat('%',:#{#posts.subject},'%')")
   // @Query("select p from Posts p join p.categories pc where :#{#posts.categories} is null or pc in (:#{#posts.categories})")
    @Query("select p from Posts p where  p.subject='Blockchain' ")
    Page<Posts> SearchSub1(Posts posts, Pageable pageable);

    @Query("select p from Posts p where  p.subject='MachineLearning' ")
    Page<Posts> SearchSub2(Posts posts,Pageable pageable);

    @Query("select p from Posts p where  p.subject='GraphicsCards' ")
    Page<Posts> SearchSub3(Posts posts,Pageable pageable);

    @Query("select p from Posts p where  p.subject='RAM' ")
    Page<Posts> SearchSub4(Posts posts,Pageable pageable);

    @Query("select p from Posts p where  p.subject='CPU' ")
    Page<Posts> SearchSub5(Posts posts, Pageable pageable);

//    @Query("select p from Posts p join p.categories pc  where pc in (:#{#posts.categories})")
//    List<Posts> SearchSub3(Posts posts);


//    @Query("select p from Posts p where p.subject=:subject")
//    Posts findBySub(@Param("subject") String subject);

}
