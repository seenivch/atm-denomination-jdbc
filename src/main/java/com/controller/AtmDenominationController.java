package com.controller;


import com.model.ResponsePossibleDenom;
import com.model.ResponseWrapper;
import com.service.AtmDenominationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Seenivasan Chandrasekaran
 */

@RestController
public class AtmDenominationController {

    @Autowired
    AtmDenominationService atmService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @GetMapping("/totalDenom")
    public ResponseEntity<ResponseWrapper> totalDenom() {
        return atmService.totalDenomation();
    }
    
    @CrossOrigin(origins = "*", allowCredentials = "true")
    @GetMapping("/possibleDenom")
    public ResponseEntity<ResponsePossibleDenom> possibleDenom(
            @RequestParam("amount") int amount) {
        return atmService.possibleDenom(amount);
    }

}
