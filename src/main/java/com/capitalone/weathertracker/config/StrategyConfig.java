package com.capitalone.weathertracker.config;

import com.capitalone.weathertracker.statistics.Statistic;
import com.capitalone.weathertracker.strategy.StatisticStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class StrategyConfig {

    @Autowired
    public StatisticStrategy average;

    @Autowired
    public StatisticStrategy max;

    @Autowired
    public StatisticStrategy min;

    @Bean
    public Map<Statistic, StatisticStrategy> strategies(){
        Map<Statistic, StatisticStrategy> strategies = new HashMap<>();
        strategies.put(Statistic.AVERAGE, average);
        strategies.put(Statistic.MAX, max);
        strategies.put(Statistic.MIN, min);
        return strategies;
    }
}
