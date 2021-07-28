package com.example.consumer.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDto {

    @ApiModelProperty("Id клиента")
    @Id
    private Long id;

    @ApiModelProperty("Имя клиента")
    private String name;

    @ApiModelProperty("Мобильный телефон клиента")
    @NotBlank(message = "Phone is required")
    @Size(min = 7, message = "Phone number must be longer than 7")
    private String phone;

    @ApiModelProperty("Электронная почта клиента")
    @NotBlank(message = "Email is required")
    private String email;

    @ApiModelProperty("Адрес клиента")
    @NotBlank(message = "Address is required")
    private String address;
}
