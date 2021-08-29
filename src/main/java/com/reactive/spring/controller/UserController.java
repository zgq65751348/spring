package com.reactive.spring.controller;

import com.reactive.spring.document.User;
import com.reactive.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
@RequestMapping("/i")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<User> getUser(@PathVariable("id") Long id) {
        Mono<User> userMono = userRepository.findById(id);
        return userMono;
    }

    @PutMapping("/users/{id}")
    public Mono<User> update(@PathVariable("id") Long id, @RequestBody User user) {
        return this.userRepository.findById(id)
                .map(u -> {
                    u.setUsername(user.getUsername());
                    u.setAddress(user.getUsername());
                    return u;
                })
                .flatMap(u -> save(u));
    }

    @DeleteMapping("/users/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return userRepository.deleteById(id);
    }


    @PostMapping("/users")
    public Mono<User> save(@RequestBody User user) {
        return userRepository.save(user);
    }
}


