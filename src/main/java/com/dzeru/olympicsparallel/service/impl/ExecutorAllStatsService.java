package com.dzeru.olympicsparallel.service.impl;

import com.dzeru.olympicsparallel.model.*;
import com.dzeru.olympicsparallel.service.AllStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Slf4j
@Service
public class ExecutorAllStatsService implements AllStatsService {

    private final OlympicsStatsService olympicsStatsService;

    @Autowired
    public ExecutorAllStatsService(OlympicsStatsService olympicsStatsService) {
        this.olympicsStatsService = olympicsStatsService;
    }

    @Override
    public AllStats getAllStats() {
        log.info("Start getAllStats");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            var start = System.currentTimeMillis();
            Callable<MedalsByCountry> mostMedalsByCountryCallable = olympicsStatsService::mostMedalsByCountry;
            Callable<MedalsByCountry> leastMedalsByCountryCallable = olympicsStatsService::leastMedalsByCountry;
            Callable<MedalsByCountry> meanMedalsByCountryCallable = olympicsStatsService::meanMedalsByCountry;
            Callable<MedalsBySportsman> mostMedalsBySportsmanAllCallable = olympicsStatsService::mostMedalsBySportsmanAll;
            Callable<MedalsBySportsman> leastMedalsBySportsmanAllCallable = olympicsStatsService::leastMedalsBySportsmanAll;
            Callable<MedalsTypeBySportsman> mostMedalsBySportsmanByMedalTypeCallable = olympicsStatsService::mostMedalsBySportsmanByMedalType;
            Callable<MedalsTypeBySportsman> leastMedalsBySportsmanByMedalTypeCallable = olympicsStatsService::leastMedalsBySportsmanByMedalType;
            Callable<OlympicsByCountry> mostPopularCountriesAllCallable = olympicsStatsService::mostPopularCountriesAll;
            Callable<OlympicsByCountry> leastPopularCountriesAllCallable = olympicsStatsService::leastPopularCountriesAll;
            Callable<OlympicsByCountrySport> mostPopularCountriesBySportCallable = olympicsStatsService::mostPopularCountriesBySport;
            Callable<OlympicsByCountrySport> leastPopularCountriesBySportCallable = olympicsStatsService::leastPopularCountriesBySport;
            Callable<OlympicsByCountrySport> mostPopularCountriesByDisciplineCallable = olympicsStatsService::mostPopularCountriesByDiscipline;
            Callable<OlympicsByCountrySport> leastPopularCountriesByDisciplineCallable = olympicsStatsService::leastPopularCountriesByDiscipline;

            Future<MedalsByCountry> mostMedalsByCountry = executorService.submit(mostMedalsByCountryCallable);
            Future<MedalsByCountry> leastMedalsByCountry = executorService.submit(leastMedalsByCountryCallable);
            Future<MedalsByCountry> meanMedalsByCountry = executorService.submit(meanMedalsByCountryCallable);
            Future<MedalsBySportsman> mostMedalsBySportsmanAll = executorService.submit(mostMedalsBySportsmanAllCallable);
            Future<MedalsBySportsman> leastMedalsBySportsmanAll = executorService.submit(leastMedalsBySportsmanAllCallable);
            Future<MedalsTypeBySportsman> mostMedalsBySportsmanByMedalType = executorService.submit(mostMedalsBySportsmanByMedalTypeCallable);
            Future<MedalsTypeBySportsman> leastMedalsBySportsmanByMedalType = executorService.submit(leastMedalsBySportsmanByMedalTypeCallable);
            Future<OlympicsByCountry> mostPopularCountriesAll = executorService.submit(mostPopularCountriesAllCallable);
            Future<OlympicsByCountry> leastPopularCountriesAll = executorService.submit(leastPopularCountriesAllCallable);
            Future<OlympicsByCountrySport> mostPopularCountriesBySport = executorService.submit(mostPopularCountriesBySportCallable);
            Future<OlympicsByCountrySport> leastPopularCountriesBySport = executorService.submit(leastPopularCountriesBySportCallable);
            Future<OlympicsByCountrySport> mostPopularCountriesByDiscipline = executorService.submit(mostPopularCountriesByDisciplineCallable);
            Future<OlympicsByCountrySport> leastPopularCountriesByDiscipline = executorService.submit(leastPopularCountriesByDisciplineCallable);

            var allStats = new AllStats(
                    mostMedalsByCountry.get(),
                    leastMedalsByCountry.get(),
                    meanMedalsByCountry.get(),
                    mostMedalsBySportsmanAll.get(),
                    leastMedalsBySportsmanAll.get(),
                    mostMedalsBySportsmanByMedalType.get(),
                    leastMedalsBySportsmanByMedalType.get(),
                    mostPopularCountriesAll.get(),
                    mostPopularCountriesBySport.get(),
                    mostPopularCountriesByDiscipline.get(),
                    leastPopularCountriesAll.get(),
                    leastPopularCountriesBySport.get(),
                    leastPopularCountriesByDiscipline.get()
            );
            var finish = System.currentTimeMillis();
            allStats.setTimerMillis(finish - start);
            log.info("Finish getAllStats " + (finish - start));
            return allStats;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return null;
    }
}
