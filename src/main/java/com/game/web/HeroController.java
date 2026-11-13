package com.game.web;

import com.game.model.heroes.Hero;
import com.game.service.HeroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @PostMapping
    public Hero createHero(@RequestBody Hero hero) {
        return heroService.createHero(hero);
    }
}