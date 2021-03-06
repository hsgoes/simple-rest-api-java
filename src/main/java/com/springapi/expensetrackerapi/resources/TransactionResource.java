package com.springapi.expensetrackerapi.resources;

import com.springapi.expensetrackerapi.domain.Transaction;
import com.springapi.expensetrackerapi.exception.EtBadRequestException;
import com.springapi.expensetrackerapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest request,
                                                          @PathVariable("categoryId") Integer categoryId,
                                                          @PathVariable("transactionId") Integer transactionId){
        String token = request.getHeader("Authorization");
         if(token != null){
            int userId = (Integer) request.getAttribute("userId");
            Transaction transaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } else {
            throw new EtBadRequestException("Token must be provided");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest request,
                                                                @PathVariable("categoryId") Integer categoryId){
        String token = request.getHeader("Authorization");
        if(token != null){
            int userId = (Integer) request.getAttribute("userId");
            List<Transaction> transactions = transactionService.fetchAllTransactions(userId, categoryId);

            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } else {
            throw new EtBadRequestException("Token must be provided");
        }
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId,
                                                                  @RequestBody Transaction transaction){
        String token = request.getHeader("Authorization");
        if(token != null){
            int userId = (Integer) request.getAttribute("userId");
            transactionService.updateTransaction(userId, categoryId, transactionId, transaction);
            Map<String, Boolean> map = new HashMap<>();
            map.put("success", true);

            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            throw new EtBadRequestException("Token must be provided");
        }
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> deleteTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId){
        String token = request.getHeader("Authorization");
        if(token != null){
            int userId = (Integer) request.getAttribute("userId");
            transactionService.removeTransaction(userId, categoryId, transactionId);
            Map<String, Boolean> map = new HashMap<>();
            map.put("success", true);

            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            throw new EtBadRequestException("Token must be provided");
        }
    }
}
