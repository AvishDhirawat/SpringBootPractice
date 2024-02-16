package com.firstspring.project.firstprojectspring.jpa;

import com.firstspring.project.firstprojectspring.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
