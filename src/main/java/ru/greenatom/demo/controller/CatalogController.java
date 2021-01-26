package ru.greenatom.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.greenatom.demo.domain.Catalog;
import ru.greenatom.demo.domain.User;
import ru.greenatom.demo.domain.dto.CatalogDto;
import ru.greenatom.demo.service.CatalogService;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
      this.catalogService = catalogService;
    }

    @PostMapping
    @ResponseBody
    public Catalog create(@RequestBody CatalogDto catalogDto,
                          @AuthenticationPrincipal User user){
      return catalogService.create(catalogDto);
    }

    @GetMapping("/{catalogId}")
    @ResponseBody
    public Catalog getOne(@PathVariable Long catalogId,
                          @AuthenticationPrincipal User user){
      return catalogService.readOne(catalogId);
    }

}
