package de.kkendzia.legacy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll(String filter) {
        List<Customer> customers = filter == null || filter.trim().isEmpty()
                ? new ArrayList<Customer>(repository.findAll())
                : repository.findByLastNameContainingIgnoreCase(filter);

        Collections.sort(customers, new Comparator<Customer>() {
            @Override
            public int compare(Customer left, Customer right) {
                return left.getLastName().compareToIgnoreCase(right.getLastName());
            }
        });
        return customers.stream().collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Customer findRequired(Long id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new IllegalArgumentException("Unknown customer: " + id);
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public void delete(Customer customer) {
        repository.delete(customer);
    }

    public String displayName(Customer customer) {
        StringBuffer result = new StringBuffer();
        result.append(customer.getFirstName());
        result.append(" ");
        result.append(customer.getLastName());
        return result.toString();
    }
}

