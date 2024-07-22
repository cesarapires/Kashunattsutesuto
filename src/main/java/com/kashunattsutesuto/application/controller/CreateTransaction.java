package com.kashunattsutesuto.application.controller;

import com.kashunattsutesuto.application.controller.resources.TransactionRequest;
import com.kashunattsutesuto.application.controller.resources.TransactionResponse;
import com.kashunattsutesuto.domain.model.Transaction;
import com.kashunattsutesuto.domain.model.TransactionStatus;
import com.kashunattsutesuto.domain.usecase.CreateTransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class CreateTransaction {

    private final CreateTransactionUseCase createTransactionUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TransactionResponse> save(@RequestBody TransactionRequest transactionRequest) {

        final Transaction transaction = transactionRequest.toDomain();

        try {
            TransactionStatus transactionStatus = createTransactionUseCase.create(transaction);
            return ResponseEntity.ok(new TransactionResponse(transactionStatus));
        } catch (Exception e) {
            return ResponseEntity.ok(new TransactionResponse(TransactionStatus.GENERIC_ERROR));
        }
    }
}
