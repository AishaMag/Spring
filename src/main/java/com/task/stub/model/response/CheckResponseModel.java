package com.task.stub.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class CheckResponseModel {
    private String account;
    @JsonProperty("vip-client")
    private boolean vip_client;
    private boolean blocked;
    private String inn;
    private List<DebtModel> debt;
}
