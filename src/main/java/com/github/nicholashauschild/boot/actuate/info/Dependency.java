package com.github.nicholashauschild.boot.actuate.info;

/**
 * Author: nicholas.hauschild
 */
class Dependency {
  private final String group;
  private final String name;
  private final String version;

  Dependency(final String group, final String name, final String version) {
    this.group = group;
    this.name = name;
    this.version = version;
  }

  public String getGroup() {
    return group;
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Dependency that = (Dependency) o;

    return group.equals(that.group)
            && name.equals(that.name)
            && version.equals(that.version);
  }

  @Override
  public int hashCode() {
    int result = group.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + version.hashCode();
    return result;
  }
}
