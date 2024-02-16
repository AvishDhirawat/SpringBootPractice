package com.firstspring.project.firstprojectspring.jpa;

import com.firstspring.project.firstprojectspring.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
