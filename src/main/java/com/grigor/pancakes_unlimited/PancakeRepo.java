package com.grigor.pancakes_unlimited;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PancakeRepo  extends JpaRepository<Pancake, Long>{

}
