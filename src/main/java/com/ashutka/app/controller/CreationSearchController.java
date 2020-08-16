package com.ashutka.app.controller;

import com.ashutka.app.model.CreationModel;
import com.ashutka.app.service.CreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creations")
public class CreationSearchController {

    private final CreationService creationService;

    @Autowired
    public CreationSearchController(CreationService creationService) {
        this.creationService = creationService;
    }

    @RequestMapping("/search")
    public List<CreationModel> searchBooksAndAlbums(@RequestParam("q") String searchQuery) {
        return creationService.searchCreations(searchQuery);
    }
}
