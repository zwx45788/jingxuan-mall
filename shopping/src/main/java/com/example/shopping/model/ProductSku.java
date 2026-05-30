package com.example.shopping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_sku")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    /**
     * 存储规格属性，例如：{"颜色": "红色", "尺寸": "XL"}
     * 使用 JdbcTypeCode(SqlTypes.JSON) 需要 Hibernate 6.1+ 版本
     */
    @Column(name = "spec_json", nullable = false, columnDefinition = "JSON")
    @JdbcTypeCode(SqlTypes.JSON)
    private String specJson;

    /**
     * 售价（分）
     */
    @Column(name = "price", nullable = false)
    private Long price;

    /**
     * 图片
     */
    @Column(name = "image", nullable = false, length = 255)
    private String image;

    /**
     * 库存
     */
    @Column(name = "stock", nullable = false)
    private Long stock;

    /**
     * 锁定库存
     */
    @Column(name = "locked_stock", nullable = false)
    private Long lockedStock;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
