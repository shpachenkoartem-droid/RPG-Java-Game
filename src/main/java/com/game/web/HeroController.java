package com.game.web;

import com.game.model.createrequest.HeroCreateRequest;
import com.game.model.response.HeroResponse;
import com.game.service.HeroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Контроллер принимает HTTP и отдаёт сервису
@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @PostMapping
    public HeroResponse create(@RequestBody HeroCreateRequest request) {
        return heroService.create(request);
    }

    @GetMapping
    public List<HeroResponse> findAll() {
        return heroService.findAll();
    }
}