package ru.greenatom.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class DocumentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentHistoryId;

    @NotBlank
    private LocalDateTime actionDate;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "document_version_id")
    private DocumentVersion documentVersion;

    @ElementCollection(targetClass = Action.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "action", joinColumns = @JoinColumn(name = "document_history_id"))
    @Enumerated(EnumType.STRING)
    private Set<Action> actions;
}
