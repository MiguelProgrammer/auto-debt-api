package com.autodebitapi.itau.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * CompareRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-24T01:21:42.654149-03:00[GMT-03:00]", comments = "Generator version: 7.11.0")
public class CompareRequest {

  @Valid
  private List<@Min(1L)Long> productIds = new ArrayList<>();

  public CompareRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CompareRequest(List<@Min(1L)Long> productIds) {
    this.productIds = productIds;
  }

  public CompareRequest productIds(List<@Min(1L)Long> productIds) {
    this.productIds = productIds;
    return this;
  }

  public CompareRequest addProductIdsItem(Long productIdsItem) {
    if (this.productIds == null) {
      this.productIds = new ArrayList<>();
    }
    this.productIds.add(productIdsItem);
    return this;
  }

  /**
   * Lista de IDs dos produtos a serem comparados
   * @return productIds
   */
  @NotNull @Size(min = 2, max = 10) 
  @Schema(name = "productIds", example = "[1,2,3]", description = "Lista de IDs dos produtos a serem comparados", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("productIds")
  public List<@Min(1L)Long> getProductIds() {
    return productIds;
  }

  public void setProductIds(List<@Min(1L)Long> productIds) {
    this.productIds = productIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompareRequest compareRequest = (CompareRequest) o;
    return Objects.equals(this.productIds, compareRequest.productIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CompareRequest {\n");
    sb.append("    productIds: ").append(toIndentedString(productIds)).append("\n");
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

