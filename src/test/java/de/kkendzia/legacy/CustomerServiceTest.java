package de.kkendzia.legacy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    private CustomerRepository repository;
    private CustomerService service;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        service = new CustomerService(repository);
    }

    @Test
    void sortsCustomersByLastName() {
        Customer hopper = new Customer("Grace", "Hopper", "grace@example.com");
        Customer lovelace = new Customer("Ada", "Lovelace", "ada@example.com");
        when(repository.findAll()).thenReturn(Arrays.asList(lovelace, hopper));

        List<Customer> result = service.findAll("");

        assertEquals("Hopper", result.get(0).getLastName());
        assertEquals("Lovelace", result.get(1).getLastName());
    }

    @Test
    void rejectsUnknownCustomer() {
        when(repository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.findRequired(42L));
    }
}
