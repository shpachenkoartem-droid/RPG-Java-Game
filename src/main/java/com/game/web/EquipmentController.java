package com.game.web;

import com.game.model.createrequest.EquipmentCreateRequest;
import com.game.model.response.EquipmentResponse;
import com.game.service.EquipmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equip")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public EquipmentResponse create(@RequestBody EquipmentCreateRequest request) {
        return equipmentService.create(request);
    }

    @GetMapping
    public List<EquipmentResponse> findAll() {
        return equipmentService.findAll();
    }
}
