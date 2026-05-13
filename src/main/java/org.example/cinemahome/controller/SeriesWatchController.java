//package org.example.cinemahome.controller;
//
//import org.example.cinemahome.dto.EpisodeDto;
//import org.example.cinemahome.service.EpisodeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//public class SeriesWatchController {
//
//    @Autowired
//    private EpisodeService episodeService;
//
//    @GetMapping("/series/{id}/watch")
//    public String watchFirst(@PathVariable Long id, RedirectAttributes attrs) {
//        EpisodeDto first = episodeService.getFirstEpisodeOfSeries(id);
//        if (first == null) {
//            return "redirect:/series";
//        }
//        return "redirect:/player/series/" + first.getId();
//    }
//}