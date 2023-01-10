package com.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User.
 *
 * This class contains parameters for token and token request.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String username;
    private String password;

}
