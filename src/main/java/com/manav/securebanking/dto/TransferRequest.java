package com.manav.securebanking.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TransferRequest {

    @NotNull
    private Long senderAccountId;

    @NotNull
    private Long receiverAccountId;


    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    public Long getSenderAccountId(){
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId){
        this.senderAccountId = senderAccountId;
    }

    public Long getReceiverAccountId(){
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId){
        this.receiverAccountId = receiverAccountId;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
}
