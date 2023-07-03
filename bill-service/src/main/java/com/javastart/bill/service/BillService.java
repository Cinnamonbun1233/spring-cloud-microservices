package com.javastart.bill.service;

import com.javastart.bill.entity.Bill;
import com.javastart.bill.exception.BillNotFoundException;
import com.javastart.bill.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Long createBill(Long accountId, BigDecimal amount, Boolean isDefault, Boolean isOverdraftEnabled) {
        Bill bill = new Bill(accountId, amount, isDefault, OffsetDateTime.now(), isOverdraftEnabled);
        return billRepository.save(bill).getBillId();
    }

    public Bill getBill(Long billId) {
        return billRepository.findById(billId)
                .orElseThrow(() -> new BillNotFoundException(String.format("Счёт с id: %s не найден", billId)));
    }

    public Bill updateBill(Long billId, Long accountId, BigDecimal amount, Boolean isDefault,
                           Boolean isOverdraftEnabled) {
        Bill bill = new Bill(accountId, amount, isDefault, OffsetDateTime.now(), isOverdraftEnabled);
        bill.setBillId(billId);
        return billRepository.save(bill);
    }

    public Bill deleteBill(Long billId) {
        Bill bill = getBill(billId);
        billRepository.deleteById(billId);
        return bill;
    }

    public List<Bill> getBills(Long accountId){
        return billRepository.getBillsByAccountId(accountId);
    }
}