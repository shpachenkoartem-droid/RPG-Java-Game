package com.game.web;


import com.game.model.createrequest.DamageDiceCreateRequest;
import com.game.model.response.DamageDiceResponse;
import com.game.service.DamageDiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dice")
public class DamageDiceController {
    private final DamageDiceService damageDiceSerive;

    public DamageDiceController(DamageDiceService damageDiceSerive) {
        this.damageDiceSerive = damageDiceSerive;
    }

    @PostMapping
    public DamageDiceResponse create(@RequestBody DamageDiceCreateRequest request) {
        return damageDiceSerive.create(request);
    }

    @GetMapping
    public List<DamageDiceResponse> findAll() {
        return damageDiceSerive.findAll();
    }
}