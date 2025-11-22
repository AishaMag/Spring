package com.task.stub.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ContactModel {
    private String name;
    private List<String> telecom;
}
