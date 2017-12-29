package gov.cosos.notary.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import gov.cosos.notary.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{

	List<Person> findByLastName(String lastName);

	Collection<Person> findAll(Pageable top);
	
}
