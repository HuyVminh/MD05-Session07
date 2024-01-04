package com.ra.md05ss07.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDTO {
    @NotEmpty(message = "Vui long dien username")
    private String userName;
    @Size(min = 3,message = "Vui long nhap mat khau > 3 ky tu")
    private String password;
    private String fullName;
}
