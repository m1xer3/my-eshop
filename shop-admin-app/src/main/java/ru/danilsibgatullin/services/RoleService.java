package ru.danilsibgatullin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danilsibgatullin.interfaces.RoleInterface;
import ru.danilsibgatullin.interfaces.RoleRepository;
import ru.danilsibgatullin.models.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements RoleInterface {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> findAll() {
        return roleRepository.findAll() ;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}
