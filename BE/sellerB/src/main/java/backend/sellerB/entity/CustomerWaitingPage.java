package backend.sellerB.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_customer_waiting_page", schema = "sellerb", catalog = "")
public class CustomerWaitingPage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_waiting_page_seq")
    private Long customerWaitingPageSeq;
    @ManyToOne
    @JoinColumn(name = "product_seq")
    @JsonBackReference
    private Product product;
    @Basic
    @Column(name = "customer_waiting_page_ment")
    private String customerWaitingPageMent;
    @Basic
    @Column(name = "customer_waiting_page_image")
    private String customerWaitingPageImage;

    @Basic
    @Column(name = "manager_del_yn",columnDefinition = "boolean default false")
    private Boolean customerWaitingPageDelYn;

    @CreatedBy
    @Basic
    @Column(name = "manager_reg_user_seq")
    private Long customerWaitingPageRegUserSeq;

    @CreatedDate
    @Basic
    @Column(name = "manager_reg_date")
    private LocalDateTime customerWaitingPageRegDate;

    @LastModifiedBy
    @Basic
    @Column(name = "manager_mod_user_seq")
    private Long customerWaitingPageModUserSeq;

    @LastModifiedDate
    @Basic
    @Column(name = "manager_mod_date")
    private LocalDateTime customerWaitingPageModDate;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerWaitingPage that = (CustomerWaitingPage) o;
        return customerWaitingPageSeq == that.customerWaitingPageSeq && product == that.product && Objects.equals(customerWaitingPageMent, that.customerWaitingPageMent) && Objects.equals(customerWaitingPageImage, that.customerWaitingPageImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerWaitingPageSeq, product, customerWaitingPageMent, customerWaitingPageImage);
    }
}