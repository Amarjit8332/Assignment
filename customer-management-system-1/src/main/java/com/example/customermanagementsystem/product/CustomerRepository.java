package com.example.customermanagementsystem.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	
//    void deleteById(Long id);
//	Optional<Customer> findAllById(Long id);
//	Optional<Customer> findById(Long id);
	List<Customer> findAllByNameContainingIgnoreCase(String name);
	@Query("SELECT c FROM Customer c WHERE c.firstName = :firstName AND c.lastName = :lastName")
	List<Customer> findAllByName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
