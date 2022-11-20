package com.spring.bank.api.controller;

import com.google.common.base.Preconditions;
import com.spring.bank.api.model.dto.StatusResponse;
import com.spring.bank.api.model.dto.WithdrawalActivityByAccountDto;
import com.spring.bank.api.model.dto.WithdrawalActivityByCardDto;
import com.spring.bank.api.service.IWithdrawalActivityService;
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
@RequestMapping("/api/v1/withdrawal-activities")
public class WithdrawalActivityController {

    private final IWithdrawalActivityService withdrawalActivityService;

    @Autowired
    public WithdrawalActivityController(IWithdrawalActivityService withdrawalActivityService) {
        this.withdrawalActivityService = withdrawalActivityService;
    }

    /*
     *   Requirement :
     *      - One rest endpoint to withdraw money
     *  Withdraw Money By Card Id
     *
     * */
    @Operation(description = "Withdraw Money by Card Id")
    @PostMapping("/card")
    public ResponseEntity<StatusResponse> withDrawByAccountDetails(@RequestBody @Valid WithdrawalActivityByCardDto request) {
        Preconditions.checkNotNull(request, "Invalid Request");
        Preconditions.checkArgument(!ValidationHelper.isNegative(request.getAmount()), "Negative Amount can't be transferred");
        withdrawalActivityService.withDrawableByCard(request);
        return new ResponseEntity<>(new StatusResponse("Withdrawal Completed Successfully"), HttpStatus.OK);
    }

    /*
     *  Withdraw Money By Account Id
     * */
    @Operation(description = "Withdraw Money by Account Id")
    @PostMapping
    public ResponseEntity<StatusResponse> withDrawByAccountDetails(@RequestBody @Valid WithdrawalActivityByAccountDto request) {
        Preconditions.checkNotNull(request, "Invalid Request");
        Preconditions.checkArgument(!ValidationHelper.isNegative(request.getAmount()), "Negative Amount can't be transferred");
        withdrawalActivityService.withDrawableByAccount(request);
        return new ResponseEntity<>(new StatusResponse("Withdrawal Completed Successfully"), HttpStatus.OK);
    }

}
