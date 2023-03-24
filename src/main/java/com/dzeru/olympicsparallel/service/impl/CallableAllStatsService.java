package com.dzeru.olympicsparallel.service.impl;

import com.dzeru.olympicsparallel.model.*;
import com.dzeru.olympicsparallel.service.AllStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j
@Service
public class CallableAllStatsService implements AllStatsService {

    private final OlympicsStatsService olympicsStatsService;

    @Autowired
    public CallableAllStatsService(OlympicsStatsService olympicsStatsService) {
        this.olympicsStatsService = olympicsStatsService;
    }

    @Override
    public AllStats getAllStats() {
        try {
            log.info("Start getAllStats");
            var start = System.currentTimeMillis();
            Callable<MedalsByCountry> mostMedalsByCountryCallable = olympicsStatsService::mostMedalsByCountry;
            FutureTask<MedalsByCountry> mostMedalsByCountryFutureTask = new FutureTask<>(mostMedalsByCountryCallable);

            Callable<MedalsByCountry> leastMedalsByCountryCallable = olympicsStatsService::leastMedalsByCountry;
            FutureTask<MedalsByCountry> leastMedalsByCountryFutureTask = new FutureTask<>(leastMedalsByCountryCallable);

            Callable<MedalsByCountry> meanMedalsByCountryCallable = olympicsStatsService::meanMedalsByCountry;
            FutureTask<MedalsByCountry> meanMedalsByCountryFutureTask = new FutureTask<>(meanMedalsByCountryCallable);

            Callable<MedalsBySportsman> mostMedalsBySportsmanAllCallable = olympicsStatsService::mostMedalsBySportsmanAll;
            FutureTask<MedalsBySportsman> mostMedalsBySportsmanAllFutureTask = new FutureTask<>(mostMedalsBySportsmanAllCallable);

            Callable<MedalsBySportsman> leastMedalsBySportsmanAllCallable = olympicsStatsService::leastMedalsBySportsmanAll;
            FutureTask<MedalsBySportsman> leastMedalsBySportsmanAllFutureTask = new FutureTask<>(leastMedalsBySportsmanAllCallable);

            Callable<MedalsTypeBySportsman> mostMedalsTypeBySportsmanByMedalTypeCallable = olympicsStatsService::mostMedalsBySportsmanByMedalType;
            FutureTask<MedalsTypeBySportsman> mostMedalsTypeBySportsmanByMedalTypeFutureTask = new FutureTask<>(mostMedalsTypeBySportsmanByMedalTypeCallable);

            Callable<MedalsTypeBySportsman> leastMedalsTypeBySportsmanByMedalTypeCallable = olympicsStatsService::leastMedalsBySportsmanByMedalType;
            FutureTask<MedalsTypeBySportsman> leastMedalsTypeBySportsmanByMedalTypeFutureTask = new FutureTask<>(leastMedalsTypeBySportsmanByMedalTypeCallable);

            Callable<OlympicsByCountry> mostPopularCountriesAllCallable = olympicsStatsService::mostPopularCountriesAll;
            FutureTask<OlympicsByCountry> mostPopularCountriesAllFutureTask = new FutureTask<>(mostPopularCountriesAllCallable);

            Callable<OlympicsByCountry> leastPopularCountriesAllCallable = olympicsStatsService::leastPopularCountriesAll;
            FutureTask<OlympicsByCountry> leastPopularCountriesAllFutureTask = new FutureTask<>(leastPopularCountriesAllCallable);

            Callable<OlympicsByCountrySport> mostPopularCountriesBySportCallable = olympicsStatsService::mostPopularCountriesBySport;
            FutureTask<OlympicsByCountrySport> mostPopularCountriesBySportFutureTask = new FutureTask<>(mostPopularCountriesBySportCallable);

            Callable<OlympicsByCountrySport> leastPopularCountriesBySportCallable = olympicsStatsService::leastPopularCountriesBySport;
            FutureTask<OlympicsByCountrySport> leastPopularCountriesBySportFutureTask = new FutureTask<>(leastPopularCountriesBySportCallable);

            Callable<OlympicsByCountrySport> mostPopularCountriesByDisciplineCallable = olympicsStatsService::mostPopularCountriesByDiscipline;
            FutureTask<OlympicsByCountrySport> mostPopularCountriesByDisciplineFutureTask = new FutureTask<>(mostPopularCountriesByDisciplineCallable);

            Callable<OlympicsByCountrySport> leastPopularCountriesByDisciplineCallable = olympicsStatsService::leastPopularCountriesByDiscipline;
            FutureTask<OlympicsByCountrySport> leastPopularCountriesByDisciplineFutureTask = new FutureTask<>(leastPopularCountriesByDisciplineCallable);

            new Thread(mostMedalsByCountryFutureTask).start();
            new Thread(leastMedalsByCountryFutureTask).start();
            new Thread(meanMedalsByCountryFutureTask).start();
            new Thread(mostMedalsBySportsmanAllFutureTask).start();
            new Thread(leastMedalsBySportsmanAllFutureTask).start();
            new Thread(mostMedalsTypeBySportsmanByMedalTypeFutureTask).start();
            new Thread(leastMedalsTypeBySportsmanByMedalTypeFutureTask).start();
            new Thread(mostPopularCountriesAllFutureTask).start();
            new Thread(mostPopularCountriesBySportFutureTask).start();
            new Thread(mostPopularCountriesByDisciplineFutureTask).start();
            new Thread(leastPopularCountriesAllFutureTask).start();
            new Thread(leastPopularCountriesBySportFutureTask).start();
            new Thread(leastPopularCountriesByDisciplineFutureTask).start();

            var allStats = new AllStats(
                    mostMedalsByCountryFutureTask.get(),
                    leastMedalsByCountryFutureTask.get(),
                    meanMedalsByCountryFutureTask.get(),
                    mostMedalsBySportsmanAllFutureTask.get(),
                    leastMedalsBySportsmanAllFutureTask.get(),
                    mostMedalsTypeBySportsmanByMedalTypeFutureTask.get(),
                    leastMedalsTypeBySportsmanByMedalTypeFutureTask.get(),
                    mostPopularCountriesAllFutureTask.get(),
                    mostPopularCountriesBySportFutureTask.get(),
                    mostPopularCountriesByDisciplineFutureTask.get(),
                    leastPopularCountriesAllFutureTask.get(),
                    leastPopularCountriesBySportFutureTask.get(),
                    leastPopularCountriesByDisciplineFutureTask.get()
            );
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
