package com.book.store.Repository;

import com.book.store.Model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, String> {

    ApplicationUser findUserByUsername(String username);

}
