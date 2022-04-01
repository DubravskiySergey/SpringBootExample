package z_spring.com.example.z_spring_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import z_spring.com.example.z_spring_demo.entities.TicketType;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Integer>{

}
