package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.UserDTO;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.mapper.UserMapper;
import com.ikub.healthcare.service.UserService;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @RolesAllowed("ADMIN")
    @PostMapping("/admin/{userRole}/{department}")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO req, @PathVariable String userRole, @PathVariable String department){
        UserDTO dto = userService.registerUser(req, userRole, department);
        return ResponseEntity.ok(dto);

    }
    @RolesAllowed("ADMIN")
    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable Integer id){
        User u = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(u));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> returnAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
//    @RolesAllowed("ADMIN")
//    @GetMapping("/admin/doctors")
//    public ResponseEntity<List<User>> returnAllDoctors(){
//        return ResponseEntity.ok(userService.findAllDoctors());
//    }
//    @RolesAllowed("ADMIN")
//    @GetMapping("/admin/family-doctors")
//    public ResponseEntity<List<User>> returnFamilyDoctors(){
//        return ResponseEntity.ok(userService.findAllFamilyDoctors());
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @RolesAllowed("ADMIN")
//    @GetMapping("/admin/{userRole}")
//    public ResponseEntity<List<UserDTO>> returnUserByRole(@PathVariable String userRole){
//        return ResponseEntity.ok(userService.findUserByRole(userRole));
//    }

}
