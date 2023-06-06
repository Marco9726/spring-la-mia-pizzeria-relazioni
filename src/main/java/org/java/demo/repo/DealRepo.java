package org.java.demo.repo;

import org.java.demo.pojo.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepo extends JpaRepository<Deal, Integer>{

}
