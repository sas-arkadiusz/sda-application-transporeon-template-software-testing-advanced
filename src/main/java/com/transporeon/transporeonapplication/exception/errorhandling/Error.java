package com.transporeon.transporeonapplication.exception.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
    private final String message;
}
