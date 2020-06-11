package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    EntityManager en;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = en.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer getById(Long id) {
        TypedQuery<Customer> query = en.createQuery("select c from Customer c where c.id = :id", Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Customer model) {
        if (model.getId() != null) {
            en.merge(model);
        } else {
            en.persist(model);
        }
    }

    @Override
    public void update(Customer model) {

    }

    @Override
    public void remove(Long id) {
        Customer customer = getById(id);
        if (customer.getId() != null) {
            en.remove(customer);
        }
    }
}
