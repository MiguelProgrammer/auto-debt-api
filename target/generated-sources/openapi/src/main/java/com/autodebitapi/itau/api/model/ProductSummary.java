package com.autodebitapi.itau.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ProductSummary
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-24T01:21:42.654149-03:00[GMT-03:00]", comments = "Generator version: 7.11.0")
public class ProductSummary {

  private Long id;

  private String name;

  private String description;

  private Double price;

  private String rating;

  private String specification;

  private URI url;

  public ProductSummary() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProductSummary(Long id, String name, String description, Double price, String rating, String specification, URI url) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.rating = rating;
    this.specification = specification;
    this.url = url;
  }

  public ProductSummary id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * ID único do produto
   * @return id
   */
  @NotNull 
  @Schema(name = "id", example = "1", description = "ID único do produto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductSummary name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Nome do produto
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "iPhone 15 Pro", description = "Nome do produto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductSummary description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Descrição do produto
   * @return description
   */
  @NotNull 
  @Schema(name = "description", example = "Smartphone Apple com chip A17 Pro", description = "Descrição do produto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductSummary price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Preço do produto
   * @return price
   */
  @NotNull 
  @Schema(name = "price", example = "1299.99", description = "Preço do produto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("price")
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public ProductSummary rating(String rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Avaliação do produto
   * @return rating
   */
  @NotNull 
  @Schema(name = "rating", example = "AWESOME", description = "Avaliação do produto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("rating")
  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public ProductSummary specification(String specification) {
    this.specification = specification;
    return this;
  }

  /**
   * Especificações técnicas
   * @return specification
   */
  @NotNull 
  @Schema(name = "specification", example = "128GB, Titânio Natural, 5G", description = "Especificações técnicas", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("specification")
  public String getSpecification() {
    return specification;
  }

  public void setSpecification(String specification) {
    this.specification = specification;
  }

  public ProductSummary url(URI url) {
    this.url = url;
    return this;
  }

  /**
   * URL do produto
   * @return url
   */
  @NotNull @Valid 
  @Schema(name = "url", example = "https://apple.com/iphone15pro", description = "URL do produto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("url")
  public URI getUrl() {
    return url;
  }

  public void setUrl(URI url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductSummary productSummary = (ProductSummary) o;
    return Objects.equals(this.id, productSummary.id) &&
        Objects.equals(this.name, productSummary.name) &&
        Objects.equals(this.description, productSummary.description) &&
        Objects.equals(this.price, productSummary.price) &&
        Objects.equals(this.rating, productSummary.rating) &&
        Objects.equals(this.specification, productSummary.specification) &&
        Objects.equals(this.url, productSummary.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, price, rating, specification, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductSummary {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    specification: ").append(toIndentedString(specification)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

