package com.ra.md05ss07.service.user;

import com.ra.md05ss07.exception.CustomException;
import com.ra.md05ss07.model.dto.request.UserRequestDTO;
import com.ra.md05ss07.model.dto.response.UserResponseDTO;
import com.ra.md05ss07.model.entity.User;
import com.ra.md05ss07.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class UserServiceIMPL implements IUserSerivce {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserResponseDTO::new);
    }

    @Override
    public UserResponseDTO saveOrUpdate(UserRequestDTO userRequestDTO) throws CustomException {
        // check trung lap
        if(userRepository.existsByUserName(userRequestDTO.getUserName())){
            throw new CustomException("Username already exists");
        }
        User user = User.builder().userName(userRequestDTO.getUserName())
                .fullName(userRequestDTO.getFullName())
                .password(userRequestDTO.getPassword()).build();
        User newUser = userRepository.save(user);
        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(newUser.getId())
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .status(user.getStatus())
                .build();
        return userResponseDTO;
    }

    @Override
    public UserResponseDTO findById(Long id) throws CustomException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return UserResponseDTO.builder()
                    .id(user.getId())
                    .userName(user.getUserName())
                    .fullName(user.getFullName())
                    .status(user.getStatus()).build();
        }
        throw new CustomException("Not Found");
    }
}
