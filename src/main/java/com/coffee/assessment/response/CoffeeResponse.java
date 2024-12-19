package com.coffee.assessment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CoffeeResponse {


    private String user;
    private double totalPaid;
    private double totalOwes;

    public CoffeeResponse() {

    }

    public CoffeeResponse(String user, double totalPaid, double totalOwes) {
        this.user = user;
        this.totalPaid = totalPaid;
        this.totalOwes = totalOwes;
    }


}
