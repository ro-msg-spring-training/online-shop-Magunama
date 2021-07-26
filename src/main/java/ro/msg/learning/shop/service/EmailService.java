package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Customer;

interface EmailService {
    void sendMail(Customer target);
}
