package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG= LogFactory.getLog("UserService");

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //@Transactional
    public void savaTransactional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("usuairo insertado: "+user))
                .forEach(userRepository::save);
                //.forEach(user -> userRepository.save(user));

    }

    public List<User>  getAllUsers(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id)

                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setBirthdate(newUser.getBirthdate());
                            user.setName(newUser.getName());
                            return userRepository.save(user);
                        }
                ).get();
    }
}
