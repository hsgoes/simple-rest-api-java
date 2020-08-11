package com.springapi.expensetrackerapi.resources;

import com.springapi.expensetrackerapi.domain.Transaction;
import com.springapi.expensetrackerapi.exception.EtBadRequestException;
import com.springapi.expensetrackerapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest request,
                                                      @PathVariable("categoryId") Integer categoryId,
                                                      @RequestBody Map<String, Object> transactionMap) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            int userId = (Integer) request.getAttribute("userId");
            Double amount = Double.valueOf(transactionMap.get("amount").toString());
            String note = (String) transactionMap.get("note");
            Long transactionDate = (Long) transactionMap.get("transactionDate");
            Transaction transaction = transactionService.addTransaction(userId, categoryId, amount, note,
                    transactionDate);

            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } else {
            throw new EtBadRequestException("Token must be provided");
        }
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long transactionId){

        

        return new ResponseEntity<>();
    }

}
