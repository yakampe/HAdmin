package co.uk.yktech.repositories;

import org.springframework.data.repository.CrudRepository;

import co.uk.yktech.models.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

	
}
