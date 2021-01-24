package ru.greenatom.demo.domain.dto;

import lombok.Data;
import ru.greenatom.demo.domain.Catalog;
import ru.greenatom.demo.domain.DocumentType;
import ru.greenatom.demo.domain.SecrecyLevel;
import ru.greenatom.demo.validation.IsPasswordMatching;

import javax.validation.constraints.Size;
import java.util.Set;


@Data
@IsPasswordMatching
public class CreatedDocumentDto {
    @Size(min = 4, max = 100, message = "Имя документа должно быть от 4 до 100 символов")
    private String documentName;

    private SecrecyLevel secrecyLevel;

    private DocumentType documentType;

    // TODO: сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String password;

    // TODO: сделать не обязательным
    @Size(min = 4, max = 30, message = "Пароль должен быть от 4 до 30 символов")
    private String confirmPassword;

    private Set<Catalog> catalogs;

  public String getDocumentName() {
    return documentName;
  }

  public void setDocumentName(String documentName) {
    this.documentName = documentName;
  }

  public SecrecyLevel getSecrecyLevel() {
    return secrecyLevel;
  }

  public void setSecrecyLevel(SecrecyLevel secrecyLevel) {
    this.secrecyLevel = secrecyLevel;
  }

  public DocumentType getDocumentType() {
    return documentType;
  }

  public void setDocumentType(DocumentType documentType) {
    this.documentType = documentType;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public Set<Catalog> getCatalogs() {
    return catalogs;
  }

  public void setCatalogs(Set<Catalog> catalogs) {
    this.catalogs = catalogs;
  }
}


