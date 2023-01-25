package com.example.crud.controller;

import com.example.crud.dto.CustomerDTO;
import com.example.crud.response.DataResponse;
import com.example.crud.response.HandlerResponse;
import com.example.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/v1/customer", produces = {"application/json"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/users")
    public void getUsers(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam int page){
        Slice<CustomerDTO> allCustomer = customerService.getAllCustomer(page);
        DataResponse<Slice<CustomerDTO>> data = new DataResponse<>();
        data.setData(allCustomer);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @PutMapping("/create-user")
    public void createCustomer(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam String customerCode,
                               @RequestParam String customerName,
                               @RequestParam String customerPhone,
                               @RequestParam String customerAddress,
                               @RequestParam String customerCity)throws IOException {
       boolean result =  customerService.createCustomer(customerCode, customerName, customerPhone, customerAddress, customerCity);
       if (result) {
           HandlerResponse.responseSuccessOK(response, "Success!");
       }else {
           HandlerResponse.responseBadRequest(response, "01", "Something went wrong");
       }
    }

    @GetMapping("/active-user")
    public void getActiveCustomer(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam int page){
        Slice<CustomerDTO> activeCustomer = customerService.getActiveCustomer(page);
        DataResponse<Slice<CustomerDTO>> data = new DataResponse<>();
        data.setData(activeCustomer);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/filter-user")
    public void getCustomerByCity(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam int page,
                                  @RequestParam String city){
        List<CustomerDTO> customerFilterByCity = customerService.getCustomerFilterByCity(page, city);
        DataResponse<List<CustomerDTO>> data = new DataResponse<>();
        data.setData(customerFilterByCity);
        HandlerResponse.responseSuccessWithData(response, data);
    }

}
