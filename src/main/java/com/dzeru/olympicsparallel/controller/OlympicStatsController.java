package com.dzeru.olympicsparallel.controller;

import com.dzeru.olympicsparallel.model.AllStats;
import com.dzeru.olympicsparallel.model.MedalsByCountry;
import com.dzeru.olympicsparallel.service.CSVService;
import com.dzeru.olympicsparallel.service.impl.CallableAllStatsService;
import com.dzeru.olympicsparallel.service.impl.CompletableFutureAllStatsService;
import com.dzeru.olympicsparallel.service.impl.DummyAllStatsService;
import com.dzeru.olympicsparallel.service.impl.ExecutorAllStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@Controller
public class OlympicStatsController {

    private final CSVService csvService;
    private final DummyAllStatsService dummyAllStatsService;
    private final CompletableFutureAllStatsService completableFutureAllStatsService;
    private final CallableAllStatsService callableAllStatsService;
    private final ExecutorAllStatsService executorAllStatsService;

    @Autowired
    public OlympicStatsController(CSVService csvService, DummyAllStatsService dummyAllStatsService, CompletableFutureAllStatsService completableFutureAllStatsService, CallableAllStatsService callableAllStatsService, ExecutorAllStatsService executorAllStatsService) {
        this.csvService = csvService;
        this.dummyAllStatsService = dummyAllStatsService;
        this.completableFutureAllStatsService = completableFutureAllStatsService;
        this.callableAllStatsService = callableAllStatsService;
        this.executorAllStatsService = executorAllStatsService;
    }

    @GetMapping("/statsCompletableFuture")
    public String statsCompletableFuture(Model model, Principal principal) throws Exception {
        var csvStats = csvService.readAllLines();
        model.addAttribute("csvStatsSize", csvStats.size());
        model.addAttribute("csvStatsData", "City,Year,Sport,Discipline,Event,Athlete,Gender,Country_Code,Country,Event_gender,Medal");
        model.addAttribute("csvStatsExampleRow", csvStats.get(0));

        CompletableFuture<AllStats> dummyAllStatsServiceStats = CompletableFuture.supplyAsync(dummyAllStatsService::getAllStats);
        CompletableFuture<AllStats> completableFutureAllStatsServiceStats = CompletableFuture.supplyAsync(completableFutureAllStatsService::getAllStats);
        CompletableFuture<AllStats> callableAllStatsServiceStats = CompletableFuture.supplyAsync(callableAllStatsService::getAllStats);
        CompletableFuture<AllStats> executorAllStatsServiceStats = CompletableFuture.supplyAsync(executorAllStatsService::getAllStats);

        model.addAttribute("user", principal.getName());
        model.addAttribute("dummyAllStatsServiceStats", dummyAllStatsServiceStats.get());
        model.addAttribute("completableFutureAllStatsServiceStats", completableFutureAllStatsServiceStats.get());
        model.addAttribute("callableAllStatsServiceStats", callableAllStatsServiceStats.get());
        model.addAttribute("executorAllStatsServiceStats", executorAllStatsServiceStats.get());
        return "stats";
    }

    @GetMapping("/statsDummy")
    public String statsDummy(Model model, Principal principal) throws Exception {
        var csvStats = csvService.readAllLines();
        model.addAttribute("csvStatsSize", csvStats.size());
        model.addAttribute("csvStatsData", "City,Year,Sport,Discipline,Event,Athlete,Gender,Country_Code,Country,Event_gender,Medal");
        model.addAttribute("csvStatsExampleRow", csvStats.get(0));

        var dummyAllStatsServiceStats = dummyAllStatsService.getAllStats();
        var completableFutureAllStatsServiceStats = completableFutureAllStatsService.getAllStats();
        var callableAllStatsServiceStats = callableAllStatsService.getAllStats();
        var executorAllStatsServiceStats = executorAllStatsService.getAllStats();

        model.addAttribute("user", principal.getName());
        model.addAttribute("dummyAllStatsServiceStats", dummyAllStatsServiceStats);
        model.addAttribute("completableFutureAllStatsServiceStats", completableFutureAllStatsServiceStats);
        model.addAttribute("callableAllStatsServiceStats", callableAllStatsServiceStats);
        model.addAttribute("executorAllStatsServiceStats", executorAllStatsServiceStats);
        return "stats";
    }
}
