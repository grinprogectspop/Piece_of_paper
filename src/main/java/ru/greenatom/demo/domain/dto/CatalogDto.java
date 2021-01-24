package ru.greenatom.demo.domain.dto;

import javax.validation.constraints.NotBlank;

public class CatalogDto {
  private String catalogName;

  private String description = "нет описания";

  public String getCatalogName() {
    return catalogName;
  }

  public void setCatalogName(String catalogName) {
    this.catalogName = catalogName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
