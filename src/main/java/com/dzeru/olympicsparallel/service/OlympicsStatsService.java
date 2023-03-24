package com.dzeru.olympicsparallel.service;

import com.dzeru.olympicsparallel.model.*;

public interface OlympicsStatsService {
    /**
     * Какая страна получила наибольшее количество медалей каждой категории (золото, серебро, бронза)
     */
    MedalsByCountry mostMedalsByCountry();

    /**
     * Какая страна получила наименьшее количество медалей каждой категории (золото, серебро, бронза)
     */
    MedalsByCountry leastMedalsByCountry();

    /**
     * Среднее число медалей по каждой стране
     */
    MedalsByCountry meanMedalsByCountry();

    /**
     * Спортсмен получивший наибольшее количество медалей, не учитывая виды медалей
     */
    MedalsBySportsman mostMedalsBySportsmanAll();

    /**
     * Спортсмен получивший наименьшее количество медалей, не учитывая виды медалей
     */
    MedalsBySportsman leastMedalsBySportsmanAll();

    /**
     * Спортсмен получивший наибольшее количество медалей, учитывая виды медалей
     */
    MedalsTypeBySportsman mostMedalsBySportsmanByMedalType();

    /**
     * Спортсмен получивший наименьшее количество медалей, учитывая виды медалей
     */
    MedalsTypeBySportsman leastMedalsBySportsmanByMedalType();

    /**
     * Самые "популярные" страны для проведения олимпийских игр
     */
    OlympicsByCountry mostPopularCountriesAll();

    /**
     * Самые "популярные" страны для проведения олимпийских игр по столбцу спорта
     */
    OlympicsByCountrySport mostPopularCountriesBySport();

    /**
     * Самые "популярные" страны для проведения олимпийских игр по столбцу дисциплины
     */
    OlympicsByCountrySport mostPopularCountriesByDiscipline();

    /**
     * Самые "непопулярные" страны для проведения олимпийских игр
     */
    OlympicsByCountry leastPopularCountriesAll();

    /**
     * Самые "непопулярные" страны для проведения олимпийских игр по столбцу спорта
     */
    OlympicsByCountrySport leastPopularCountriesBySport();

    /**
     * Самые "непопулярные" страны для проведения олимпийских игр по столбцу дисциплины
     */
    OlympicsByCountrySport leastPopularCountriesByDiscipline();
}
