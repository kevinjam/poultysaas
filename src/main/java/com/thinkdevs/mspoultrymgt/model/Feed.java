package com.thinkdevs.mspoultrymgt.model;

import com.thinkdevs.mspoultrymgt.data.dto.FeedDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "feeds")
@Where(clause = "active <> 'false'")
public class Feed extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer quantity;
    private String date;
    private Double price;
    private Double totalPrice;

    private String currency;
    private Boolean active;

    public Feed(FeedDto feedDto) {
        setData(feedDto);
    }

    private void setData(FeedDto feedDto) {
        this.name = feedDto.getName();
        this.quantity = feedDto.getQuantity();
        this.date = feedDto.getDate();
        this.price = feedDto.getPrice();
        this.totalPrice = feedDto.getPrice() * feedDto.getQuantity();
        this.currency = feedDto.getCurrency();
        this.active = true;
        this.dateCreated = LocalDateTime.now();
        this.lastModifiedDate= LocalDateTime.now();
    }

    public Feed(Feed response, FeedDto feedDto) {
        this.id = response.getId();
        setData(feedDto);

    }

    public static Feed createNewFeeds(FeedDto feedDto){
        return new Feed(feedDto);
    }

    public static Feed updateFeeds(Feed response, FeedDto feedDto){
        return new Feed(response, feedDto);
    }
}
