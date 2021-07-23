package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Customer;

import java.util.List;
import java.util.Optional;

interface UserDetailsService {
    Optional<Customer> getUser(Integer id);
    Optional<Customer> getUser(String username);
    List<Customer> getUsers();
}
