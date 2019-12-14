package dev.equalcoding.repositories;

import org.springframework.data.repository.CrudRepository;

import dev.equalcoding.models.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

	
}
