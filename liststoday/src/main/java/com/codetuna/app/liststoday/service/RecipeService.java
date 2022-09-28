package com.codetuna.app.liststoday.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetuna.app.liststoday.data.Recipes;
import com.codetuna.app.liststoday.data.RecipesRepository;

@Service
public class RecipeService implements IRecipeService {

	private RecipesRepository recipe_repo;

	@Autowired
	public RecipeService(RecipesRepository recipe_repo) {
		this.recipe_repo = recipe_repo;
	}

	@Override
	public Recipes createRecipes(Recipes recipe) {
		return recipe_repo.save(recipe);
	}
	  
	@Override 
	public void updateRecipes(long id, Recipes recipe_inparam) {
		Optional<Recipes> optionalRecipes = recipe_repo.findById(id);

		if(optionalRecipes.isPresent()) {

			Recipes thiscurrent_recipe = optionalRecipes.get();
			thiscurrent_recipe.setTitle(recipe_inparam.getTitle());
			thiscurrent_recipe.setContent(recipe_inparam.getContent());
			thiscurrent_recipe.setStatus(recipe_inparam.getStatus());
			thiscurrent_recipe.setAuthor(recipe_inparam.getAuthor()); 
			thiscurrent_recipe.setDescription_recipe(recipe_inparam.getDescription_recipe()); 
			recipe_repo.save(thiscurrent_recipe);

		}
	}

	@Override
	public Iterable<Recipes> getRecipes() {
		return recipe_repo.findAll();
	}

	@Override
	public void deleteRecipes(long id) {
		recipe_repo.deleteById(id);
	}

	@Override
	public Optional<Recipes> getRecipesbyId(long id) {
		return recipe_repo.findById(id);
	}



}