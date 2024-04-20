package com.example.magazine.modules.services;

import com.example.magazine.modules.entity.Users;
import com.example.magazine.modules.repository.UsersRepo;
import com.example.magazine.modules.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UsersRepo usersRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersRepo usersRepo, @Lazy PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //store in database
    @Transactional
    public Users saveUser(Users users){
        users.setPassword(this.passwordEncoder.encode(users.getPassword()));

//        users.setRoles(Roles.USER);
//
//        Role role = roleRepository.findByRole(2);
//        Set<Role> roles = new HashSet<>();
//        roles.add(users.setRoles(roles));
//        users.setRoles(roles);

        return this.usersRepo.save(users);
    }

    //change password
    @Transactional
//    @PreAuthorize("#username == authentication.name")
    public Users findUsername(String username, String password){
        Users user= this.usersRepo.findByUsername(username);
        user.setPassword(this.passwordEncoder.encode(password));
        return this.usersRepo.save(user);
    }
    //show a list of Users
    public List<Users> showAllUsers(){
        return this.usersRepo.findAll();
    }

    //get one user only by id
    public Users findByid(Long id) {
        return this.usersRepo.getOne(id);
    }

    //find a user by Username
    public Users findByUsername(String username) {
        return usersRepo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepo.findByUsername(username);

    }
}
