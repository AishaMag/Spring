package com.task.stub.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class PaymentResponseModel {
    private String transaction_id;
    private String bank_bik;
    private String status;
    private List<ContactModel> contact;
}
