package com.javastart.account.dto;

import com.javastart.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class AccountResponseDto {
    private Long accountId;
    private String name;
    private String phone;
    private String email;
    private OffsetDateTime creationDate;
    private List<Long> bills;

    public AccountResponseDto(Account account) {
        accountId = account.getAccountId();
        name = account.getName();
        phone = account.getPhone();
        email = account.getEmail();
        creationDate = account.getCreationDate();
        bills = account.getBills();
    }
}