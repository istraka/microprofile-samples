package configurable;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetadataBuilder;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Random;

@ApplicationScoped
public class CustomMetricService {

    @Inject
    @ConfigProperty(name="dummy_increment")
    int increment;

    @Inject
    @RegistryType(type = MetricRegistry.Type.APPLICATION)
    MetricRegistry metricRegistry;

    @Counted
    public String hello() throws InterruptedException {
        metricRegistry.counter("custom-metric").inc();
        Thread.sleep(new Random().nextInt(100) + 1);
        return "Hello! " + increment + " : " +metricRegistry.counter("custom-metric").getCount() + "\n";
    }
}
