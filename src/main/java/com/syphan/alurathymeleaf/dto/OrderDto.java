package com.syphan.alurathymeleaf.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDto {

    public OrderDto() {}

    public OrderDto(String name, String description, BigDecimal price, LocalDateTime arriveDate, String productUrl, String imageUrl, Integer status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.arriveDate = arriveDate;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
        this.status = status;
    }

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private LocalDateTime arriveDate;

    @NotBlank
    private String productUrl;

    @NotBlank
    private String imageUrl;

    @NotNull
    @Min(1)
    @Max(3)
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDateTime arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
