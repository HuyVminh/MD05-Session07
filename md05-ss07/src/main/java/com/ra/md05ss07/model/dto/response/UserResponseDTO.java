package com.ra.md05ss07.model.dto.response;

import com.ra.md05ss07.model.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {
    private Long id;
    private String userName;
    private String fullName;
    private Boolean status;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.fullName = user.getFullName();
        this.status = user.getStatus();
    }
}
