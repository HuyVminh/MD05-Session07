package com.ra.md05ss07.service.user;

import com.ra.md05ss07.exception.CustomException;
import com.ra.md05ss07.model.dto.request.UserRequestDTO;
import com.ra.md05ss07.model.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserSerivce {
    Page<UserResponseDTO> findAll(Pageable pageable);
    UserResponseDTO saveOrUpdate(UserRequestDTO userRequestDTO) throws CustomException;
    UserResponseDTO findById(Long id) throws CustomException;
}
