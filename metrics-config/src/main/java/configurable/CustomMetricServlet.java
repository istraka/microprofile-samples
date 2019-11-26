package configurable;

import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetadataBuilder;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/metric")
public class CustomMetricServlet extends HttpServlet {
    @Inject
    @RegistryType(type = MetricRegistry.Type.APPLICATION)
    MetricRegistry metricRegistry;
    @Inject
    private CustomMetricService hello;

    @Override
    public void init() throws ServletException {
        Metadata metadata = new MetadataBuilder()
                .withName("custom-metric")
                .withType(MetricType.COUNTER)
                .withUnit(MetricUnits.NONE)
                .build();
        metricRegistry.register(metadata, new CustomCounterMetric());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.getWriter().print(hello.hello());
    }
}
