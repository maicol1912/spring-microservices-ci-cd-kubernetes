package com.maicol1912.identityService.repository;

import com.maicol1912.identityService.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials,Integer> {

    Optional<UserCredentials> findByName(String username);
}
