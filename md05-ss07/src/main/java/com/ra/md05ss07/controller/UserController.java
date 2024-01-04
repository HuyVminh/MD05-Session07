package com.ra.md05ss07.controller;

import com.ra.md05ss07.exception.CustomException;
import com.ra.md05ss07.model.dto.request.UserRequestDTO;
import com.ra.md05ss07.model.dto.response.UserResponseDTO;
import com.ra.md05ss07.model.entity.User;
import com.ra.md05ss07.service.user.IUserSerivce;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private IUserSerivce userSerivce;
    @GetMapping("/users")
    public ResponseEntity<Page<UserResponseDTO>> getUsers(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<UserResponseDTO> userResponseDTOPage = userSerivce.findAll(pageable);
        return new ResponseEntity<>(userResponseDTOPage, HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody @Valid UserRequestDTO userRequestDTO) throws CustomException{
            UserResponseDTO userResponseDTO = userSerivce.saveOrUpdate(userRequestDTO);
            return new ResponseEntity<>(userResponseDTO,HttpStatus.CREATED);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?> findById(Long id) throws CustomException {
        UserResponseDTO userResponseDTO = userSerivce.findById(id);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserRequestDTO userRequestDTO) throws CustomException {
        UserResponseDTO userUpdate = userSerivce.findById(id);
        userUpdate.setUserName(userRequestDTO.getUserName());
        userUpdate.setFullName(userRequestDTO.getFullName());
        return new ResponseEntity<>(userUpdate,HttpStatus.OK);
    }
}
