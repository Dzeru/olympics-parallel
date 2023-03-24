package com.dzeru.olympicsparallel.controller;

import com.dzeru.olympicsparallel.service.InfinityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@Controller
public class InfinityController {

    private final InfinityService infinityService;

    public InfinityController(InfinityService infinityService) {
        this.infinityService = infinityService;
    }

    @GetMapping("/infinity")
    public String infinity(Model model, Principal principal) {
        model.addAttribute("user", principal.getName());
        model.addAttribute("infinityThreadCount", InfinityService.THREAD_COUNT);
        model.addAttribute("infinityBatchStep", InfinityService.BATCH_STEP);

        var infinityItems = infinityService.getInfinityItems();
        model.addAttribute("infinityItem", infinityItems);
        model.addAttribute("infinityItemSize", infinityItems.size());
        return "infinity";
    }
}
