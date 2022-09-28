package com.codetuna.app.liststoday.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codetuna.app.liststoday.data.Recipes;

@Repository
public interface RecipesRepository extends CrudRepository<Recipes, Long>{
 

}