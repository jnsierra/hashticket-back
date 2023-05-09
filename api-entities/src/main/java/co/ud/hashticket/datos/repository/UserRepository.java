package co.ud.hashticket.datos.repository;

import co.ud.hashticket.datos.entity.UserEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, CrudRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
    @Modifying
    @Transactional
    Integer updateAttempts(@Param("id") Long id, @Param("attempts") Long attempts);
    @Modifying
    Integer updatePassword(@Param("email")String email, @Param("password")String password);
}