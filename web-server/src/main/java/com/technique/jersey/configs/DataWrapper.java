package com.technique.jersey.configs;

public class DataWrapper<T> {

  private final T data;
  private T error;

  public DataWrapper(final T data) {
    this.data = data;
  }

  public T getError() {
    return error;
  }

  public void setError(final T error) {
    this.error = error;
  }

  public T getData() {
    return data;
  }
}
