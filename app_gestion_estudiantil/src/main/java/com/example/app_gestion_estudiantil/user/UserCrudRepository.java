package com.example.app_gestion_estudiantil.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<User,Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);


}
