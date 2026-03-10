package com.example.expenseapp.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDTO {
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private List<Long> participantIds;
}
