package com.autodebitapi.itau.api.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CompareResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-24T01:21:42.654149-03:00[GMT-03:00]", comments = "Generator version: 7.11.0")
public class CompareResponse {

  @Valid
  private List<@Valid ProductSummary> products = new ArrayList<>();

  @Valid
  private Map<String, List<CompareResponseComparisonValueInner>> comparison = new HashMap<>();

  public CompareResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CompareResponse(List<@Valid ProductSummary> products, Map<String, List<CompareResponseComparisonValueInner>> comparison) {
    this.products = products;
    this.comparison = comparison;
  }

  public CompareResponse products(List<@Valid ProductSummary> products) {
    this.products = products;
    return this;
  }

  public CompareResponse addProductsItem(ProductSummary productsItem) {
    if (this.products == null) {
      this.products = new ArrayList<>();
    }
    this.products.add(productsItem);
    return this;
  }

  /**
   * Lista resumida dos produtos comparados
   * @return products
   */
  @NotNull @Valid 
  @Schema(name = "products", description = "Lista resumida dos produtos comparados", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("products")
  public List<@Valid ProductSummary> getProducts() {
    return products;
  }

  public void setProducts(List<@Valid ProductSummary> products) {
    this.products = products;
  }

  public CompareResponse comparison(Map<String, List<CompareResponseComparisonValueInner>> comparison) {
    this.comparison = comparison;
    return this;
  }

  public CompareResponse putComparisonItem(String key, List<CompareResponseComparisonValueInner> comparisonItem) {
    if (this.comparison == null) {
      this.comparison = new HashMap<>();
    }
    this.comparison.put(key, comparisonItem);
    return this;
  }

  /**
   * Mapa de características comparadas
   * @return comparison
   */
  @NotNull @Valid 
  @Schema(name = "comparison", example = "{\"price\":[1299.99,1199.99,699.99],\"rating\":[\"AWESOME\",\"AWESOME\",\"GOOD\"],\"RAM\":[\"N/A\",\"N/A\",\"N/A\"],\"Storage\":[\"128GB\",\"256GB\",\"128GB\"]}", description = "Mapa de características comparadas", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("comparison")
  public Map<String, List<CompareResponseComparisonValueInner>> getComparison() {
    return comparison;
  }

  public void setComparison(Map<String, List<CompareResponseComparisonValueInner>> comparison) {
    this.comparison = comparison;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompareResponse compareResponse = (CompareResponse) o;
    return Objects.equals(this.products, compareResponse.products) &&
        Objects.equals(this.comparison, compareResponse.comparison);
  }

  @Override
  public int hashCode() {
    return Objects.hash(products, comparison);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CompareResponse {\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
    sb.append("    comparison: ").append(toIndentedString(comparison)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

