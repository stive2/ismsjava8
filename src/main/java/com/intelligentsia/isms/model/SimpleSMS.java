package com.intelligentsia.isms.model;

public class SimpleSMS {
    private String paiementchanel;
    private String paiementpaterne;
    private String message;
    private String phone;
    private String Operation;

    public String getPaiementpaterne() {
        return paiementpaterne;
    }

    public void setPaiementpaterne(String paiementpaterne) {
        this.paiementpaterne = paiementpaterne;
    }

    public String getPaiementchanel() {
        return paiementchanel;
    }

    public void setPaiementchanel(String paiementchanel) {
        this.paiementchanel = paiementchanel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOperation() {
        return Operation;
    }

    public void setOperation(String operation) {
        Operation = operation;
    }

    @Override
    public String toString() {
        return "SimpleSMS{" +
                "paiementchanel='" + paiementchanel + '\'' +
                ", paiementpaterne='" + paiementpaterne + '\'' +
                ", message='" + message + '\'' +
                ", phone='" + phone + '\'' +
                ", Operation='" + Operation + '\'' +
                '}';
    }
}
