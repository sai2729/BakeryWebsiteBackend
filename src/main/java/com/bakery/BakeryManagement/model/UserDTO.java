package com.bakery.BakeryManagement.model;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String password;
    private String name; // For signup only
}
