package com.api.model;
import lombok.Setter;
import lombok.Getter;

@Setter @Getter
public class CreateMessageModel {
    public int messageid;
    public String name;
    public String email;
    public String phone;
    public String subject;
    public String description;
}
