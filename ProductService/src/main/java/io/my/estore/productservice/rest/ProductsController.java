package io.my.estore.productservice.rest;

import io.my.estore.productservice.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel createProductRestModel){
        CreateProductCommand createProductCommand=CreateProductCommand
                .builder()
                .productId(UUID.randomUUID().toString())
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle()).build();
        String returnValue = null;
        try{
            commandGateway.sendAndWait(createProductCommand);
        } catch (Exception e){
            returnValue=e.getLocalizedMessage();
        }

        return returnValue;
    }

    @GetMapping
    public String getProduct(){
        return "get product";
    }

    @PutMapping
    public String updateProduct(){
        return "update product";
    }

    @DeleteMapping
    public String deleteProduct(){
        return "delete product";
    }

}
