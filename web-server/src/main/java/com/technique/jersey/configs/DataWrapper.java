package com.technique.jersey.configs;

public class DataWrapper<T> {

  private final T data;

  public DataWrapper(final T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }
}
