package com.thinkdevs.mspoultrymgt.service;

import com.thinkdevs.mspoultrymgt.data.FarmProjection;
import com.thinkdevs.mspoultrymgt.data.dto.OrdersDto;
import com.thinkdevs.mspoultrymgt.model.Orders;
import com.thinkdevs.mspoultrymgt.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.thinkdevs.mspoultrymgt.data.FarmProjection.toProjection;
import static com.thinkdevs.mspoultrymgt.model.Orders.*;

@Service
@Transactional(readOnly = false)
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Orders addOrder(OrdersDto ordersDto) {
        return orderRepository.save(createNewOrder(ordersDto));
    }


    public FarmProjection updateOrders(Integer orderId, OrdersDto ordersDto) {
        var orders = orderRepository.findById(orderId)
                .map(response-> updateOrder(response, ordersDto))
                .orElseThrow(()->new EntityNotFoundException("Order not found for provided ID: " + orderId));
        orderRepository.saveAndFlush(orders);
        return toProjection(orders);
    }


    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    public Orders getOrder(Integer orderId) {
        log.info("Order Id --- {} ", orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found for provided ID "));
    }

    public FarmProjection updateStatus(Integer orderId,String status){
        var orders = orderRepository.findById(orderId)
                .map(response-> updateOrderStatus(response,orderId, status))
                .orElseThrow(()->new EntityNotFoundException("Order not found for provided ID: " + orderId));
        orderRepository.saveAndFlush(orders);
        return toProjection(orders);
    }
//    public FeedStatistics feedStatistics(){
//        return feedRepository.sumStatistics();
//    }
}
