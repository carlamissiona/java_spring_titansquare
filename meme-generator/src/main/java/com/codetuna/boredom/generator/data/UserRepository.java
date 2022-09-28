package com.codetuna.boredom.generator.data;
import org.springframework.data.repository.CrudRepository;
// import com.codetuna.boredom.data.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
}
