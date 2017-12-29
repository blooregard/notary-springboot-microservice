package gov.cosos.notary;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.cosos.notary.entity.Person;
import gov.cosos.notary.repository.PersonRepository;

@RestController
@RequestMapping("/api/person/")
public class PersonController {
	private static final Logger log = LoggerFactory.getLogger(PersonController.class);
	private final PersonRepository personRepository;
	
	@Autowired
	PersonController(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Person> getAll() {
		Pageable top = new PageRequest(0, 50);
		
		return this.personRepository.findAll(top);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Person getById(@PathVariable Integer id) {
		return this.personRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, value = "/{id}" )
	public @ResponseBody String updateById(@PathVariable Integer id, @RequestBody Person input) {
		Person person = personRepository.findOne(id);
		
		if (!person.getPersonId().equals(input.getPersonId())) {
			log.info("Not the same id");
			return  "BAD";
		} else {
			log.info("Updating person - " + person.toString());
			return "Created";
		}
	}
}
