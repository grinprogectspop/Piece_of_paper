package ru.greenatom.demo.service;

import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.Catalog;
import ru.greenatom.demo.domain.dto.CatalogDto;
import ru.greenatom.demo.domain.dto.SavedCatalogDto;
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
    this.catalogRepo.save(createdCatalog);
    return createdCatalog;
  }

  public Catalog save(SavedCatalogDto savedCatalogDto){
    Catalog savedCatalog = this.catalogRepo.findCatalogByCatalogId(savedCatalogDto.getCatalogId());
    savedCatalog.setCatalogName(savedCatalogDto.getCatalogName());
    savedCatalog.setDescription(savedCatalogDto.getDescription());
    this.catalogRepo.save(savedCatalog);
    return savedCatalog;
  }

  public Catalog readOne(Long catalogId){
    return this.catalogRepo.findCatalogByCatalogId(catalogId);
  }
}
