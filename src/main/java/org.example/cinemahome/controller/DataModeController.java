package org.example.cinemahome.controller;

import org.example.cinemahome.config.DataMode;
import org.example.cinemahome.config.DataModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DataModeController {

    @Autowired
    private DataModeService dataModeService;

    @GetMapping("/mode")
    public String selectModePage() {
        return "mode/select";
    }

    @PostMapping("/mode")
    public String setMode(@RequestParam("mode") String mode) {
        if ("JSON".equalsIgnoreCase(mode)) {
            dataModeService.setMode(DataMode.JSON);
        } else {
            dataModeService.setMode(DataMode.DB);
        }
        return "redirect:/";
    }
}