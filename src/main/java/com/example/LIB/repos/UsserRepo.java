package com.example.LIB.repos;

import com.example.LIB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsserRepo extends JpaRepository<User,Long> {
}
