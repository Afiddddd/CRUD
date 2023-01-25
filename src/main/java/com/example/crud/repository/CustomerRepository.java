package com.example.crud.repository;

import com.example.crud.entity.CustomerModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
    Optional<CustomerModel> findByCustomerCode(String customerCode);

    //show customer active only
    @Query(value = "select * from customer as cus " +
            "where cus.is_active = 1 ", nativeQuery = true)
    Slice<CustomerModel> getActiveCustomer(Pageable pageable);
}
