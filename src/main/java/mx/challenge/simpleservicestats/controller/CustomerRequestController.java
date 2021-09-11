package mx.challenge.simpleservicestats.controller;

import mx.challenge.simpleservicestats.aspect.CountInvalid;
import mx.challenge.simpleservicestats.aspect.CountValid;
import mx.challenge.simpleservicestats.data.input.Request;
import mx.challenge.simpleservicestats.service.CustomerService;
import mx.challenge.simpleservicestats.service.IpBlacklistService;
import mx.challenge.simpleservicestats.service.StubService;
import mx.challenge.simpleservicestats.service.UaBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

@RestController
@RequestMapping("user")
public class CustomerRequestController {

    private final CustomerService customerService;
    private final IpBlacklistService ipBlacklistService;
    private final UaBlacklistService uaBlacklistService;
    private final StubService stubService;


    @Autowired
    public CustomerRequestController(CustomerService customerService, IpBlacklistService ipBlacklistService,
                                     UaBlacklistService uaBlacklistService, StubService stubService) {
        this.customerService = customerService;
        this.ipBlacklistService = ipBlacklistService;
        this.uaBlacklistService = uaBlacklistService;
        this.stubService = stubService;
    }



    @CountValid
    @PostMapping("request")
    public Request request(@RequestBody @Valid Request request) {

        stubService.stubMethod(request);

        return request;
    }

    @CountInvalid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getBindingResult().getAllErrors()
                .stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST);
    }

}
