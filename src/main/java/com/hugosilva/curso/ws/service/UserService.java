package com.hugosilva.curso.ws.service;

import com.hugosilva.curso.ws.domain.User;
import com.hugosilva.curso.ws.dto.UserDto;
import com.hugosilva.curso.ws.repository.UserRepository;
import com.hugosilva.curso.ws.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll() ;
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()  -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User create(User user ){
        return userRepository.save(user);

    }

    public User fromDto(UserDto userDto) {
        return new User(userDto);

    }

    public User update(User user) {
        Optional<User> updateUser = userRepository.findById(user.getId());
        return updateUser.map( u -> userRepository.save( new User(u.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),
                               u.getPassword(), u.isEnable() )))
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));

    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

}
