package com.deepak.prodReadyFeatures.prod_ready_features.repositories;

import com.deepak.prodReadyFeatures.prod_ready_features.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}
