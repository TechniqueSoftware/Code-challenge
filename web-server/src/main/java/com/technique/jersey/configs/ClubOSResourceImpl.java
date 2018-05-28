package com.technique.jersey.configs;

import java.lang.annotation.Annotation;

public final class ClubOSResourceImpl implements ClubOSResource {

  final String value;

  private ClubOSResourceImpl(final String value) {
    this.value = value;
  }

  public static ClubOSResource annotationFor(String value) {
    return new ClubOSResourceImpl(value);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ClubOSResource)) {
      return false;
    }

    ClubOSResource other = (ClubOSResource) o;
    return value.equals(other.value());
  }

  @Override
  public String toString() {
    return "@" + ClubOSResource.class.getName() + "(value=" + value + ")";
  }

  @Override
  public int hashCode() {
    return (127 * "value".hashCode()) ^ value.hashCode();
  }

  public Class<? extends Annotation> annotationType() {
    return ClubOSResource.class;
  }

  public String value() {
    return value;
  }
}
