package com.javastart.bill.controller;

import com.javastart.bill.dto.BillRequestDto;
import com.javastart.bill.dto.BillResponseDto;
import com.javastart.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/")
    public Long createBill(@RequestBody BillRequestDto billRequestDto) {
        return billService.createBill(billRequestDto.getAccountId(), billRequestDto.getAmount(),
                billRequestDto.getIsDefault(), billRequestDto.getIsOverdraftEnabled());
    }

    @GetMapping("/{billId}")
    public BillResponseDto getBill(@PathVariable Long billId) {
        return new BillResponseDto(billService.getBill(billId));
    }

    @PutMapping("/{billId}")
    public BillResponseDto updateBill(@PathVariable Long billId,
                                      @RequestBody BillRequestDto billRequestDto) {
        return new BillResponseDto(billService.updateBill(billId, billRequestDto.getAccountId(),
                billRequestDto.getAmount(), billRequestDto.getIsDefault(), billRequestDto.getIsOverdraftEnabled()));
    }

    @DeleteMapping("/{billId}")
    public BillResponseDto deleteBill(@PathVariable Long billId) {
        return new BillResponseDto(billService.deleteBill(billId));
    }
}