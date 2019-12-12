package co.uk.yktech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.uk.yktech.models.Person;
import co.uk.yktech.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepo;

	public ResponseEntity<List<Person>> getAllPayers() {
		return new ResponseEntity<>((List<Person>) personRepo.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<Person> createPayer(String name) {
		Person person = new Person();
		person.setName(name);
		personRepo.save(person);

		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	public ResponseEntity<Person> getPayerById(Long id) {
		return new ResponseEntity<>(personRepo.findById(id).get(), HttpStatus.OK);
	}

}
