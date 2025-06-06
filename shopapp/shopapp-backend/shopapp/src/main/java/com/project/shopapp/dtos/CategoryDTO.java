package com.project.shopapp.dtos;

import lombok.*;
import jakarta.validation.constraints.NotEmpty;

@Data
//@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDTO {
    @NotEmpty(message = "Category's name cannot be empty")
    private String name;
}
