package com.repositary;

import com.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Person repositary.
 */
@Repository
public interface PersonRepositary extends MongoRepository<Person, Integer> {
}
