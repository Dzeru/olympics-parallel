package com.dzeru.olympicsparallel.controller;

import com.dzeru.olympicsparallel.service.InfinityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class UtilController {

    private final InfinityService infinityService;

    public UtilController(InfinityService infinityService) {
        this.infinityService = infinityService;
    }

    @GetMapping("/startInfinity")
    public String startInfinity() {
        CompletableFuture.runAsync(infinityService::infinity);
        return "infinity";
    }

    @GetMapping("/infThreadCount/{infThreadCount}")
    public int infThreadCount(@PathVariable("infThreadCount") int infThreadCount) {
        InfinityService.THREAD_COUNT = infThreadCount;
        return InfinityService.THREAD_COUNT;
    }

    @GetMapping("/infBatchStep/{infBatchStep}")
    public int infBatchStep(@PathVariable("infBatchStep") int infBatchStep) {
        InfinityService.BATCH_STEP = infBatchStep;
        return InfinityService.BATCH_STEP;
    }
}
