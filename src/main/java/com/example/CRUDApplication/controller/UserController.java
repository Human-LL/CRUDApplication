package com.example.CRUDApplication.controller;

import com.example.CRUDApplication.model.User;
import com.example.CRUDApplication.servise.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")    //http://localhost:9090/users/all
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")    //http://localhost:9090/users/add
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/by-id")    //http://localhost:9090/users/by-id?id=1
    public ResponseEntity getUserById(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            return ResponseEntity.badRequest().build();
        }
        Long id = Long.valueOf(idStr);

        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update")    //http://localhost:9090/users/update
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user.getId(), user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/delete")    //http://localhost:9090/users/delete?id=1
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        if (id != null) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}