package com.github.nicholashauschild.boot.actuate.info.autoconfigure;

import com.github.nicholashauschild.boot.actuate.info.DependencyInfoContributor;
import org.springframework.boot.actuate.autoconfigure.ConditionalOnEnabledInfoContributor;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Author: nicholas.hauschild
 */

@Configuration
@AutoConfigureBefore(EndpointAutoConfiguration.class)
@EnableConfigurationProperties({DependencyInfoContributorProperties.class, })
public class DependencyInfoContributorAutoConfiguration {
  private final DependencyInfoContributorProperties properties;

  public DependencyInfoContributorAutoConfiguration(final DependencyInfoContributorProperties properties) {
    this.properties = properties;
  }

  @Bean
  @ConditionalOnResource(resources =
          "${spring.info.dependencies.location:classpath:dependency-info.properties}")
  @ConditionalOnEnabledInfoContributor("dependencies")
  @ConditionalOnMissingBean
  public DependencyInfoContributor dependencyInfoContributor() {
    final Resource dependencyInfo = properties.getDependencies().getLocation();
    return new DependencyInfoContributor(dependencyInfo);
  }
}
