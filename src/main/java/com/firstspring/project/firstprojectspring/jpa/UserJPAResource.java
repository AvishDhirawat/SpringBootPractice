package com.firstspring.project.firstprojectspring.jpa;

import com.firstspring.project.firstprojectspring.user.Post;
import com.firstspring.project.firstprojectspring.user.User;
import com.firstspring.project.firstprojectspring.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    private UserRepository repository;
    private PostRepository postRepository;
    public UserJPAResource(UserRepository repository, PostRepository postRepository){
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveSpecificUser(@PathVariable int id){

        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:"+ id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    // Delete /users/{id}
    @DeleteMapping("/jpa/users/{id}")
    public void deleteSpecificUser(@PathVariable int id){
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForSpecificUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:"+ id);
        }

        return user.get().getPosts();

    }

    //POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = repository.save(user);
//        return ResponseEntity.created(null).build();
        // For Location in status code response
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); // /users/4 => /users /{id}, user.getId()
        // location - /users/4
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForSpecificUser(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:"+ id);
        }

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri(); // /users/4 => /users /{id}, user.getId()

        return ResponseEntity.created(location).build();

    }

}
