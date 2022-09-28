package com.codetuna.app.liststoday.data;

import javax.persistence.*; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data; 

@Data
@Entity 
 
public class Recipes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@Column(name  = "_Name")
	@NotEmpty(message = "Recipe title field can't be empty")
	private String title;

	@Column(name = "_Description_recipe")
	@NotEmpty(message = "Recipe description field can't be empty")
	private String description_recipe;

	@Column(name = "_Author") 
	private String author;

	@Column(name = "_Content")
	@NotEmpty(message = "Content field can't be empty")
	private String content;
	
	@Column(name = "_Status")
	@NotEmpty(message = "Status field can't be empty")
	private String status;

	public Recipes() {}
 
	public Recipes(Long id, String title, String content, String status, String author, String description_recipe) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.status = status;
        this.author = author;
		this.description_recipe = description_recipe;
	}
 
	@Override
	public String toString() {
		return "Recipes{" +				 
				", title='" + title + '\'' +
				", description='" + description_recipe + '\'' +
				", content=" + content +
                ", status =" + status +
				", author='" + author + '\'' +
				'}';
	}
}