package pl.car_dealership.infrastructure.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.car_dealership.ComponentScanMarker;

@Configuration
@ComponentScan(basePackageClasses = ComponentScanMarker.class)
@Import(PersistenceJPAConfiguration.class)
public class ApplicationConfiguration {
}
