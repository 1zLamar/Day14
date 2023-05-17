package com.example.day14sol.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Employee {

    @NotEmpty(message = "id cannot be null")
    @Min(value = 3,message = "id should be more than 2")
    private String id ;

    @NotEmpty(message = "name cannot be null")
    @Size(min = 5,max = 10,message = "name should be more than 4")
    private String name ;

    @NotNull(message = "age cannot be null")
    @Positive(message = "should positive number")
    @Min(value = 26,message = "age should be above 25")
    private int age ;

    @NotEmpty(message = "role cannot be null")
    @Pattern(regexp = "^(supervisor|coordinator)$",message = "should be supervisor or coordinator")
    private String role;

    @NotNull(message = "on leave should be not null")
    @AssertFalse(message = "on leave should br false")
    private boolean onLeave;

    @NotNull(message = "year cannot be null")
    @Min(value = 1900 , message = "should enter year from 1900 to 2023")
    @Max(value = 2023 , message = "should enter year from 1900 to 2023")
    @Positive(message = "it must be a number")
    private int employmentYear;

    @NotNull(message = "annual leave cannot be null")
    @PositiveOrZero(message = "should be a number")
    private int annualLeave;

}
