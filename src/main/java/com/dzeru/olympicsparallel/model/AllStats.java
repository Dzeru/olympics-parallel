package com.dzeru.olympicsparallel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class AllStats {
    /**
     * Какая страна получила наибольшее количество медалей каждой категории (золото, серебро, бронза)
     */
    private MedalsByCountry mostMedalsByCountry;

    /**
     * Какая страна получила наименьшее количество медалей каждой категории (золото, серебро, бронза)
     */
    private MedalsByCountry leastMedalsByCountry;

    /**
     * Среднее число медалей по каждой стране
     */
    private MedalsByCountry meanMedalsByCountry;

    /**
     * Спортсмен получивший наибольшее количество медалей, не учитывая виды медалей
     */
    private MedalsBySportsman mostMedalsBySportsmanAll;

    /**
     * Спортсмен получивший наименьшее количество медалей, не учитывая виды медалей
     */
    private MedalsBySportsman leastMedalsBySportsmanAll;

    /**
     * Спортсмен получивший наибольшее количество медалей, учитывая виды медалей
     */
    private MedalsTypeBySportsman mostMedalsBySportsmanByMedalType;

    /**
     * Спортсмен получивший наименьшее количество медалей, учитывая виды медалей
     */
    private MedalsTypeBySportsman leastMedalsBySportsmanByMedalType;

    /**
     * Самые "популярные" страны для проведения олимпийских игр
     */
    private OlympicsByCountry mostPopularCountriesAll;

    /**
     * Самые "популярные" страны для проведения олимпийских игр по столбцу спорта
     */
    private OlympicsByCountrySport mostPopularCountriesBySport;

    /**
     * Самые "популярные" страны для проведения олимпийских игр по столбцу дисциплины
     */
    private OlympicsByCountrySport mostPopularCountriesByDiscipline;

    /**
     * Самые "непопулярные" страны для проведения олимпийских игр
     */
    private OlympicsByCountry leastPopularCountriesAll;

    /**
     * Самые "непопулярные" страны для проведения олимпийских игр по столбцу спорта
     */
    private OlympicsByCountrySport leastPopularCountriesBySport;

    /**
     * Самые "непопулярные" страны для проведения олимпийских игр по столбцу дисциплины
     */
    private OlympicsByCountrySport leastPopularCountriesByDiscipline;

    private long timerMillis;

    public AllStats(MedalsByCountry mostMedalsByCountry, MedalsByCountry leastMedalsByCountry,
                    MedalsByCountry meanMedalsByCountry, MedalsBySportsman mostMedalsBySportsmanAll,
                    MedalsBySportsman leastMedalsBySportsmanAll,
                    MedalsTypeBySportsman mostMedalsBySportsmanByMedalType,
                    MedalsTypeBySportsman leastMedalsBySportsmanByMedalType,
                    OlympicsByCountry mostPopularCountriesAll, OlympicsByCountrySport mostPopularCountriesBySport,
                    OlympicsByCountrySport mostPopularCountriesByDiscipline,
                    OlympicsByCountry leastPopularCountriesAll, OlympicsByCountrySport leastPopularCountriesBySport,
                    OlympicsByCountrySport leastPopularCountriesByDiscipline) {
        this.mostMedalsByCountry = mostMedalsByCountry;
        this.leastMedalsByCountry = leastMedalsByCountry;
        this.meanMedalsByCountry = meanMedalsByCountry;
        this.mostMedalsBySportsmanAll = mostMedalsBySportsmanAll;
        this.leastMedalsBySportsmanAll = leastMedalsBySportsmanAll;
        this.mostMedalsBySportsmanByMedalType = mostMedalsBySportsmanByMedalType;
        this.leastMedalsBySportsmanByMedalType = leastMedalsBySportsmanByMedalType;
        this.mostPopularCountriesAll = mostPopularCountriesAll;
        this.mostPopularCountriesBySport = mostPopularCountriesBySport;
        this.mostPopularCountriesByDiscipline = mostPopularCountriesByDiscipline;
        this.leastPopularCountriesAll = leastPopularCountriesAll;
        this.leastPopularCountriesBySport = leastPopularCountriesBySport;
        this.leastPopularCountriesByDiscipline = leastPopularCountriesByDiscipline;
    }

    public AllStats(MedalsByCountry mostMedalsByCountry, MedalsByCountry leastMedalsByCountry,
                    MedalsByCountry meanMedalsByCountry, MedalsBySportsman mostMedalsBySportsmanAll,
                    MedalsBySportsman leastMedalsBySportsmanAll,
                    MedalsTypeBySportsman mostMedalsBySportsmanByMedalType,
                    MedalsTypeBySportsman leastMedalsBySportsmanByMedalType,
                    OlympicsByCountry mostPopularCountriesAll, OlympicsByCountrySport mostPopularCountriesBySport,
                    OlympicsByCountrySport mostPopularCountriesByDiscipline,
                    OlympicsByCountry leastPopularCountriesAll, OlympicsByCountrySport leastPopularCountriesBySport,
                    OlympicsByCountrySport leastPopularCountriesByDiscipline, long timerMillis) {
        this.mostMedalsByCountry = mostMedalsByCountry;
        this.leastMedalsByCountry = leastMedalsByCountry;
        this.meanMedalsByCountry = meanMedalsByCountry;
        this.mostMedalsBySportsmanAll = mostMedalsBySportsmanAll;
        this.leastMedalsBySportsmanAll = leastMedalsBySportsmanAll;
        this.mostMedalsBySportsmanByMedalType = mostMedalsBySportsmanByMedalType;
        this.leastMedalsBySportsmanByMedalType = leastMedalsBySportsmanByMedalType;
        this.mostPopularCountriesAll = mostPopularCountriesAll;
        this.mostPopularCountriesBySport = mostPopularCountriesBySport;
        this.mostPopularCountriesByDiscipline = mostPopularCountriesByDiscipline;
        this.leastPopularCountriesAll = leastPopularCountriesAll;
        this.leastPopularCountriesBySport = leastPopularCountriesBySport;
        this.leastPopularCountriesByDiscipline = leastPopularCountriesByDiscipline;
        this.timerMillis = timerMillis;
    }
}
