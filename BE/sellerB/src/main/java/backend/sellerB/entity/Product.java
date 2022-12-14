package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE t_product SET product_del_yn=true WHERE product_seq=?")
@Where(clause = "product_del_yn=false")
@Table(name = "t_product", schema = "sellerb", catalog = "")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_seq")
    private Long productSeq;
    @ManyToOne
    @JoinColumn(name = "product_group_seq")
    @JsonBackReference
    private ProductGroup productGroup;
    @Basic
    @Column(name = "product_id", length = 25)
    private String productId;
    @Basic
    @Column(name = "product_name", length = 25)
    private String productName;
    @Basic
    @Column(name = "product_price")
    private Integer productPrice;
    @Basic
    @Column(name = "product_manual")
    private String productManual;
    @Basic
    @Column(name = "product_thumbnail")
    private String productThumbnail;
    @Basic
    @Column(name = "product_del_yn",columnDefinition = "boolean default false")
    private Boolean productDelYn;
    @CreatedBy
    @Basic
    @Column(name = "product_reg_user")
    private String productRegUser;
    @CreatedDate
    @Basic
    @Column(name = "product_reg_date")
    private LocalDateTime productRegDate;
    @LastModifiedBy
    @Basic
    @Column(name = "product_mod_user")
    private String productModUser;
    @LastModifiedDate
    @Basic
    @Column(name = "product_mod_date")
    private LocalDateTime productModDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return productSeq == that.productSeq && productGroup == that.productGroup && Objects.equals(productId, that.productId) && Objects.equals(productName, that.productName) && Objects.equals(productPrice, that.productPrice) && Objects.equals(productManual, that.productManual) && Objects.equals(productThumbnail, that.productThumbnail) && Objects.equals(productDelYn, that.productDelYn) && Objects.equals(productRegUser, that.productRegUser) && Objects.equals(productRegDate, that.productRegDate) && Objects.equals(productModUser, that.productModUser) && Objects.equals(productModDate, that.productModDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productSeq, productGroup, productId, productName, productPrice, productManual, productThumbnail, productDelYn, productRegUser, productRegDate, productModUser, productModDate);
    }
}
