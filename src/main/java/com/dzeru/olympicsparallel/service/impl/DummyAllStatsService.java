package com.dzeru.olympicsparallel.service.impl;

import com.dzeru.olympicsparallel.model.AllStats;
import com.dzeru.olympicsparallel.service.AllStatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DummyAllStatsService implements AllStatsService {

    private final OlympicsStatsService olympicsStatsService;

    @Autowired
    public DummyAllStatsService(OlympicsStatsService olympicsStatsService) {
        this.olympicsStatsService = olympicsStatsService;
    }

    @Override
    public AllStats getAllStats() {
        log.info("Start getAllStats");
        var start = System.currentTimeMillis();
        var allStats = new AllStats(
                olympicsStatsService.mostMedalsByCountry(),
                olympicsStatsService.leastMedalsByCountry(),
                olympicsStatsService.meanMedalsByCountry(),
                olympicsStatsService.mostMedalsBySportsmanAll(),
                olympicsStatsService.leastMedalsBySportsmanAll(),
                olympicsStatsService.mostMedalsBySportsmanByMedalType(),
                olympicsStatsService.leastMedalsBySportsmanByMedalType(),
                olympicsStatsService.mostPopularCountriesAll(),
                olympicsStatsService.mostPopularCountriesBySport(),
                olympicsStatsService.mostPopularCountriesByDiscipline(),
                olympicsStatsService.leastPopularCountriesAll(),
                olympicsStatsService.leastPopularCountriesBySport(),
                olympicsStatsService.leastPopularCountriesByDiscipline()
        );
        var finish = System.currentTimeMillis();
        allStats.setTimerMillis(finish - start);
        log.info("Finish getAllStats " + (finish - start));
        return allStats;
    }
}
