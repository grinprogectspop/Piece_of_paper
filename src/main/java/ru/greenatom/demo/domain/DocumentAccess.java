package ru.greenatom.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
public class DocumentAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long documentAccessId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "document_id")
    Document document;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;

    @ElementCollection(targetClass = Action.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "access", joinColumns = @JoinColumn(name = "document_access_id"))
    @Enumerated(EnumType.STRING)
    private Set<Action> accessTypes;

    public Long getDocumentAccessId() {
        return documentAccessId;
    }

    public void setDocumentAccessId(Long documentAccessId) {
        this.documentAccessId = documentAccessId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Action> getAccessTypes() {
        return accessTypes;
    }

    public void setAccessTypes(Set<Action> accessTypes) {
        this.accessTypes = accessTypes;
    }
}
