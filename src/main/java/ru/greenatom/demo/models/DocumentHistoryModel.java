package ru.greenatom.demo.models;

import lombok.Data;
import ru.greenatom.demo.domain.Action;
import ru.greenatom.demo.domain.Document;
import ru.greenatom.demo.domain.DocumentVersion;
import ru.greenatom.demo.domain.User;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class DocumentHistoryModel {

    private Long documentHistoryId;
    private LocalDateTime actionDate;
    private String description;
    private User author;
    private Document document;
    private DocumentVersion documentVersion;
    private Set<Action> actions;
}
