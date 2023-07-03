package com.javastart.deposit.controller;

import com.javastart.deposit.dto.DepositRequestDto;
import com.javastart.deposit.dto.DepositResponseDto;
import com.javastart.deposit.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {
    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/deposits")
    public DepositResponseDto createDeposit(@RequestBody DepositRequestDto depositRequestDto) {
        return depositService.deposit(depositRequestDto.getAccountId(), depositRequestDto.getBillId(),
                depositRequestDto.getAmount());
    }
}