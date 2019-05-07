package com.capitalone.weathertracker.strategy;

import com.capitalone.weathertracker.measurements.Measurement;
import com.capitalone.weathertracker.statistics.AggregateResult;

import java.util.List;

public interface StatisticStrategy {
    AggregateResult calculate(List<Measurement> measurements, String metric);
}
