package com.example.firstproject.controller;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/api/pizzas")
    public ResponseEntity<List<PizzaDto>> show(){
        List<PizzaDto> dtos = pizzaService.show();
        if (dtos != null)
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> get(@PathVariable Long id){
        PizzaDto dto = pizzaService.get(id);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/pizzas/new")
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto pizzaDto){
        PizzaDto dto = pizzaService.create(pizzaDto);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> update(@PathVariable Long id, @RequestBody PizzaDto pizzaDto){
        PizzaDto dto = pizzaService.update(id, pizzaDto);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> delete(@PathVariable Long id){
        PizzaDto dto = pizzaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
