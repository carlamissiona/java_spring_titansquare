package com.codetuna.app.liststoday.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler; 
import org.springframework.security.core.annotation.AuthenticationPrincipal; 
import org.springframework.security.core.context.SecurityContextHolder; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import com.codetuna.app.liststoday.data.Recipes;    
import com.codetuna.app.liststoday.data.RecipesRepository; 
import com.codetuna.app.liststoday.service.RecipeService; 

import io.swagger.v3.oas.annotations.Hidden;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller 
@RequestMapping("/api") 
public class RecipesController {

	private final RecipeService recipeService;
 	@Autowired
    public RecipesController(RecipeService _recipeService) {
        this.recipeService = _recipeService;
    }
    @Operation(summary = "Get the list of recipes. It has simple GET method")
    @GetMapping("/lists-recipes")
    public String index(Recipes recipes, Model model) {
        List<Recipes> recipesList = (List<Recipes>) recipeService.getRecipes();
		System.out.println(recipesList);System.out.println("=======================");
		System.out.println(recipesList);System.out.println("=======================");
        model.addAttribute("recipes", recipesList.isEmpty() ? Collections.EMPTY_LIST : recipesList);
        return "recipes";
    }
	@Hidden
	@GetMapping("/add-recipes")
    public String showAddRecipesForm(Recipes recipe) {
        return "addrecipes";
    }
   
    @Operation(summary = "A POST method to add a recipe to our database. ")
	@PostMapping("/add-recipes")
    public String addRecipes(@Valid Recipes recipe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addrecipes";
        }
        recipeService.createRecipes(recipe);
        model.addAttribute("recipes", recipeService.getRecipes());
        return "redirect:/lists-recipes";
    }
	@Hidden
	@GetMapping("/update-recipes/{id}")
    public String showUpdateRecipeForm(@PathVariable("id") Long id, Model model) {
		System.out.println(id);
		Recipes rc = recipeService.getRecipesbyId(id).get();
		System.out.println(rc);System.out.println("=====================>>");
	 
		model.addAttribute("id", id);
        model.addAttribute("recipe", rc );

        return "updaterecipes";
    }
	
   
    @Operation(summary = "A POST method to update a recipe. Must supply id in action attribute of form. ")
    @PostMapping("/update-recipes/{id}")
    public String updateRecipes(@PathVariable("id") Long id, @Valid Recipes recipe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            recipe.setId(id);
            return "updaterecipes";
        }
		System.out.println(recipe);System.out.println("<<<<====================>>");	System.out.println(recipe);
        recipeService.updateRecipes(id, recipe);
        model.addAttribute("recipes", recipeService.getRecipes());
        return "redirect:/lists-recipes";
    } 


    @Operation(summary = "A POST method to delete a recipe. Must supply id in action attribute of form. ")
	@PostMapping("/delete-recipes/{id}")
    public String deleteRecipes(@PathVariable("id") Long id, Model model) {              
    	recipeService.deleteRecipes(id);
        model.addAttribute("recipes", recipeService.getRecipes());
        return "redirect:/lists-recipes";
    }
    
                                                                                                                                                                                                                                                                                                               
	// @GetMapping("/catstrivia")                                                                                                             
    // public String cats( Model model ) {
	// 	Random randomGenerator=new Random();  
	// 	String command ="curl -X GET https://catfact.ninja/facts?limit=1&page=" + (randomGenerator.nextInt(10) + 1);
    //     ProcessBuilder pb = new ProcessBuilder(command.split(" "));                                                        

	// 	Process proc = null; BufferedReader read  = null; String txtsb ="";

	// 	try{
	// 		pb.redirectErrorStream(true); 
	// 		proc = pb.start(); 
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("IOException");System.out.println("IOException");System.out.println("IOException");
	// 		System.out.println(ex);
	// 	}
	// 	try{
	// 		InputStream ins = proc.getInputStream(); 
	// 		read = new BufferedReader(new InputStreamReader(ins));
	// 		StringBuilder sb = new StringBuilder();
		 
	// 		read.lines().forEach(line -> {
	// 			System.out.println("line>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
	// 			sb.append(line);
	// 		});

	// 		System.out.println("line> sb "+sb);
	// 		txtsb = sb.toString();
		 
	// 		read.close();// close the buffered reader
	// 	}catch(IOException ex){
	// 		System.out.println("InterruptedException");
	// 	}
			 
	// 	try{
	// 		proc.waitFor(); int exitCode = proc.exitValue();
	// 		System.out.println("exit code::"+exitCode); 
	// 	}
	// 	catch(InterruptedException ex){
	// 		System.out.println("InterruptedException");System.out.println("InterruptedException");System.out.println("InterruptedException");	System.out.println(ex);
	// 	}
	  
	// 	proc.destroy();

	// 	/*=================================*/
	// 	String command2 ="curl -X GET https://catfact.ninja/facts?limit=12&page=" + (randomGenerator.nextInt(10) + 1);
    //     System.out.println(command2);
	// 	ProcessBuilder pb2 = new ProcessBuilder(command2.split(" "));

	// 	Process proc2 = null; BufferedReader read2  = null; String txtsb2 ="";

	// 	try{
	// 		pb2.redirectErrorStream(true); 
	// 		proc2 = pb2.start(); 
	// 	}
	// 	catch(Exception ex){
	// 		System.out.println("IOException");System.out.println("IOException");System.out.println("IOException");
	// 		System.out.println(ex);
	// 	}
	// 	try{
	// 		InputStream ins2 = proc2.getInputStream(); 
	// 		read2 = new BufferedReader(new InputStreamReader(ins2));
	// 		StringBuilder sb2 = new StringBuilder();
		 
	// 		read2.lines().forEach(line -> {
	// 			System.out.println("line>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
	// 			sb2.append(line);
	// 		});

	// 		System.out.println("line> sb "+sb2);
	// 		txtsb2 = sb2.toString();
		 
	// 		read2.close();// close the buffered reader
	// 	}catch(IOException ex){
	// 		System.out.println("InterruptedException");
	// 	}
			 
	// 	try{
	// 		proc2.waitFor(); int exitCode2 = proc2.exitValue();
	// 		System.out.println("exit code::"+exitCode2); 
	// 	}
	// 	catch(InterruptedException ex){
	// 		System.out.println("InterruptedException");System.out.println("InterruptedException");System.out.println("InterruptedException");	System.out.println(ex);
	// 	}
	  
	// 	proc2.destroy();

	// 	model.addAttribute("trivia", txtsb );
	// 	model.addAttribute("tbl_trivia", txtsb2 );
		 
	// 	return "catstrivia";
	// }


	// @GetMapping("/memes")
	// public String memes() {
     
	// 	return "memes";
	// }
	// @GetMapping("/quotes")
	// public String quotes() {
     
	// 	return "quotes";
	// }

 
 

	

}
