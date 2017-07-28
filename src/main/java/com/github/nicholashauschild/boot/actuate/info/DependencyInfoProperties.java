package com.github.nicholashauschild.boot.actuate.info;

import org.springframework.boot.info.InfoProperties;

import java.util.Properties;

/**
 * Author: nicholas.hauschild
 */
public class DependencyInfoProperties extends InfoProperties {
  public DependencyInfoProperties(final Properties entries) {
    super(entries);
  }
}
