package loko.lab1.repository;

import loko.lab1.entity.Tent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TentRepository extends JpaRepository<Tent, Long> {
}
