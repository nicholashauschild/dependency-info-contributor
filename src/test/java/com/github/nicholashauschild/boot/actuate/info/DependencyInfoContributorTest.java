package com.github.nicholashauschild.boot.actuate.info;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Author: nicholas.hauschild
 */
public class DependencyInfoContributorTest {
  private DependencyInfoContributor testObj;

  @Before
  public void setup() {
    final Resource resource = new ClassPathResource("dependency-info_test.properties");
    testObj = new DependencyInfoContributor(resource);
  }

  @Test
  public void testGetDependencies_happy() {
    final List<Dependency> dependencies = testObj.getDependencies();

    assertEquals(3, dependencies.size());
    assertEquals(
            dep("org.springframework.boot",
                    "spring-boot-autoconfigure",
                    "1.4.6.RELEASE"),
            dependencies.get(0));
    assertEquals(
            dep("org.eclipse.jetty",
                    "jetty-webapp",
                    "9.3.18.v20170406"),
            dependencies.get(1));
    assertEquals(
            dep("org.springframework.boot",
                    "spring-boot-starter-actuator",
                    "1.4.6.RELEASE"),
            dependencies.get(2));
  }

  @Test
  public void testGetDependencies_emptyFile() {
    final Resource resource = new ClassPathResource("dependency-info_test-empty.properties");
    testObj = new DependencyInfoContributor(resource);
    final List<Dependency> dependencies = testObj.getDependencies();

    assertTrue(dependencies.isEmpty());
  }

  @Test
  public void testGetDependencies_missingDependencies() {
    final Resource resource = new ClassPathResource("dependency-info_test-missing-dependencies.properties");
    testObj = new DependencyInfoContributor(resource);
    final List<Dependency> dependencies = testObj.getDependencies();

    assertEquals(2, dependencies.size());
    assertEquals(
            dep("org.springframework.boot",
                    "spring-boot-autoconfigure",
                    "1.4.6.RELEASE"),
            dependencies.get(0));
    assertEquals(
            dep("org.springframework.boot",
                    "spring-boot-starter-actuator",
                    "1.4.6.RELEASE"),
            dependencies.get(1));
  }

  Dependency dep(final String g, final String n, final String v) {
    return new Dependency(g, n, v);
  }
}
