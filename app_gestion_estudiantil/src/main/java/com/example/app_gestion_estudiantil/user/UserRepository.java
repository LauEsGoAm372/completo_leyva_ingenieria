package com.example.app_gestion_estudiantil.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private UserCrudRepository userCrudRepository;
    public User save(User u){  return userCrudRepository.save(u);    }
    public Optional<User> getUser(Long id) {
       return userCrudRepository.findById(id);
    }
    public Optional<User> findByEmail(String email){
        return userCrudRepository.findByEmail(email);
    }

}
