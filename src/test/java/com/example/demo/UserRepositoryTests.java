package com.example.demo;

import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;
    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("iuiui@gmail.com");
        user.setFirstName("A");
        user.setLastName("A");
        user.setAddress("Hoa Binh");
        user.setAge(15);
       User savedUser = repo.save(user);

    }
    @Test
    public void testListAll(){
        Iterable<User> u = repo.findAll();
        for (User user: u) {
            System.out.println(user);
        }
    }
    @Test
    public void testUpdateUser(){
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setAddress("Son La");
        repo.save(user);
        User updatedUser = repo.findById(userId).get();
    }
    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        System.out.println(optionalUser.get());
    }
    @Test
    public void testDelete(){
        Integer userId = 1;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
    }
}
