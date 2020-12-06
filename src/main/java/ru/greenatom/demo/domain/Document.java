package ru.greenatom.demo.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document")
@Data
public class Document {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @JsonView(Views.Id.class)
    private String id;
    @JsonView(Views.IdName.class)
    private String name;
    @JsonView(Views.FullProfile.class)
    private String text;
    private String password;



}
