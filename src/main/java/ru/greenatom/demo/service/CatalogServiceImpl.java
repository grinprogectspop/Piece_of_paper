package ru.greenatom.demo.service;

import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.Catalog;
import ru.greenatom.demo.domain.dto.CatalogDto;
import ru.greenatom.demo.repo.CatalogRepo;

@Service
public class CatalogServiceImpl implements CatalogService {

  private final CatalogRepo catalogRepo;

  public CatalogServiceImpl(CatalogRepo catalogRepo) {
    this.catalogRepo = catalogRepo;
  }

  @Override
  public Catalog create(CatalogDto catalogDto) {
    Catalog createdCatalog = new Catalog();
    createdCatalog.setCatalogName(catalogDto.getCatalogName());
    catalogRepo.save(createdCatalog);
    return createdCatalog;

  }
}
