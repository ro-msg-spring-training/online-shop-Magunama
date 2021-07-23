package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository repository;

    @Override
    public Optional<Customer> getUser(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Customer> getUser(String username) {
        return this.repository.findCustomerByUsername(username);
    }

    @Override
    public List<Customer> getUsers() {
        return (List<Customer>) this.repository.findAll();
    }
}
