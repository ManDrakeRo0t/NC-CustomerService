package ru.bogatov.customerservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bogatov.customerservice.Entities.PaidType;
import ru.bogatov.customerservice.Services.PaidTypeService;

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
