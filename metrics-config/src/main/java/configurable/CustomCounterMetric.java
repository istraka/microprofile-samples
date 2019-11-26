package configurable;


import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Metric;

import javax.inject.Inject;

public class CustomCounterMetric implements Metric, Counter {

    @Inject
    @ConfigProperty(name="dummy_increment")
    int increment;

    private long counter = 0;

    @Override
    public void inc() {
        counter = counter + increment;
    }

    @Override
    public void inc(long l) {
        counter = counter + l + increment ;
    }

    @Override
    public long getCount() {
        return counter;
    }
}
