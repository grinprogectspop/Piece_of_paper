package ru.greenatom.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class TemplateDocument {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long templateDocumentId;

  @NotBlank
  private String templateName;

  @NotBlank
  private String description;

  @NotBlank
  private String templateUrl;

  public Long getTemplateDocumentId() {
    return templateDocumentId;
  }

  public void setTemplateDocumentId(Long templateDocumentId) {
    this.templateDocumentId = templateDocumentId;
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTemplateUrl() {
    return templateUrl;
  }

  public void setTemplateUrl(String templateUrl) {
    this.templateUrl = templateUrl;
  }
}
