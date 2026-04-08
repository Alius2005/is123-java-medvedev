package org.example.cinemahome.controller;

import org.example.cinemahome.service.ActorService;
import org.example.cinemahome.dto.ActorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActorController {
    @Autowired
    private ActorService actorService;

    @GetMapping("/actors")
    public String listActors(Model model) {
        model.addAttribute("actors", actorService.getAllActors());
        return "actor/list";
    }
}
