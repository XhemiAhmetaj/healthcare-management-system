package com.akademia.ikub.controller;

import com.akademia.ikub.dto.RoleDTO;
import com.akademia.ikub.dto.UserRegistrationDTO;
import com.akademia.ikub.entity.User;
import com.akademia.ikub.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/{roleId}")
    public ResponseEntity<UserRegistrationDTO> registerUser(@PathVariable Integer roleId,
                                                            @RequestBody UserRegistrationDTO u){
        return ResponseEntity.ok(userService.registerUser(roleId,u));
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<RoleDTO> addRole(@PathVariable String roleName){
        return ResponseEntity.ok(userService.addRole(roleName));
    }


    @GetMapping
    public ResponseEntity<List<User>> returnAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
