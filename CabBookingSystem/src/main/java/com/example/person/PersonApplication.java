package com.example.person;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.repo.HobbyRepository;
import com.example.repo.PersonRepositary;
import com.querydsl.core.types.dsl.BooleanExpression;


@SpringBootApplication
@EnableJpaRepositories("com.example.repo")
public class PersonApplication {
	
	@Autowired
	HobbyRepository hRepo;
	private static final Logger log = LoggerFactory.getLogger(PersonApplication.class);
	
	@Bean
	public CommandLineRunner demo(PersonRepositary repository) {
		
		//insertPerson(repository);
		//findPersonByHobbyname(repository,"Photography");
		findPersonByNameQueryDsl(repository,"Photography");
		/*List<Person> pesonList = repository.findByCountryContains("India");
		for(Person p : pesonList)
		log.info("Person " + p);*/
		return null;
	}
	
	
	private void findPersonByNameQueryDsl(PersonRepositary repository,String hobby)
	{
			BooleanExpression nameExpr = QPerson.person.name.contains("Shamik");
		
		
		

		Iterable<Person> pList = repository.findAll(nameExpr);
		for(Person p : pList)
		log.info("Person " + p);
	}
	
	
	
	private void findPersonByHobbyname(PersonRepositary repository,String hobby)
	{
		List<Person> pList = repository.findPersonByHobbyName(hobby);
		for(Person p : pList)
		log.info("Person " + p);
	}
	
	private void insertPerson(PersonRepositary repository)
	{
		
		 Person p = new Person();
		 p.setName("Samir mitra");
		 p.setCountry("America");
		 p.setGender("male");
		 
		 Person p1 = new Person();
		 p1.setName("Shamik mitra");
		 p1.setCountry("India");
		 p1.setGender("male");
		 
		 repository.save(p);
		 repository.save(p1);
		 
		 Hobby hobby1 =new Hobby();
		 hobby1.setName("Photography");
		 hobby1.setPerson(p);
		 
		 
		 Hobby hobby2 =new Hobby();
		 hobby2.setName("Dancing");
		 hobby2.setPerson(p1);
		 
		 Hobby hobby3 =new Hobby();
		 hobby3.setName("Photography");
		 hobby3.setPerson(p1);
		 
		 
		 hRepo.save(hobby1);
		 hRepo.save(hobby2);
		 hRepo.save(hobby3);
		 
		 
		 
		
	}

	
	public static void main(String[] args) {
		
	 SpringApplication.run(PersonApplication.class, args);
	
	
	}
}
