package com.galymzhan.finalspring.service;

import com.galymzhan.finalspring.entity.RoleEntity;
import com.galymzhan.finalspring.entity.UserEntity;
import com.galymzhan.finalspring.repository.PermissionRepo;
import com.galymzhan.finalspring.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepo permissionRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);

        if (Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User Not Found");
    }

    public void registr(UserEntity model) {
        UserEntity check = userRepository.findByEmail(model.getEmail());
        if (check == null) {
            model.setPassword(passwordEncoder.encode(model.getPassword()));
            RoleEntity userRoleEntity = permissionRepo.findByName("ROLE_USER");

            if (userRoleEntity != null) {
                List<RoleEntity> roleEntities = List.of(userRoleEntity);
                model.setRoleEntities(roleEntities);
                userRepository.save(model);
            }
        }
    }
}
