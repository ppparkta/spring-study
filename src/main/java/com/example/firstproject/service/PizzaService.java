package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public List<PizzaDto> show() {
//        return pizzaRepository.findAll()
//                .stream().map(pizza -> PizzaDto.createDto(pizza))
//                .collect(Collectors.toList());
        List<Pizza> pizzas = pizzaRepository.findAll();
        List<PizzaDto> pizzaDtos = new ArrayList<PizzaDto>();
        for(int i =0;i<pizzas.size();i++){
            Pizza pizza = pizzas.get(i);
            PizzaDto dto = PizzaDto.createDto(pizza);
            pizzaDtos.add(dto);
        }
        return pizzaDtos;
    }

    public PizzaDto get(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("피자 id를 찾을 수 없습니다"));
        if (pizza.getId() != id)
            throw new IllegalArgumentException("피자의 id가 이상합니다.");
        return PizzaDto.createDto(pizza);
    }

    @Transactional
    public PizzaDto create(PizzaDto pizzaDto) {
        if (pizzaDto.getId() != null)
            throw new IllegalArgumentException("피자 생성 시 id를 지정할 수 없습니다,");
        Pizza pizza = Pizza.createPizza(pizzaDto);
        Pizza update = pizzaRepository.save(pizza);
        return PizzaDto.createDto(update);
    }

    @Transactional
    public PizzaDto update(Long id, PizzaDto pizzaDto) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("피자 수정 실패 -> 피자 id를 찾을 수 없습니다."));
        if (id != pizzaDto.getId())
            throw new IllegalArgumentException("피자의 id가 이상합니다.");
        pizza.patch(pizzaDto);
        Pizza update = pizzaRepository.save(pizza);
        return PizzaDto.createDto(update);
    }

    @Transactional
    public PizzaDto delete(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("피자 삭제 실패 -> 피자의 id를 찾을 수 없습니다,"));
        pizzaRepository.delete(pizza);
        return PizzaDto.createDto(pizza);
    }
}
