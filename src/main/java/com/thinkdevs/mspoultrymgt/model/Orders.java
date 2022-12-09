package com.thinkdevs.mspoultrymgt.model;

import com.thinkdevs.mspoultrymgt.data.dto.OrdersDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@Where(clause = "active <> 'false'")
public class Orders extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phoneNumber;
    private String prodName;
    private BigDecimal cost;
    private String status;
    private Boolean active;

    public Orders(OrdersDto ordersDto) {
        this.status = "PENDING";
        setOrders(ordersDto);
    }

    public Orders(Orders orders, OrdersDto ordersDto) {
        this.id = orders.getId();
        this.status = orders.getStatus();
        setOrders(ordersDto);
    }

    public Orders(Orders response, Integer orderId, String status) {
        this.id = orderId;
        this.prodName = response.getProdName();
        this.phoneNumber = response.getPhoneNumber();
        this.cost = response.getCost();
        this.status = status;
        this.active = true;
        this.dateCreated = LocalDateTime.now();
        this.lastModifiedDate= LocalDateTime.now();
    }

    private void setOrders(OrdersDto ordersDto) {
        this.prodName = ordersDto.getProdName();
        this.phoneNumber = ordersDto.getPhoneNumber();
        this.cost = ordersDto.getCost();
        this.active = true;
        this.dateCreated = LocalDateTime.now();
        this.lastModifiedDate= LocalDateTime.now();
    }

    public static Orders createNewOrder(OrdersDto ordersDto){
        return new Orders(ordersDto);
    }

    public static Orders updateOrder(Orders response, OrdersDto ordersDto){
        return new Orders(response, ordersDto);
    }

    public static Orders updateOrderStatus(Orders response, Integer orderId, String status){
        return new Orders(response,orderId, status);
    }

}
