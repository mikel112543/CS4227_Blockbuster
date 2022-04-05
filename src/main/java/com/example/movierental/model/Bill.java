package com.example.movierental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Bill Details")
public class Bill {

    //required parameters
    @JsonIgnore
    private int billId;
    @JsonProperty("Rental")
    private Rental rental;
    @JsonProperty("Email Address")
    private String emailAddress;
    //optional parameters
    @JsonIgnore
    private int phoneNumber;

    public int getBillId() {
        return billId;
    }
    public Rental getRental(){
        return rental;
    }
    public String getEmailAddress(){
        return emailAddress;
    }

    private Bill(BillBuilder builder) {
        this.billId = builder.billId;
        this.rental = builder.rental;
        this.emailAddress = builder.emailAddress;
    }

    public static class BillBuilder {
        //required parameters
        private int billId;
        private Rental rental;
        private String emailAddress;

        //optional parameters
        private int phoneNumber;

        public BillBuilder(int billId, Rental rental, String emailAddress) {
            this.billId = billId;
            this.rental = rental;
            this.emailAddress = emailAddress;
        }

        public void setPhoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Bill build() {
            return new Bill(this);
        }
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", emailAddress='" + emailAddress + '\'' +
                ", rental='" + rental + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}

