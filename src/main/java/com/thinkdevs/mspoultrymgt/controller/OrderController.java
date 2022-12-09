package com.thinkdevs.mspoultrymgt.controller;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.OrdersDto;
import com.thinkdevs.mspoultrymgt.data.respose.Response;
import com.thinkdevs.mspoultrymgt.model.Orders;
import com.thinkdevs.mspoultrymgt.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.thinkdevs.mspoultrymgt.data.respose.Response.ofData;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/poultry/order")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @ApiOperation(value = "",
            response = ResponseEntity.class)
    public ResponseEntity<Response<List<Orders>>> getAllOrders() {
        return ok(ofData(orderService.getAllOrders()));
    }

    @GetMapping("/{orderId}")
    @ApiOperation(value = "Get Order",
            response = ResponseEntity.class)
    public ResponseEntity<Response<Orders>> getOrder(@PathVariable Integer orderId) {
        return ok(ofData(orderService.getOrder(orderId)));
    }


    @PutMapping("/{orderId}")
    public ResponseEntity<Response<FarmProjection>> updateOrder(@PathVariable Integer orderId,
                                                                       @RequestBody OrdersDto orderDto) {
        return ok(ofData(orderService.updateOrders(orderId, orderDto)));
    }

    @PostMapping
    public ResponseEntity<Response<Orders>> saveOrder(@RequestBody OrdersDto ordersDto) {
        return new ResponseEntity<>(ofData(orderService.addOrder(ordersDto), 200), HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return noContent().build();
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Response<FarmProjection>> updateStatus(@PathVariable Integer orderId,
                                                              @RequestParam("status")String status) {
        return new ResponseEntity<>(ofData(orderService.updateStatus(orderId,status),
                200), HttpStatus.CREATED);
    }

//    @GetMapping("/statistics")
//    @ApiOperation(value = "Get Feeds statistic",
//            response = ResponseEntity.class)
//    public ResponseEntity<Response<FeedStatistics>> getStatistic() {
//        return ok(ofData(orderService.feedStatistics()));
//    }
//


}
