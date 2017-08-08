package com.github.nicholashauschild.boot.actuate.info.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Author: nicholas.hauschild
 */
@ConfigurationProperties(prefix = "spring.info")
public class DependencyInfoContributorProperties {
  private final Dependencies dependencies = new Dependencies();

  public Dependencies getDependencies() {
    return dependencies;
  }

  public static class Dependencies {
    private Resource location = new ClassPathResource("dependency-info.properties");

    public Resource getLocation() {
      return this.location;
    }

    public void setLocation(Resource location) {
      this.location = location;
    }
  }
}
