package com.capitalone.weathertracker.strategy;

import com.capitalone.weathertracker.measurements.Measurement;
import com.capitalone.weathertracker.statistics.AggregateResult;
import com.capitalone.weathertracker.statistics.Statistic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("min")
public class MinStrategy implements StatisticStrategy{

    public AggregateResult calculate(List<Measurement> measurements, String metric) {
        if(measurements.stream().anyMatch(val -> val.getMetric(metric) != null)){
            double min = measurements.stream().filter(val -> val.getMetric(metric) != null)
                    .mapToDouble(val -> val.getMetric(metric))
                    .min().getAsDouble();
            return new AggregateResult(metric, Statistic.MIN, min);
        }
        return null;
    }
}
