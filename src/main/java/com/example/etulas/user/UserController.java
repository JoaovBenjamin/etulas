package com.example.etulas.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.etulas.user.dto.UserResponse;

@Controller
@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
    UserService service;

    @Autowired
    UserRepository repository;

     @GetMapping
    public List<UserResponse> findAll(){
        return service 
                      .findAll()
                      .stream()
                      .map(UserResponse::fromModel)
                      .toList();
                       
    }

     @PostMapping
public ResponseEntity<UserResponse> create(@RequestBody User data){
        var user = service.create(data);

        return ResponseEntity
                .created(user.toEntityModel().getRequiredLink("self").toUri())
                .body(UserResponse.fromModel(user));
    }

    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
       repository.deleteById(id);
          return ResponseEntity.noContent().build();
    }
}

