package ru.greenatom.demo.service;

import ru.greenatom.demo.domain.Catalog;
import ru.greenatom.demo.domain.dto.CatalogDto;
import ru.greenatom.demo.domain.dto.SavedCatalogDto;

public interface CatalogService {
    Catalog create(CatalogDto catalogDto);
    Catalog save(SavedCatalogDto savedCatalogDto);
    Catalog readOne(Long catalogId);
}
