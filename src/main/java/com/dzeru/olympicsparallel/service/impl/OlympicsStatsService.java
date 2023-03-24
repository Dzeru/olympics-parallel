package com.dzeru.olympicsparallel.service.impl;

import com.dzeru.olympicsparallel.model.*;
import com.dzeru.olympicsparallel.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Service
public class OlympicsStatsService implements com.dzeru.olympicsparallel.service.OlympicsStatsService {

    private final CSVService csvService;

    @Autowired
    public OlympicsStatsService(CSVService csvService) {
        this.csvService = csvService;
    }

    @Override
    public MedalsByCountry mostMedalsByCountry() {
        Map<String, MedalsByCountry> countries = calculateMedalsByCountry();
        return countries.values().stream().max(Comparator.comparingInt(MedalsByCountry::sumMedals)).get();
    }

    @Override
    public MedalsByCountry leastMedalsByCountry() {
        Map<String, MedalsByCountry> countries = calculateMedalsByCountry();
        return countries.values().stream().min(Comparator.comparingInt(MedalsByCountry::sumMedals)).get();
    }

    @Override
    public MedalsByCountry meanMedalsByCountry() {
        Map<String, MedalsByCountry> countries = calculateMedalsByCountry();
        int goldMedals = 0;
        int silverMedals = 0;
        int bronzeMedals = 0;
        for (MedalsByCountry medalsByCountry : countries.values()) {
            goldMedals += medalsByCountry.getGoldMedals();
            silverMedals += medalsByCountry.getSilverMedals();
            bronzeMedals += medalsByCountry.getBronzeMedals();
        }
        int countriesCount = countries.size();
        return new MedalsByCountry("", goldMedals / countriesCount, silverMedals / countriesCount, bronzeMedals / countriesCount);
    }

    @Override
    public MedalsBySportsman mostMedalsBySportsmanAll() {
        Map<String, MedalsBySportsman> sportsmen = calculateMedalsBySportsman();
        return sportsmen.values().stream().max(Comparator.comparingInt(MedalsBySportsman::sumMedals)).get();
    }

    @Override
    public MedalsBySportsman leastMedalsBySportsmanAll() {
        Map<String, MedalsBySportsman> sportsmen = calculateMedalsBySportsman();
        return sportsmen.values().stream().min(Comparator.comparingInt(MedalsBySportsman::sumMedals)).get();
    }

    @Override
    public MedalsTypeBySportsman mostMedalsBySportsmanByMedalType() {
        Map<String, MedalsBySportsman> sportsmen = calculateMedalsBySportsman();
        MedalsTypeBySportsman medalsTypeBySportsman = new MedalsTypeBySportsman();
        medalsTypeBySportsman.setGoldMedals(sportsmen.values().stream().max(Comparator.comparingInt(MedalsBySportsman::getGoldMedals)).get());
        medalsTypeBySportsman.setSilverMedals(sportsmen.values().stream().max(Comparator.comparingInt(MedalsBySportsman::getSilverMedals)).get());
        medalsTypeBySportsman.setBronzeMedals(sportsmen.values().stream().max(Comparator.comparingInt(MedalsBySportsman::getBronzeMedals)).get());
        return medalsTypeBySportsman;
    }

    @Override
    public MedalsTypeBySportsman leastMedalsBySportsmanByMedalType() {
        Map<String, MedalsBySportsman> sportsmen = calculateMedalsBySportsman();
        MedalsTypeBySportsman medalsTypeBySportsman = new MedalsTypeBySportsman();
        medalsTypeBySportsman.setGoldMedals(sportsmen.values().stream().min(Comparator.comparingInt(MedalsBySportsman::getGoldMedals)).get());
        medalsTypeBySportsman.setSilverMedals(sportsmen.values().stream().min(Comparator.comparingInt(MedalsBySportsman::getSilverMedals)).get());
        medalsTypeBySportsman.setBronzeMedals(sportsmen.values().stream().min(Comparator.comparingInt(MedalsBySportsman::getBronzeMedals)).get());
        return medalsTypeBySportsman;
    }

    @Override
    public OlympicsByCountry mostPopularCountriesAll() {
        Map<String, OlympicsByCountry> countries = calculateOlympicsByCountry();
        return countries.values().stream().max(Comparator.comparingInt(OlympicsByCountry::getOlympics)).get();
    }

    @Override
    public OlympicsByCountrySport mostPopularCountriesBySport() {
        Map<String, OlympicsByCountrySport> countries = calculateOlympicsByCountrySport();
        return countries.values().stream().max(Comparator.comparingInt(OlympicsByCountrySport::getOlympics)).get();
    }

    @Override
    public OlympicsByCountrySport mostPopularCountriesByDiscipline() {
        Map<String, OlympicsByCountrySport> countries = calculateOlympicsByCountrySport();
        return countries.values().stream().max(Comparator.comparingInt(OlympicsByCountrySport::getOlympics)).get();
    }

    @Override
    public OlympicsByCountry leastPopularCountriesAll() {
        Map<String, OlympicsByCountry> countries = calculateOlympicsByCountry();
        return countries.values().stream().min(Comparator.comparingInt(OlympicsByCountry::getOlympics)).get();
    }

    @Override
    public OlympicsByCountrySport leastPopularCountriesBySport() {
        Map<String, OlympicsByCountrySport> countries = calculateOlympicsByCountrySport();
        return countries.values().stream().min(Comparator.comparingInt(OlympicsByCountrySport::getOlympics)).get();
    }

    @Override
    public OlympicsByCountrySport leastPopularCountriesByDiscipline() {
        Map<String, OlympicsByCountrySport> countries = calculateOlympicsByCountrySport();
        return countries.values().stream().min(Comparator.comparingInt(OlympicsByCountrySport::getOlympics)).get();
    }

    private Map<String, MedalsByCountry> calculateMedalsByCountry() {
        Map<String, MedalsByCountry> countries = new HashMap<>();
        var dataset = csvService.getOlympicDataset();
        for (OlympicDatasetRow olympicDatasetRow : dataset) {
            var country = olympicDatasetRow.getCountry();
            MedalsByCountry medalsByCountry;
            if (countries.containsKey(country)) {
                medalsByCountry = countries.get(country);
            } else {
                medalsByCountry = new MedalsByCountry(country);
            }
            var medal = olympicDatasetRow.getMedal();
            if (!medal.isBlank()) {
                medalsByCountry.addMedal(MedalType.valueOf(medal.toUpperCase()));
                countries.put(country, medalsByCountry);
            }
        }
        return countries;
    }

    private Map<String, MedalsBySportsman> calculateMedalsBySportsman() {
        Map<String, MedalsBySportsman> sportsmen = new HashMap<>();
        var dataset = csvService.getOlympicDataset();
        for (OlympicDatasetRow olympicDatasetRow : dataset) {
            var sportsman = olympicDatasetRow.getAthlete();
            MedalsBySportsman medalsBySportsman;
            if (sportsmen.containsKey(sportsman)) {
                medalsBySportsman = sportsmen.get(sportsman);
            } else {
                medalsBySportsman = new MedalsBySportsman(sportsman);
            }
            var medal = olympicDatasetRow.getMedal();
            if (!medal.isBlank()) {
                medalsBySportsman.addMedal(MedalType.valueOf(medal.toUpperCase()));
                sportsmen.put(sportsman, medalsBySportsman);
            }
        }
        return sportsmen;
    }

    private Map<String, OlympicsByCountry> calculateOlympicsByCountry() {
        Map<String, OlympicsByCountry> countries = new HashMap<>();
        var dataset = csvService.getOlympicDataset();
        for (OlympicDatasetRow olympicDatasetRow : dataset) {
            var country = olympicDatasetRow.getCountry();
            if (countries.containsKey(country)) {
                countries.put(country, new OlympicsByCountry(country, countries.get(country).getOlympics() + 1));
            } else {
                countries.put(country, new OlympicsByCountry(country, 1));
            }
        }
        return countries;
    }

    private Map<String, OlympicsByCountrySport> calculateOlympicsByCountrySport() {
        Map<String, OlympicsByCountrySport> countries = new HashMap<>();
        var dataset = csvService.getOlympicDataset();
        for (OlympicDatasetRow olympicDatasetRow : dataset) {
            var country = olympicDatasetRow.getCountry();
            var sport = olympicDatasetRow.getSport();
            var key = (country + sport).toLowerCase();
            if (countries.containsKey(key)) {
                countries.put(key, new OlympicsByCountrySport(country, sport, countries.get(key).getOlympics() + 1));
            } else {
                countries.put(key, new OlympicsByCountrySport(country, sport, 1));
            }
        }
        return countries;
    }

    private Map<String, OlympicsByCountrySport> calculateOlympicsByCountryDiscipline() {
        Map<String, OlympicsByCountrySport> countries = new HashMap<>();
        var dataset = csvService.getOlympicDataset();
        for (OlympicDatasetRow olympicDatasetRow : dataset) {
            var country = olympicDatasetRow.getCountry();
            var sport = olympicDatasetRow.getDiscipline();
            var key = (country + sport).toLowerCase();
            if (countries.containsKey(key)) {
                countries.put(key, new OlympicsByCountrySport(country, sport, countries.get(key).getOlympics() + 1));
            } else {
                countries.put(key, new OlympicsByCountrySport(country, sport, 1));
            }
        }
        return countries;
    }
}
