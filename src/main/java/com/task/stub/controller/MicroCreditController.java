package com.task.stub.controller;

import com.task.stub.model.request.PaymentRequestModel;
import com.task.stub.model.response.CheckResponseModel;
import com.task.stub.model.response.PaymentResponseModel;
import com.task.stub.service.MicroCreditService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@Tag(name="Система процессинга",
        description="процесс обработки и подтверждения платежей, совершаемых с использованием банковских карт, электронных кошельков и других платежных инструментов")
public class MicroCreditController {
    @Value("${PauseTime}")
    private int PauseTime;

    @Autowired
    private MicroCreditService service;

    @GetMapping(path="/v2/checkAccount",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary="Проверка информации о задолженностях по карте клиента")
    @Timed(value="getCreditInfo", description = "Информация о методе getCreditInfo")
    public ResponseEntity<?> getCreditInfo(@RequestParam("acc") int acc,
                                           @RequestParam("days") int days)
    {
        CheckResponseModel checkmodel = service.Check(acc, days);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(checkmodel);
    }

    @PostMapping(path="/v2/payment/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary="Подтверждение платежа")
    @Parameter(name = "BankCode", description = "Заголовок BankCode", required = true, in = ParameterIn.HEADER)
    @Timed(value="PaymentConfirm", description = "Информация о методе PaymentConfirm")
    public ResponseEntity<PaymentResponseModel> PaymentConfirm(@RequestBody PaymentRequestModel request,
                                                              @RequestHeader HttpHeaders header)
    {
        int sumBank = Integer.parseInt(header.get("BankCode").getFirst().toString());

        HttpHeaders httpheaders = new HttpHeaders();
        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        httpheaders.set("CustomHeader", timestamp);

        PaymentResponseModel responsemodel = service.Confirm(request, sumBank);

        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpheaders)
                .body(responsemodel);
    }

    @DeleteMapping(path="/v1/transactions/cleare/{id}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary="Удаление транзакции процессинга")
    @Timed(value="DeleteTransaction", description = "Информация о методе DeleteTransaction")
    public ResponseEntity<String> DeleteTransaction(HttpServletResponse DeleteResponse)
    {
        try {
            Thread.sleep(PauseTime);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        DeleteResponse.setStatus(100);
        return ResponseEntity.status(HttpStatus.OK)
                .body("deleted success");
    }

}
