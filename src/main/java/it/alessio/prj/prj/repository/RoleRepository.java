package it.alessio.prj.prj.repository;

import it.alessio.prj.prj.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {


    Optional<Role> findById(Integer id);
}
