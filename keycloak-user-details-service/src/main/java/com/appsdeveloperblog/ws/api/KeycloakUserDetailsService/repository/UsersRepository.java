package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.repository;

import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User findByEmail(String userEmail);
}
