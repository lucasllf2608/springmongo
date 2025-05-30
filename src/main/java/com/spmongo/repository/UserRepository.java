package com.spmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
