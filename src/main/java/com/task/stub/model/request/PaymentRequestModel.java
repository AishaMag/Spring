package com.task.stub.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRequestModel {

    private String transaction_id;
    private double sum;
    private boolean need_processing;
}
