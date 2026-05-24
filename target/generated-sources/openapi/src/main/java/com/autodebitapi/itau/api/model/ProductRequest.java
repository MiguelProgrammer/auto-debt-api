package com.autodebitapi.itau.api.model;

import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * ProductRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-24T01:21:42.654149-03:00[GMT-03:00]", comments = "Generator version: 7.11.0")
public class ProductRequest {

  private @Nullable Long id;

  private String name;

  private String description;

  private Double price;

  private Rating rating;

  private String specification;

  private URI url;

  public ProductRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProductRequest(String name, String description, Double price, Rating rating, String specification, URI url) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.rating = rating;
    this.specification = specification;
    this.url = url;
  }

  public ProductRequest id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * ID único do produto (opcional para criação)
   * @return id
   */
  
  @Schema(name = "id", example = "1", description = "ID único do produto (opcional para criação)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ProductRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Nome do produto
   * @return name
   */
  @NotNull @Size(min = 1, max = 255) 
  @Schema(name = "name", example = "iPhone 15 Pro", description = "Nome do produto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Descrição detalhada do produto
   * @return description
   */
  @NotNull @Size(min = 1, max = 1000) 
  @Schema(name = "description", example = "Smartphone Apple com chip A17 Pro e câmera de 48MP", description = "Descrição detalhada do produto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductRequest price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Preço do produto em USD
   * minimum: 0.01
   * @return price
   */
  @NotNull @DecimalMin("0.01") 
  @Schema(name = "price", example = "1299.99", description = "Preço do produto em USD", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("price")
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public ProductRequest rating(Rating rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Get rating
   * @return rating
   */
  @NotNull @Valid 
  @Schema(name = "rating", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("rating")
  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  public ProductRequest specification(String specification) {
    this.specification = specification;
    return this;
  }

  /**
   * Especificações técnicas do produto
   * @return specification
   */
  @NotNull @Size(min = 1, max = 500) 
  @Schema(name = "specification", example = "128GB, Titânio Natural, 5G", description = "Especificações técnicas do produto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("specification")
  public String getSpecification() {
    return specification;
  }

  public void setSpecification(String specification) {
    this.specification = specification;
  }

  public ProductRequest url(URI url) {
    this.url = url;
    return this;
  }

  /**
   * URL oficial do produto
   * @return url
   */
  @NotNull @Valid 
  @Schema(name = "url", example = "https://apple.com/iphone15pro", description = "URL oficial do produto", requiredMode = Schema.RequiredMode.REQUIRED)
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
    ProductRequest productRequest = (ProductRequest) o;
    return Objects.equals(this.id, productRequest.id) &&
        Objects.equals(this.name, productRequest.name) &&
        Objects.equals(this.description, productRequest.description) &&
        Objects.equals(this.price, productRequest.price) &&
        Objects.equals(this.rating, productRequest.rating) &&
        Objects.equals(this.specification, productRequest.specification) &&
        Objects.equals(this.url, productRequest.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, price, rating, specification, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductRequest {\n");
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

