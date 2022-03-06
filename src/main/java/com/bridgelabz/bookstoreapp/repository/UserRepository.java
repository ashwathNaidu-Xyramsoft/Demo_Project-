package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    User getEncodePasswordByEmail(String email);

    User getEmailIdByEmail(String email);


}
