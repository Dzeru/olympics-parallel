package com.dzeru.olympicsparallel.service.impl;

import com.dzeru.olympicsparallel.model.*;
import com.dzeru.olympicsparallel.service.AllStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CompletableFutureAllStatsService implements AllStatsService {
    private final OlympicsStatsService olympicsStatsService;

    @Autowired
    public CompletableFutureAllStatsService(OlympicsStatsService olympicsStatsService) {
        this.olympicsStatsService = olympicsStatsService;
    }

    @Override
    public AllStats getAllStats() {
        log.info("Start getAllStats");
        CompletableFuture<MedalsByCountry> mostMedalsByCountry = CompletableFuture.supplyAsync(olympicsStatsService::mostMedalsByCountry);
        CompletableFuture<MedalsByCountry> leastMedalsByCountry = CompletableFuture.supplyAsync(olympicsStatsService::leastMedalsByCountry);
        CompletableFuture<MedalsByCountry> meanMedalsByCountry = CompletableFuture.supplyAsync(olympicsStatsService::meanMedalsByCountry);
        CompletableFuture<MedalsBySportsman> mostMedalsBySportsmanAll = CompletableFuture.supplyAsync(olympicsStatsService::mostMedalsBySportsmanAll);
        CompletableFuture<MedalsBySportsman> leastMedalsBySportsmanAll = CompletableFuture.supplyAsync(olympicsStatsService::leastMedalsBySportsmanAll);
        CompletableFuture<MedalsTypeBySportsman> mostMedalsBySportsmanByMedalType = CompletableFuture.supplyAsync(olympicsStatsService::mostMedalsBySportsmanByMedalType);
        CompletableFuture<MedalsTypeBySportsman> leastMedalsBySportsmanByMedalType = CompletableFuture.supplyAsync(olympicsStatsService::leastMedalsBySportsmanByMedalType);
        CompletableFuture<OlympicsByCountry> mostPopularCountriesAll = CompletableFuture.supplyAsync(olympicsStatsService::mostPopularCountriesAll);
        CompletableFuture<OlympicsByCountrySport> mostPopularCountriesBySport = CompletableFuture.supplyAsync(olympicsStatsService::mostPopularCountriesBySport);
        CompletableFuture<OlympicsByCountrySport> mostPopularCountriesByDiscipline = CompletableFuture.supplyAsync(olympicsStatsService::mostPopularCountriesByDiscipline);
        CompletableFuture<OlympicsByCountry> leastPopularCountriesAll = CompletableFuture.supplyAsync(olympicsStatsService::leastPopularCountriesAll);
        CompletableFuture<OlympicsByCountrySport> leastPopularCountriesBySport = CompletableFuture.supplyAsync(olympicsStatsService::leastPopularCountriesBySport);
        CompletableFuture<OlympicsByCountrySport> leastPopularCountriesByDiscipline = CompletableFuture.supplyAsync(olympicsStatsService::leastPopularCountriesByDiscipline);

        try {
            var start = System.currentTimeMillis();
            var allStats = new AllStats(mostMedalsByCountry.get(), leastMedalsByCountry.get(), meanMedalsByCountry.get(),
                    mostMedalsBySportsmanAll.get(), leastMedalsBySportsmanAll.get(), mostMedalsBySportsmanByMedalType.get(),
                    leastMedalsBySportsmanByMedalType.get(), mostPopularCountriesAll.get(),
                    mostPopularCountriesBySport.get(), mostPopularCountriesByDiscipline.get(),
                    leastPopularCountriesAll.get(), leastPopularCountriesBySport.get(),
                    leastPopularCountriesByDiscipline.get());
            var finish = System.currentTimeMillis();
            allStats.setTimerMillis(finish - start);
            log.info("Finish getAllStats " + (finish - start));
            return allStats;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
