package com.example.magazine.modules.repository;

import com.example.magazine.modules.entity.Users;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {

    Users findByUsername(String username);

//    Users findByEmail(String email);


//    @Query("select p from Users p where p.email=:email")
//    Users findByEmail2(@Param("email") String subject);
}
