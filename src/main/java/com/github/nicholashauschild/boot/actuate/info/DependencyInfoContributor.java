package com.github.nicholashauschild.boot.actuate.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: nicholas.hauschild
 */
public class DependencyInfoContributor implements InfoContributor {
  private final Resource resource;

  public DependencyInfoContributor(final Resource resource) {
    this.resource = resource;
  }

  @Override
  public void contribute(final Info.Builder builder) {
    builder.withDetail("dependencies", getDependencies());
  }

  public List<Dependency> getDependencies() {
    final List<Dependency> dependencies = new ArrayList<>();

    // line format
    // --------------------------------------------
    // dependency.xyz=group/name/version || [input]
    // ^^^^^^^^^^^                       || match
    //               ^group/name/version || split =
    //                group^name^version || split /
    // --------------------------------------------
    for(final String line : resourceLines()) {
      if(line.startsWith("dependency.")) {
        final String[] parts = line.split("=");
        final String gnvUnparsed = parts[1];
        final String[] gnv = gnvUnparsed.split("/");
        dependencies.add(new Dependency(gnv[0], gnv[1], gnv[2]));
      }
    }
    return dependencies;
  }

  List<String> resourceLines() {
    try ( final InputStream is = resource.getInputStream();
          final InputStreamReader isr = new InputStreamReader(is);
          final BufferedReader br = new BufferedReader(isr); )
    {
      final List<String> lines = new ArrayList<>();

      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }

      return lines;
    } catch(final IOException e) {
      throw new RuntimeException(e);
    }
  }
}
