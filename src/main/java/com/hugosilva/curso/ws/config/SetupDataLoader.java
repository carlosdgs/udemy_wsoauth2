package com.hugosilva.curso.ws.config;

import com.hugosilva.curso.ws.domain.Role;
import com.hugosilva.curso.ws.domain.User;
import com.hugosilva.curso.ws.repository.RoleRepository;
import com.hugosilva.curso.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Arrays;
import java.util.Optional;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        userRepository.deleteAll();
        roleRepository.deleteAll();

        Role roleAdmin = createRoleNotFound( "ROLE_ADMIN");
        Role roleUser  = createRoleNotFound("ROLE_USER");

        User joao = new User("João", "Souza", "joao@gmail.com");
        User maria = new User("Maria", "Teixeira", "maria@gmail.com");
        joao.setRoles(Arrays.asList(roleAdmin));
        maria.setRoles(Arrays.asList(roleUser));


        createUserIfNotFound(joao);
        createUserIfNotFound(maria);
    }

    private User createUserIfNotFound(final User user) {
        Optional<User> obj = userRepository.findByEmail(user.getEmail());
        if(obj.isPresent()) {
            return obj.get();
        }
        return userRepository.save(user);

    }

    private Role createRoleNotFound(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if(role.isPresent()) {
            return role.get();
        }
        return roleRepository.save(new Role(name));
    }
}