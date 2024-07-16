package tech.ian.watchStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import tech.ian.watchStore.entity.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserDetails findByEmail(String email);

    UserEntity findByVerificationCode(String verificationCode);
}
