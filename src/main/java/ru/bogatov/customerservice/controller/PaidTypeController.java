package ru.bogatov.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bogatov.customerservice.dto.PaidTypeDto;
import ru.bogatov.customerservice.entity.PaidType;
import ru.bogatov.customerservice.service.PaidTypeService;

import java.util.List;

@RestController
@RequestMapping("/paid-types")
public class PaidTypeController {

    private PaidTypeService paidTypeService;

    public PaidTypeController(@Autowired PaidTypeService paidTypeService){this.paidTypeService = paidTypeService;}
    @GetMapping("")
    public PaidTypeDto getAll(){
        PaidTypeDto paidTypeDto = new PaidTypeDto();
        paidTypeDto.setPaidTypes(paidTypeService.getAll());
        return paidTypeDto;
    }
    @GetMapping("/{id}")
    public PaidType getById(@PathVariable String id){
        return paidTypeService.getOneById(id);
    }
    @PostMapping()
    public void createPaidType(@RequestBody PaidType paidType){
        paidTypeService.createPaidType(paidType);
    }
    @PutMapping("/{id}")
    public void updatePaidType(@RequestBody PaidType paidType,@PathVariable String id){
        paidTypeService.editPaidType(paidType,id);
    }
    @DeleteMapping("/{id}")
    public void deletePaidType(@PathVariable String id){
        paidTypeService.deleteById(id);
    }
}
