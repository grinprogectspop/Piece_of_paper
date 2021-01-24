package ru.greenatom.demo.service;

import ru.greenatom.demo.domain.Catalog;
import ru.greenatom.demo.domain.dto.CatalogDto;

public interface CatalogService {
    Catalog create(CatalogDto catalogDto);
}
