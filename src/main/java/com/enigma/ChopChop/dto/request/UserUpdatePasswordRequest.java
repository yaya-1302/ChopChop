package com.enigma.ChopChop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdatePasswordRequest {
    @NotBlank(message = "current password must included")
    private String oldPassword;
    @NotBlank(message = "new password must included")
    private String newPassword;
}
