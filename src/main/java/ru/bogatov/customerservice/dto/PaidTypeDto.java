package ru.bogatov.customerservice.dto;

import lombok.Data;
import ru.bogatov.customerservice.entity.PaidType;

import java.util.List;

@Data
public class PaidTypeDto {
    List<PaidType> paidTypes;
}
