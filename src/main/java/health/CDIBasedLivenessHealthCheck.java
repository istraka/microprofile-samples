package health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;


@Liveness
@ApplicationScoped
public class CDIBasedLivenessHealthCheck implements HealthCheck {


    @Inject
    @ConfigProperty(name = "dummy_live")
    private Provider<Boolean> live;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("dummy")
                .state(live.get())
                .build();
    }
}
