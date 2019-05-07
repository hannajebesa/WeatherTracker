package com.capitalone.weathertracker.services;

import com.capitalone.weathertracker.measurements.Measurement;
import com.capitalone.weathertracker.measurements.MeasurementQueryService;
import com.capitalone.weathertracker.measurements.MeasurementStore;
import com.capitalone.weathertracker.statistics.AggregateResult;
import com.capitalone.weathertracker.statistics.MeasurementAggregator;
import com.capitalone.weathertracker.statistics.Statistic;
import com.capitalone.weathertracker.strategy.StatisticStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
class MeasurementService implements MeasurementQueryService, MeasurementStore, MeasurementAggregator {

  private Map<Long, Measurement> metrics = new TreeMap<>();

  @Autowired
  private Map<Statistic, StatisticStrategy> strategies;

  @Override
  public void add(Measurement measurement) {
    metrics.put(measurement.getTimestamp().toEpochSecond(), measurement);
  }

  @Override
  public Measurement fetch(ZonedDateTime timestamp) {
    return metrics.get(timestamp.toEpochSecond());
  }

  @Override
  public List<Measurement> queryDateRange(ZonedDateTime from, ZonedDateTime to) {
    List<Measurement> result = new ArrayList<>();
    for(Long epochTime : metrics.keySet()){
      if(epochTime >= from.toEpochSecond() && epochTime < to.toEpochSecond()){
        result.add(metrics.get(epochTime));
      }
    }
    return result;
  }

  @Override
  public List<AggregateResult> analyze(List<Measurement> measurements, List<String> metrics, List<Statistic> stats) {
    List<AggregateResult> result = new ArrayList<>();

    if (measurements.size() == 0) {
      return result;
    }

    for (String metric : metrics) {
      for (Statistic statistic : stats) {
        AggregateResult aggregateResult = strategies.get(statistic).calculate(measurements, metric);
        if (aggregateResult != null) {
          result.add(aggregateResult);
        }
      }
    }
    return result;
  }
}
