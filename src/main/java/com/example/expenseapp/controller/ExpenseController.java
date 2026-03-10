package com.example.expenseapp.controller;

import com.example.expenseapp.dto.ExpenseDTO;
import com.example.expenseapp.dto.ExpenseResponseDTO;
import com.example.expenseapp.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody ExpenseDTO dto) {
        return ResponseEntity.ok(expenseService.createExpense(dto));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAll() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}/split")
    public ResponseEntity<Map<String, BigDecimal>> split(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.calculateSplit(id));
    }
}
