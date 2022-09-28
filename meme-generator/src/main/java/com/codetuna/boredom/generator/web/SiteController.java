package com.codetuna.boredom.generator.web;

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
import com.codetuna.boredom.generator.data.User; 
import java.io.BufferedReader;  
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException; 
import java.io.BufferedInputStream; 

@Controller
@RequestMapping("/") 
public class SiteController {
	//static pages 
    @GetMapping("/googledoodles")
    public String doodles() {
     
      return "doodles";
    }
	@GetMapping("/app")
    public String index() {
     
      return "index";
    }
	@GetMapping("/catstrivia")
    public String cats( Model model ) {
		Random randomGenerator=new Random();  
		String command ="curl -X GET https://catfact.ninja/facts?limit=1&page=" + (randomGenerator.nextInt(10) + 1);
        ProcessBuilder pb = new ProcessBuilder(command.split(" "));

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
	  
		proc.destroy();

		/*=================================*/
		String command2 ="curl -X GET https://catfact.ninja/facts?limit=12&page=" + (randomGenerator.nextInt(10) + 1);
        System.out.println(command2);
		ProcessBuilder pb2 = new ProcessBuilder(command2.split(" "));

		Process proc2 = null; BufferedReader read2  = null; String txtsb2 ="";

		try{
			pb2.redirectErrorStream(true); 
			proc2 = pb2.start(); 
		}
		catch(Exception ex){
			System.out.println("IOException");System.out.println("IOException");System.out.println("IOException");
			System.out.println(ex);
		}
		try{
			InputStream ins2 = proc2.getInputStream(); 
			read2 = new BufferedReader(new InputStreamReader(ins2));
			StringBuilder sb2 = new StringBuilder();
		 
			read2.lines().forEach(line -> {
				System.out.println("line>>>>>>>>>>>>>>>>>>>>>>>>>>>"); 
				sb2.append(line);
			});

			System.out.println("line> sb "+sb2);
			txtsb2 = sb2.toString();
		 
			read2.close();// close the buffered reader
		}catch(IOException ex){
			System.out.println("InterruptedException");
		}
			 
		try{
			proc2.waitFor(); int exitCode2 = proc2.exitValue();
			System.out.println("exit code::"+exitCode2); 
		}
		catch(InterruptedException ex){
			System.out.println("InterruptedException");System.out.println("InterruptedException");System.out.println("InterruptedException");	System.out.println(ex);
		}
	  
		proc2.destroy();

		model.addAttribute("trivia", txtsb );
		model.addAttribute("tbl_trivia", txtsb2 );
		 
		return "catstrivia";
	}


	@GetMapping("/memes")
	public String memes() {
     
		return "memes";
	}
	@GetMapping("/quotes")
	public String quotes() {
     
		return "quotes";
	}

 
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login";
    }

	

}
