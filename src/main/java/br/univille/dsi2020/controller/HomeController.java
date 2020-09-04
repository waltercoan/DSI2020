package br.univille.dsi2020.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping({"/home","/"})
public class HomeController {
    
    @GetMapping({"/index",""})
    public ModelAndView index(){
        return new ModelAndView("home/index","titulo","Título da aplicação");
    }    
    @GetMapping("/contato")
    @ResponseBody
    public String contato(){
        return "walter.s@univille.br";
    }
}