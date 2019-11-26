package configurable;


import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Metric;

import javax.inject.Inject;
import javax.inject.Provider;

public class CustomCounterMetric implements Metric, Counter {

    @Inject
    @ConfigProperty(name="dummy_increment")
    Provider<Long> increment;

    private long counter = 0;

    @Override
    public void inc() {
        counter = counter + increment.get();
    }

    @Override
    public void inc(long l) {
        counter = counter + l + increment.get() ;
    }

    @Override
    public long getCount() {
        return counter;
    }
}
