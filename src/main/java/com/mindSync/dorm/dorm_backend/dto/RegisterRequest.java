package com.mindSync.dorm.dorm_backend.dto;

import com.mindSync.dorm.dorm_backend.model.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message="email should not blank")
//    @Email(message="email should be in the format")
    private String username;

    @NotBlank(message="email should not blank")
    private String name;

//    @NotBlank(message = "Password cannot be blank")
//    @Size(min = 8, max = 20, message = "Password must be 8-20 characters long")
//    @Pattern(
//            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
//            message = "Password must contain at least one uppercase letter, one number, and one special character"
//    )
    private String password;

    @NotNull(message="role should not blank")
    private Role role;

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
