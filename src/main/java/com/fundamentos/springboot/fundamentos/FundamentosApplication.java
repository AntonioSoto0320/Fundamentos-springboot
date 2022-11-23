package com.fundamentos.springboot.fundamentos;


import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.practicando.Calculadora;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.repository.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private Calculadora calculadora;
	private MyBeanWithProperties myBeanWithProperties;

	private UserPojo userPojo;

	private UserRepository userRepository;

	private UserService userService;

	private void getInformationJpqlFromUser(){
		/*LOGGER.info("Usuario con el metodo finbyUserEmail" + userRepository.findByUserEmail("antonio@mail.com")
				.orElseThrow(()->new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("Anto", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort "+user));

		userRepository.findByName("Antonio")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method "+user));

		LOGGER.info( "usuario con query method " + userRepository.findByEmailAndName("juan@mail.com","Juan")
				.orElseThrow(()-> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%u%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario finByLike "+user));


		userRepository.findByNameOrEmail(null,"antonio@mail.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail "+user));*/

		userRepository.findByBirthdateBetween(LocalDate.of(2003,1,1),
				LocalDate.of(2006,8,8))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas" + user));



//		userRepository.findByNameLikeOrderByIdDesc("user")
//				.stream()
//				.forEach(user -> LOGGER.info("Usuario encontrado con like ordenado "+ user));

		userRepository.findByNameContainingOrderByIdDesc("user")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like ordenado "+ user));

		LOGGER.info("El usuario a partir del named parameter es: "+userRepository.getAllByBirthdateAndEmail(LocalDate.of(2003,7, 3),
				"antonio@mail.com")
		.orElseThrow(()->
				new RuntimeException("No se enocntro el usuario a partir de named parameter")));

	}


	/*private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;*/


	/*public FundamentosApplication(@Qualifier("componentToImplement")ComponentDependency componentDependency,MyBean myBean,MyBeanWithDependency myBeanWithDependency) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency=myBeanWithDependency;
	}*/

	public FundamentosApplication(Calculadora calculadora, UserPojo userPojo, UserRepository userRepository,UserService userService) {
		this.calculadora = calculadora;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService=userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUserInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();

	}

	private void saveWithErrorTransactional(){
		User userTest1 = new User("TestTransactional1","TestTransactional1@mail",LocalDate.now());
		User userTest2 = new User("TestTransactional2","TestTransactional2@mail",LocalDate.now());
		User userTest3 = new User("TestTransactional3","TestTransactional2@mail",LocalDate.now());
		User userTest4 = new User("TestTransactional4","TestTransactional2@mail",LocalDate.now());

		List<User> users = Arrays.asList(userTest1,userTest2,userTest4);

		try {

			userService.savaTransactional(users);

		}catch (Exception e){
			LOGGER.error("Esta es una exception de tipo transactional");
		}


		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("este es el usuario dentro del metodo transactional "+user));

	}


	public void  saveUserInDataBase(){
		User user1 = new User("Antonio","antonio@mail.com", LocalDate.of(2003,7,3));
		User user2 = new User("Juan","juan@mail.com", LocalDate.of(1999,11,22));
		User user3 = new User("user3","user3@mail.com", LocalDate.of(1999,3,3));
		User user4 = new User("user4","user4@mail.com", LocalDate.of(1999,4,4));
		User user5 = new User("user5","user5@mail.com", LocalDate.of(1999,5,5));
		User user6 = new User("user6","user6@mail.com", LocalDate.of(1999,6,6));
		User user7 = new User("user7","user7@mail.com", LocalDate.of(2006,7,7));
		User user8 = new User("user8","user8@mail.com", LocalDate.of(2005,8,8));
		User user9 = new User("user9","user9@mail.com", LocalDate.of(1999,9,9));
		User user10 = new User("user10","user10@mail.com", LocalDate.of(2021,10,10));

		 List<User> lists = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		 lists.stream().forEach(userRepository::save);


	}



	private void ejemplosAnteriores(){
		System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword());


		try {
			int value =10/0;
			LOGGER.debug("Mi valor : "+value);
		}catch (Exception e){
			LOGGER.debug("esto es un erro al dividir por cero "+ e.getMessage());
		}

		System.out.println(myBeanWithProperties.function());
		calculadora.operationsCalculator(6,2);
		LOGGER.debug("esto es un debug");

		LOGGER.info("Esto es un info");

		//System.out.println("La suma es : "+calculadora.operaciones(5,2));

		//System.out.println("La resta es : "+calculadora.operaciones(5,2));

		//System.out.println("La multiplicacion es : "+calculadora.operaciones(5,2));

		//System.out.println("La division es : "+calculadora.operaciones(6,2));

		/*componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();*/
	}
}
