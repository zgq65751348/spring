package com.reactive.spring.repository;

import com.reactive.spring.document.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User,Long> {

}
