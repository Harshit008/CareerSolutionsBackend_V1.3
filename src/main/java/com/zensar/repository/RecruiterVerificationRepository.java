package com.zensar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entities.RecruiterVerificationToken;

public interface VerificationTokenRepository extends JpaRepository<RecruiterVerificationToken, Long> {

	Optional<RecruiterVerificationToken> findByToken(String token);

}
