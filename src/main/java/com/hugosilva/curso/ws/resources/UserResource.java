package com.hugosilva.curso.ws.resources;


import com.hugosilva.curso.ws.domain.Role;
import com.hugosilva.curso.ws.domain.User;
import com.hugosilva.curso.ws.dto.UserDto;
import com.hugosilva.curso.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> users = userService.findAll();
        List<UserDto> listDto = users.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
//        List<User> users = new ArrayList<>();
//        User joao = new User("João", "Souza", "joao@gmail.com");
//        User maria = new User("Maria", "Teixeira", "maria@gmail.com");
//        users.addAll(Arrays.asList(joao, maria));
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findByIdA(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDto(user));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        User user = userService.fromDto(userDto);
        return ResponseEntity.ok().body(new UserDto(userService.create(user)));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UserDto userDto) {
        User user = userService.fromDto(userDto);
        user.setId(id);
        return ResponseEntity.ok().body(new UserDto(userService.update(user)));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}/roles")
    public ResponseEntity<List<Role>> findRoles(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user.getRoles());

    }


}



