package com.example.demo.exceptions.config

import com.example.demo.exceptions.dto.ErrorResponse
import com.example.demo.exceptions.exceptions.GhUserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(GhUserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleGhUserNotFoundException(ex: GhUserNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            errorCode = 404,
            errorMessage = ex.message
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(ex:Exception):ResponseEntity<ErrorResponse>{
        val errorResponse = ErrorResponse(
            errorCode = 500,
            errorMessage = "Unexpected internal error"
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}