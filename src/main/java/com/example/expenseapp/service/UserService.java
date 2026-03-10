package com.example.expenseapp.service;

import com.example.expenseapp.dto.UserDTO;
import com.example.expenseapp.model.User;
import com.example.expenseapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO createUser(UserDTO dto) {
        User user = User.builder().name(dto.getName()).build();
        user = userRepository.save(user);
        return UserDTO.builder().id(user.getId()).name(user.getName()).build();
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(u -> UserDTO.builder().id(u.getId()).name(u.getName()).build())
                .collect(Collectors.toList());
    }
}
