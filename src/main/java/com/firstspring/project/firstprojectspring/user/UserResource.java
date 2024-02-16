package com.firstspring.project.firstprojectspring.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserResource {

    public UserDAOService service;

    public UserResource(UserDAOService service) {
        this.service = service;
    }

    // GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    //https://localhost:8080/users

    //EntityModel
    //WebMVCLinkBuilder


    // GET /users/{id}
    @GetMapping("/users/{id}")
//    public User retrieveSpecificUser(@PathVariable int id){
    public EntityModel<User> retrieveSpecificUser(@PathVariable int id) {

        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user); // Added later on after the Entity Model Creation
//        return user;

        //Added later on for dynamic link to display within api to retrieve all users
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    // Delete /users/{id}
    @DeleteMapping("/users/{id}")
    public void deleteSpecificUser(@PathVariable int id) {
        service.deleteOne(id);
    }

    //POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
//        return ResponseEntity.created(null).build();
        // For Location in status code response
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); // /users/4 => /users /{id}, user.getId()
        // location - /users/4
        return ResponseEntity.created(location).build();
    }

}
