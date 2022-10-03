package com.codetuna.app.liststoday.web;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler; 

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.ui.Model;
import java.util.Random;
import com.codetuna.app.liststoday.data.User; 
import java.io.BufferedReader;  
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException; 
import java.security.Principal;
import java.io.BufferedInputStream;  
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;

@Controller
@RequestMapping("/") 
public class SiteController { 
	


    @Value("${openapi.server}")
    private String serverswaggerui;


    @GetMapping("/home")
    public String home( Model model , Principal user) { 

		//  String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
        //     .replacePath(null)
        //     .build()
        //     .toUriString();
 
	    // System.out.println(baseUrl);
		
      // news  
	//   model.addAttribute("news_tech", get_news("tech") );
	//   model.addAttribute("news_biz", get_news("biz") );	  
	//   model.addAttribute("news_us", get_news("us") );
	//   curl https://api.coinranking.com/v2/coins?limit=8&tags=stablecoin -H x-access-token: 53a0b0a7e4f2fa59519e4
	//   stablecoin nft defi staking exchange
	 if (user == null ){
		System.out.println("!!Not LoginLoginLogin");System.out.println("!!Not LoginLoginLogin");
		model.addAttribute("islogin",  false);
	 }else{
	    System.out.println("LoginLoginLogin");
		
		model.addAttribute("islogin",  true);
	 }
	//   model.addAttribute("coin_defi", get_coinrank("defi") );
 
	//   model.addAttribute("coin_stablecoin", get_coinrank("stablecoin") );	  
	//   model.addAttribute("coin_staking", get_coinrank("staking") );
	//   model.addAttribute("coin_nft", get_coinrank("nft") );
	//   model.addAttribute("recipes", get_recipes() );
	 
	//   recipes
	//  "https://api.spoonacular.com/recipes/random?number=1&tags=vegetarian,dessert&apiKey=68e63d319d6e4b51ba1c11de4e945de3"

      return "home";
    }
	@GetMapping("/index")
    public String index( Model model , Principal user) {
      model.addAttribute("islogin",  true);
      return "index";
    }
	@GetMapping("/food")
    public String food() {
     
      return "index";
    }
	 
	private String get_news(String genre){

		String cmd_str ="";
		switch (genre) {
		case "tech":
			cmd_str = "curl -X GET 'https://api.newscatcherapi.com/v2/latest_headlines?countries=PH&topic=tech&page_size=3' -H 'x-api-key:YqyvD46VhUYoB--M4AlEaZeWe633q0_DD0sVz6cGFVo'" ;
			break;
		case "us":
			cmd_str = "curl -X GET 'https://api.newscatcherapi.com/v2/latest_headlines?lang=en&countries=US&topic=politics&page_size=3' -H 'x-api-key:YqyvD46VhUYoB--M4AlEaZeWe633q0_DD0sVz6cGFVo'" ;
			break;
		case "biz":
			cmd_str  ="curl -X GET 'https://api.newscatcherapi.com/v2/latest_headlines?countries=PH&topic=business&page_size=3' -H 'x-api-key:YqyvD46VhUYoB--M4AlEaZeWe633q0_DD0sVz6cGFVo' -H 'Authorization: Apikey' " ;
			break;
		} 

		Random randomGenerator=new Random(); 
		ProcessBuilder pb = new ProcessBuilder(cmd_str.split(" "));

		Process proc = null; BufferedReader read  = null; String txtsb ="";

		try{
			pb.redirectErrorStream(true); 
			proc = pb.start(); 
		}
		catch(Exception ex){
			System.out.println("IOException");System.out.println("IOException");System.out.println("IOException");
			System.out.println(ex);
		}
		try{
			InputStream ins = proc.getInputStream(); 
			read = new BufferedReader(new InputStreamReader(ins));
			StringBuilder sb = new StringBuilder();
		 
			read.lines().forEach(line -> {
				System.out.println("line>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
				//if(sb.contains(" ")  ){break;};
				sb.append(line);
			});
          
			System.out.println("line> sb "+sb);
			txtsb = sb.toString();
		 
			read.close();// close the buffered reader
		}catch(IOException ex){
			System.out.println("InterruptedException");
		}
			 
		try{
			proc.waitFor(); int exitCode = proc.exitValue();
			System.out.println("exit code::"+exitCode); 
		}
		catch(InterruptedException ex){
			System.out.println("InterruptedException");System.out.println("InterruptedException");System.out.println("InterruptedException");	System.out.println(ex);
		}
	  
		proc.destroy();	System.out.println("genre'''''''''''''''''''''''");
		System.out.println(txtsb);System.out.println("genre'''''''''''''''''''''''");
		return txtsb;

	}
	private String get_coinrank(String coin){
 
		String cmd_str = "curl -X GET https://api.coinranking.com/v2/coins?limit=7&tags="+ coin  +" -H x-access-token: 53a0b0a7e4f2fa59519e4";

		Random randomGenerator=new Random(); 
		ProcessBuilder pb = new ProcessBuilder(cmd_str.split(" "));

		Process proc = null; BufferedReader read  = null; String txtsb ="";

		try{
			pb.redirectErrorStream(true); 
			proc = pb.start(); 
		}
		catch(Exception ex){
			System.out.println("IOException");System.out.println("IOException");System.out.println("IOException");
			System.out.println(ex);
		}
		try{
			InputStream ins = proc.getInputStream(); 
			read = new BufferedReader(new InputStreamReader(ins));
			StringBuilder sb = new StringBuilder();
		 
			read.lines().forEach(line -> {
				System.out.println("line>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
				sb.append(line);
			});

			System.out.println("line> sb "+sb);
			txtsb = sb.toString();
		 
			read.close();// close the buffered reader
		}catch(IOException ex){
			System.out.println("InterruptedException");
		}
			 
		try{
			proc.waitFor(); int exitCode = proc.exitValue();
			System.out.println("exit code::"+exitCode); 
		}
		catch(InterruptedException ex){
			System.out.println("InterruptedException");System.out.println("InterruptedException");System.out.println("InterruptedException");	System.out.println(ex);
		}
	  
		proc.destroy();	System.out.println("get_coinrank'''''''''''''''''''''''");
		System.out.println(txtsb);System.out.println("get_coinrsank'''''''''''''''''''''''");
		return txtsb;

	}
	private String get_recipes(){
 
		String cmd_str =  "curl -X GET https://api.spoonacular.com/recipes/random?number=10&tags=vegetarian,dessert&apiKey=68e63d319d6e4b51ba1c11de4e945de3" ;

		Random randomGenerator=new Random(); 
		ProcessBuilder pb = new ProcessBuilder(cmd_str.split(" "));

		Process proc = null; BufferedReader read  = null; String txtsb ="";

		try{
			pb.redirectErrorStream(true); 
			proc = pb.start(); 
		}
		catch(Exception ex){
			System.out.println("IOException");System.out.println("IOException");System.out.println("IOException");
			System.out.println(ex);
		}
		try{
			InputStream ins = proc.getInputStream(); 
			read = new BufferedReader(new InputStreamReader(ins));
			StringBuilder sb = new StringBuilder();
		 
			read.lines().forEach(line -> {
				System.out.println("line>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
				sb.append(line);
			});

			System.out.println("line> sb "+sb);
			txtsb = sb.toString();
		 
			read.close();// close the buffered reader
		}catch(IOException ex){
			System.out.println("InterruptedException");
		}
			 
		try{
			proc.waitFor(); int exitCode = proc.exitValue();
			System.out.println("exit code::"+exitCode); 
		}
		catch(InterruptedException ex){
			System.out.println("InterruptedException");System.out.println("InterruptedException");System.out.println("InterruptedException");	System.out.println(ex);
		}
	  
		proc.destroy();	System.out.println("recipes'''''''''''''''''''''''");
		System.out.println(txtsb);System.out.println("get_coinrsankrecipes'''''''''''''''''''''''");
		return txtsb;

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

 
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login";
    }

	

}
