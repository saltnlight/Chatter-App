package com.flora.chatter.Repository;

import com.flora.chatter.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);

    boolean existsAppUserByEmail(String email);
}
