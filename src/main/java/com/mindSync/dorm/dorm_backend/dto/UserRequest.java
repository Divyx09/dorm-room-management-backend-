package com.mindSync.dorm.dorm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NotBlank(message="room name should not be blank")
    private String roomNumber;

//    @NotNull(message="roomid should not be blank")
//    private int roomId;
}
