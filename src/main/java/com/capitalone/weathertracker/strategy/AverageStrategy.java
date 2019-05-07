package com.capitalone.weathertracker.strategy;

import com.capitalone.weathertracker.measurements.Measurement;
import com.capitalone.weathertracker.statistics.AggregateResult;
import com.capitalone.weathertracker.statistics.Statistic;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service("average")
public class AverageStrategy implements StatisticStrategy {

    public AggregateResult calculate(List<Measurement> measurements, String metric) {
        if(measurements.stream().anyMatch(val -> val.getMetric(metric) != null)){
            double avg = measurements.stream().filter(val -> val.getMetric(metric) != null)
                    .mapToDouble(val -> val.getMetric(metric))
                    .average().getAsDouble();
            double truncatedAvg = BigDecimal.valueOf(avg)
                    .setScale(1, RoundingMode.HALF_UP)
                    .doubleValue();
            return new AggregateResult(metric, Statistic.AVERAGE, truncatedAvg);
        }
        return null;
    }
}
