package  com.codetuna.app.liststoday.service;

import java.util.Optional;

import  com.codetuna.app.liststoday.data.Recipes;

public interface IRecipeService {

	Recipes createRecipes(Recipes recipe);
	
	void updateRecipes(long id, Recipes recipe);
	
	Iterable<Recipes> getRecipes();
	
	Optional<Recipes> getRecipesbyId(long id);
	
	void deleteRecipes(long id);
}