package com.springbootopenapi.springbootopenapi.resource;

import com.springbootopenapi.springbootopenapi.entity.Payment;
import com.springbootopenapi.springbootopenapi.repository.PaymentRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
@RequestMapping("/payment")
@Tag(name = "Payment Resource API")
public class PaymentResource {

    @Autowired
    public PaymentRepository paymentRepository;

    @GetMapping("/{paymentId}")
    @Operation(description = "Api to fetch the details based on payment Id",
            responses = {@ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")},
            tags = "GetPaymentById",
            parameters = {@Parameter(name = "paymentId", description = "Enter the paymentId to fetch details")},
            summary = "Api to fetch the details based on payment Id"
    )
    ResponseEntity<Payment> getPaymentById(@PathVariable("paymentId") String paymentId) throws Throwable {
        long parseLong = Long.parseLong(paymentId);
        Optional<Payment> byId = paymentRepository.findByPaymentId(parseLong);
        return ResponseEntity.ok().body(byId.orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new Exception();
            }
        }));
    }

    @GetMapping("")
    @Operation(description = "Api to fetch the all the payments available",
            responses = {@ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")},
            tags = "GetAllPayments",
            summary = "Api to fetch the all the payments available"
    )
    ResponseEntity<List<Payment>> getAllPayment() throws Throwable {
        List<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok().body(payments);
    }

    @PostMapping("")
    @Operation(description = "Api to create payment resource",
               responses = {@ApiResponse(responseCode = "201", description = "Payment Successfully created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")},
               tags = "CreatePayment",
               summary = "Api to create payment resource",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"paymentId\": 0,\n" +
                                    "  \"userId\": \"ssdf\",\n" +
                                    "  \"transactionId\": \"dsf\",\n" +
                                    "  \"transactionDate\": \"sdfsd\",\n" +
                                    "  \"amount\": 0\n" +
                                    "}")))
    )
    ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment paymentOutput = paymentRepository.save(payment);
        return ResponseEntity.ok().body(paymentOutput);
    }

    @PutMapping("")
    @Operation(description = "Api to update payment resource",
            responses = {@ApiResponse(responseCode = "201", description = "Payment Successfully updated"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")},
            tags = "CreatePayment",
            summary = "Api to update payment resource",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody
                    (content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"paymentId\": 0,\n" +
                                    "  \"userId\": \"ssdf\",\n" +
                                    "  \"transactionId\": \"dsf\",\n" +
                                    "  \"transactionDate\": \"sdfsd\",\n" +
                                    "  \"amount\": 0\n" +
                                    "}")))
    )
    ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
        Payment paymentOutput = paymentRepository.save(payment);
        return ResponseEntity.ok().body(paymentOutput);
    }

    @DeleteMapping("{paymentId}")
    @Operation(description = "Api to fetch the details based on payment Id",
            responses = {@ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")},
            tags = "GetPaymentById",
            parameters = {@Parameter(name = "paymentId", description = "Enter the paymentId to fetch details")},
            summary = "Api to fetch the details based on payment Id"
    )
    ResponseEntity<String> updatePayment(@PathVariable("paymentId") String paymentId) {
        paymentRepository.deleteById(paymentId);
        return ResponseEntity.ok().body("Successfully deleted");
    }
}
