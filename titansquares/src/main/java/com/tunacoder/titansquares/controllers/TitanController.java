package com.tunacoder.titansquares.controllers;

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

import com.tunacoder.titansquares.models.Titan;
import com.tunacoder.titansquares.services.TitanService;
import com.tunacoder.titansquares.repository.TitanRepository;

@Controller
public class TitanController {

    private final TitanService titanService;

    @Autowired
    public TitanController(TitanService titanService) {
        this.titanService = titanService;
    }
    @GetMapping("/")
    public String index(Titan titan) {
        return "home";
    }
    @GetMapping("/home")
    public String home(Titan titan) {
        return "home";
    }
    @GetMapping("/nftarena")
    public String nftarena(Titan titan) {
        return "nft";
    }

    @GetMapping("/listtitans")
    public String index(Titan titan, Model model) {
        List<Titan> titanList = (List<Titan>) titanService.getTitans();
        model.addAttribute("titans", titanList.isEmpty() ? Collections.EMPTY_LIST : titanList);
        return "list";
    }


    
    @PostMapping("/addtitans")
    public String addTitan(@Valid Titan titan, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "home";
        }
        titanService.createTitan(titan);
        model.addAttribute("titans", titanService.getTitans());
        return "redirect:/listtitans";
    }

    // @GetMapping("/addconference")
    // public String showAddCourseForm(Titan course) {
    //     return "add-conference";
    // }

    // @PostMapping("/addconference")
    // public String addCourse(@Valid Titan course, BindingResult result, Model model){
    //     if (result.hasErrors()) {
    //         return "add-conference";
    //     }
    //     titanService.createTitan(course);
    //     model.addAttribute("conferences", titanService.getTitans());
    //     return "redirect:/index";
    // }

    @GetMapping("/update-titan/{id}")
    public String showUpdateCourseForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("titan", titanService.getTitanbyId(id).get());
        return "update-titan";
    }

    @PutMapping("/update-titan/{id}")
    public String updateCourse(@PathVariable("id") Long id, @Valid Titan titan, BindingResult result, Model model) {
        if (result.hasErrors()) {
            titan.setId(id);
            return "update-titan";
        }
        titanService.updateTitan(id, titan);
        model.addAttribute("titans", titanService.getTitans());
        return "redirect:/listtitans";
    }

    // @DeleteMapping("/delete/{id}")
    // public String deleteCourse(@PathVariable("id") Long id, Model model) {
    // 	titanService.deleteTitan(id);
    //     model.addAttribute("conferences", titanService.getTitans());
    //     return "redirect:/index";
    // }
}
