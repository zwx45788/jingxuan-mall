package com.example.shopping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_spu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shop_id", nullable = false)
    private Long shopId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "main_image", nullable = false, length = 255)
    private String mainImage;

    // 商品状态（2=已上架）
    @Column(name = "status", nullable = false)
    private Integer status;

    // 审核状态（1=已通过）
    @Column(name = "audit_status", nullable = false)
    private Integer auditStatus;

    // 金额建议使用 Long 类型存储（单位：分），或者 BigDecimal 存储元
    // 这里对应数据库 BIGINT
    @Column(name = "min_price", nullable = false)
    private Long minPrice;

    @Column(name = "max_price", nullable = false)
    private Long maxPrice;

    @Column(name = "total_stock", nullable = false)
    private Long totalStock;

    @Column(name = "sales_volume", nullable = false)
    private Long salesVolume;

    @Column(name = "comment_count", nullable = false)
    private Long commentCount;

    @Column(name = "positive_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal positiveRate;

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
