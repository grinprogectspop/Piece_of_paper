package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.Catalog;

public interface CatalogRepo extends JpaRepository<Catalog, Long> {
  Catalog findByCatalogName(String catalogName);
}
