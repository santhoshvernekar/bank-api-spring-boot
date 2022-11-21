package com.spring.bank.api.controller;

import com.google.common.base.Preconditions;
import com.spring.bank.api.model.dto.StatusResponse;
import com.spring.bank.api.model.dto.TransferActivityByAccountDto;
import com.spring.bank.api.model.dto.TransferActivityByCardDto;
import com.spring.bank.api.service.ITransferActivityService;
import com.spring.bank.api.utils.ValidationHelper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/transfer-activities")
public class TransferActivityController {

    private final ITransferActivityService transferActivityService;

    @Autowired
    public TransferActivityController(ITransferActivityService transferActivityService) {
        this.transferActivityService = transferActivityService;
    }

    /*
     *   Requirement :
     *      - One rest endpoint to transfer money
     *  Transfer Money By Card Id
     *
     * */
    @Operation(description = "Transfer Money by Card Id")
    @PostMapping("/card")
    public ResponseEntity<StatusResponse> transferByCard(@RequestBody @Valid TransferActivityByCardDto request) {
        Preconditions.checkNotNull(request, "Invalid request");
        Preconditions.checkArgument(!ValidationHelper.isNegative(request.getAmount()), "Negative Amount can't be transferred");
        transferActivityService.transferByCardDetails(request);
        return new ResponseEntity<>(new StatusResponse("Transferred Successfully"), HttpStatus.OK);
    }

    /*
     *   Transfer Money By Account Id
     *
     * */
    @Operation(description = "Transfer Money by Account Id")
    @PostMapping
    public ResponseEntity<StatusResponse> transferByAccountDetails(@RequestBody @Valid TransferActivityByAccountDto request) {
        Preconditions.checkNotNull(request, "Invalid request");
        Preconditions.checkArgument(!ValidationHelper.isNegative(request.getAmount()), "Negative Amount can't be transferred");
        transferActivityService.transferByAccountDetails(request);
        return new ResponseEntity<>(new StatusResponse("Transferred Successfully"), HttpStatus.OK);
    }
}
