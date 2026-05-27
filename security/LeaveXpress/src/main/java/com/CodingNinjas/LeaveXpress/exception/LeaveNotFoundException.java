package com.CodingNinjas.LeaveXpress.exception;

public class LeaveNotFoundException extends RuntimeException {

    public LeaveNotFoundException(String message) {
        super(message);
    }

    public LeaveNotFoundException(Long id) {
        super("Leave not found with id: " + id);
    }
}