package com.github.nicholashauschild.boot.actuate.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoPropertiesInfoContributor;
import org.springframework.core.env.PropertySource;

/**
 * Author: nicholas.hauschild
 */
public class DependencyInfoContributor extends InfoPropertiesInfoContributor<DependencyInfoProperties> {
  protected DependencyInfoContributor(DependencyInfoProperties properties) {
    super(properties, Mode.SIMPLE);
  }

  protected DependencyInfoContributor(DependencyInfoProperties properties, Mode mode) {
    super(properties, mode);
  }

  @Override
  protected PropertySource<?> toSimplePropertySource() {
    return null;
  }

  @Override
  public void contribute(final Info.Builder builder) {

  }
}
