package ecommerce.ecommerce.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RouterController {
    
    @GetMapping(value="/")
    public String indexg(){
        return "teste ...";
    }

    @PostMapping(value="/")
    public String indexp(){
        return "teste ...";
    }

}
