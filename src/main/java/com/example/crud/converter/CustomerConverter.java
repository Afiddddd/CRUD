package com.example.crud.converter;

import com.example.crud.dto.CustomerDTO;
import com.example.crud.entity.CustomerModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public static CustomerDTO convertToDTO(CustomerModel item){

        return new CustomerDTO(
                item.getCustomerName(),
                item.getCustomerPhone(),
                item.getCustomerAddress(),
                item.getCustomerCity()
        );

    }
}
