package com.example.crud.service;

import com.example.crud.converter.CustomerConverter;
import com.example.crud.dto.CustomerDTO;
import com.example.crud.entity.CustomerModel;
import com.example.crud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;


    //get all customers
    public Slice<CustomerDTO> getAllCustomer(int page){
        Pageable pagination = PageRequest.of(page, 10);
        return customerRepository.findAll(pagination).map(CustomerConverter::convertToDTO);
    }

    //create a new customer
    public boolean createCustomer(String customerCode, String customerName, String customerPhone, String customerAddress, String customerCity) {

        //check customer code
        if( customerRepository.findByCustomerCode(customerCode).isEmpty()){
            CustomerModel model = new CustomerModel();
            model.setCustomerCode(customerCode);
            model.setCustomerName(customerName);
            model.setCustomerCity(customerCity);
            model.setCustomerAddress(customerAddress);
            model.setCustomerPhone(customerPhone);
            model.setActive(true);
            model.setCreatedAt(LocalDateTime.now());
            customerRepository.save(model);
            return true;
        }else{
            return false;
        }
    }


    // get customer active without stream
    public Slice<CustomerDTO> getActiveCustomer(int page){
        Pageable pagination = PageRequest.of(page, 10);
        return customerRepository.getActiveCustomer(pagination).map(CustomerConverter::convertToDTO);
    }

    //get cutomer by city using stream filter
    public List<CustomerDTO> getCustomerFilterByCity(int page, String city){
        Pageable pagination = PageRequest.of(page, 10);
        return customerRepository.findAll(pagination).stream().filter(data -> data.getCustomerCity().equals(city)).map(CustomerConverter::convertToDTO).collect(Collectors.toList());
    }
}
