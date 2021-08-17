package ru.danilsibgatullin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.danilsibgatullin.controllers.RoleDto;
import ru.danilsibgatullin.controllers.UserDto;
import ru.danilsibgatullin.interfaces.RoleRepository;
import ru.danilsibgatullin.interfaces.UserInterface;
import ru.danilsibgatullin.interfaces.UserRepository;
import ru.danilsibgatullin.models.User;
import ru.danilsibgatullin.models.UserParams;
import ru.danilsibgatullin.models.UserSpecifications;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(user -> new UserDto(user.getId(),user.getUsername(),user.getAge(),mapRolesDto(user)))
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDto> findWithFilter(UserParams userParams) {
        Specification<User> spec = Specification.where(null);

        if (userParams.getUsernameFilter() != null && !userParams.getUsernameFilter().isEmpty()) {
            spec = spec.and(UserSpecifications.usernamePrefix(userParams.getUsernameFilter()));
        }
        if (userParams.getMinAge() != null) {
            spec = spec.and(UserSpecifications.minAge(userParams.getMinAge()));
        }
        if (userParams.getMaxAge() != null ) {
            spec = spec.and(UserSpecifications.maxAge(userParams.getMaxAge()));
        }

        if(userParams.getSortIsDown()==null || userParams.getSortIsDown()) {
            return userRepository.findAll(spec,
                    PageRequest.of(
                            Optional.ofNullable(userParams.getPage()).orElse(1) - 1,
                            Optional.ofNullable(userParams.getSize()).orElse(3),
                            Sort.by(Sort.Direction.ASC,Optional.ofNullable(userParams.getSortField())
                                    .filter(c -> !c.isEmpty())
                                    .orElse("id")))).map(user -> new UserDto(user.getId(),user.getUsername(),user.getAge(),mapRolesDto(user)));
        }else{
            return userRepository.findAll(spec,
                    PageRequest.of(
                            Optional.ofNullable(userParams.getPage()).orElse(1) - 1,
                            Optional.ofNullable(userParams.getSize()).orElse(3),
                            Sort.by(Sort.Direction.DESC,Optional.ofNullable(userParams.getSortField())
                                    .filter(c -> !c.isEmpty())
                                    .orElse("id")))).map(user -> new UserDto(user.getId(),user.getUsername(),user.getAge(),mapRolesDto(user)));
        }
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(user -> new UserDto(user.getId(),user.getUsername(),user.getAge(),mapRolesDto(user)));
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getAge(),
                userDto.getRoles().stream()
                        .map(roleDto -> roleRepository.getOne(roleDto.getId()))
                        .collect(Collectors.toSet()));
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private static Set<RoleDto> mapRolesDto(User user) {
        return user.getRoles().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toSet());
    }
}