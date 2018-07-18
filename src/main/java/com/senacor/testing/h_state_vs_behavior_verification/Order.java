package com.senacor.testing.h_state_vs_behavior_verification;

class Order {
    private final String product;
    private final int amount;

    private boolean filled;

    private MailService mailService;

    public Order(String product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void fill(Warehouse warehouse) {
        if (warehouse.hasInventory(product, amount)) {
            warehouse.removeFromInventory(product, amount);
            filled = true;
        } else {
            mailService.send(new Message());
        }
    }

    public boolean isFilled() {
        return filled;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product='" + product + '\'' +
                ", amount=" + amount +
                ", filled=" + filled +
                ", mailService=" + mailService +
                '}';
    }
}
