package com.autodebitapi.itau.api.model;

import com.fasterxml.jackson.annotation.JsonValue;


import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Avaliação qualitativa do produto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-24T01:21:42.654149-03:00[GMT-03:00]", comments = "Generator version: 7.11.0")
public enum Rating {
  
  ONE_STAR("ONE_STAR"),
  
  TWO_STARS("TWO_STARS"),
  
  THREE_STARS("THREE_STARS"),
  
  FOUR_STARS("FOUR_STARS"),
  
  FIVE_STARS("FIVE_STARS");

  private String value;

  Rating(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Rating fromValue(String value) {
    for (Rating b : Rating.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

