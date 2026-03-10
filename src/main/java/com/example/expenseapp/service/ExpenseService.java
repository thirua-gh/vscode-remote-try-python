package com.example.expenseapp.service;

import com.example.expenseapp.dto.ExpenseDTO;
import com.example.expenseapp.dto.ExpenseResponseDTO;
import com.example.expenseapp.dto.UserDTO;
import com.example.expenseapp.model.Expense;
import com.example.expenseapp.model.User;
import com.example.expenseapp.repository.ExpenseRepository;
import com.example.expenseapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseResponseDTO createExpense(ExpenseDTO dto) {
        List<User> participants = userRepository.findAllById(dto.getParticipantIds());
        Expense e = Expense.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .participants(participants)
                .build();
        e = expenseRepository.save(e);
        return toResponseDTO(e);
    }

    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public Map<String, BigDecimal> calculateSplit(Long expenseId) {
        Expense e = expenseRepository.findById(expenseId).orElseThrow(() -> new NoSuchElementException("Expense not found"));
        List<User> participants = e.getParticipants();
        if (participants == null || participants.isEmpty()) return Collections.emptyMap();
        int n = participants.size();
        BigDecimal share = e.getAmount().divide(BigDecimal.valueOf(n), 2, RoundingMode.HALF_UP);
        Map<String, BigDecimal> result = new LinkedHashMap<>();
        for (User u : participants) {
            String key = u.getId() + ":" + u.getName();
            result.put(key, share);
        }
        return result;
    }

    private ExpenseResponseDTO toResponseDTO(Expense e) {
        List<UserDTO> users = Optional.ofNullable(e.getParticipants()).orElse(Collections.emptyList()).stream()
                .map(u -> UserDTO.builder().id(u.getId()).name(u.getName()).build())
                .collect(Collectors.toList());
        return ExpenseResponseDTO.builder()
                .id(e.getId())
                .description(e.getDescription())
                .amount(e.getAmount())
                .date(e.getDate())
                .participants(users)
                .build();
    }
}
