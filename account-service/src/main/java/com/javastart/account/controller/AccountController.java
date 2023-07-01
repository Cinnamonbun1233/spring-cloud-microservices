package com.javastart.account.controller;

import com.javastart.account.dto.AccountRequestDto;
import com.javastart.account.dto.AccountResponseDto;
import com.javastart.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/")
    public Long createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.createAccount(accountRequestDto.getName(), accountRequestDto.getEmail(),
                accountRequestDto.getPhone(), accountRequestDto.getBills());
    }

    @GetMapping("/{accountId}")
    public AccountResponseDto getAccount(@PathVariable Long accountId) {
        return new AccountResponseDto(accountService.getAccount(accountId));
    }

    @PutMapping("/{accountId}")
    public AccountResponseDto updateAccount(@PathVariable Long accountId,
                                            @RequestBody AccountRequestDto accountRequestDto) {
        return new AccountResponseDto(accountService.updateAccount(accountId, accountRequestDto.getName(),
                accountRequestDto.getEmail(), accountRequestDto.getPhone(), accountRequestDto.getBills()));
    }

    @DeleteMapping("/{accountId}")
    public AccountResponseDto deleteAccount(@PathVariable Long accountId) {
        return new AccountResponseDto(accountService.deleteAccount(accountId));
    }
}