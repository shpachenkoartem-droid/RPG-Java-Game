package com.game.web;

import com.game.model.createrequest.HeroCreateRequest;
import com.game.model.response.HeroResponse;
import com.game.service.HeroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Контроллер принимает HTTP-запросы
// JSON -> Java
// Spring преобразует JSON в объект HeroCreateRequest
// И передаёт в HeroService.create()

// Обозначает контроллер, возвращающий HTTP-ответы
@RestController
// Задаёт общий URL
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    // Обрабатывает POST по этому URL
    // Вызываем то, что клиент прислал серверу
    // Создаём нового героя и отправляем его в БД
    @PostMapping
    // RequestBody преобразует тело HTTP-запроса в Java-объект
    // После использования POST должен появиться объект HeroResponse
    public HeroResponse create(@RequestBody HeroCreateRequest request) {
        return heroService.create(request);
    }

    // Возвращает героев в виде JSON по GET
    @GetMapping
    public List<HeroResponse> findAll() {
        return heroService.findAll();
    }
}