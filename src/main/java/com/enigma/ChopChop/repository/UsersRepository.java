package com.enigma.ChopChop.repository;

import com.enigma.ChopChop.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @NonNull
    @Query(value = "SELECT * FROM users WHERE id = :userId", nativeQuery = true)
    Optional<User> findById(@Param("userId") @NonNull Long userId);

    @Modifying
    @Query(value = "DELETE FROM users WHERE id = :userId", nativeQuery = true)
    void deleteById(@Param("userId") @NonNull Long userId);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM users WHERE username = :username", nativeQuery = true)
    boolean existsByUsername(@Param("username") String username);
}