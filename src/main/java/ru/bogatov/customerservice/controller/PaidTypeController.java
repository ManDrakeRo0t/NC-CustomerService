package ru.bogatov.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogatov.customerservice.entity.PaidType;
import ru.bogatov.customerservice.service.PaidTypeService;

import java.util.List;

@RestController
@RequestMapping("/paid-types")
public class PaidTypeController {

    private PaidTypeService paidTypeService;

    public PaidTypeController(@Autowired PaidTypeService paidTypeService){this.paidTypeService = paidTypeService;}
    @GetMapping("")
    public List<PaidType> getAll(){
        return paidTypeService.getAll();
    }
    @GetMapping("/{id}")
    public PaidType getById(@PathVariable String id){
        return paidTypeService.getOneById(id);
    }
    @PostMapping()
    public ResponseEntity<PaidType> createPaidType(@RequestBody PaidType paidType){
        return ResponseEntity.ok().body(paidTypeService.createPaidType(paidType));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PaidType> updatePaidType(@RequestBody PaidType paidType,@PathVariable String id){
        try{
            return ResponseEntity.ok().body(paidTypeService.editPaidType(paidType,id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaidType(@PathVariable String id){
        try{
            paidTypeService.deleteById(id);
            return ResponseEntity.ok("was deleted");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
