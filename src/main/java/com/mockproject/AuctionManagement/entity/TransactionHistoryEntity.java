package com.mockproject.AuctionManagement.entity;

import com.mockproject.AuctionManagement.enums.TransactionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_transaction_history")
public class TransactionHistoryEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long idTransaction;

    @Column(name = "transaction_amount")
    @Positive
    private Double transactionAmount;

    @Column(name = "content")
    private String content;

    @Column(name = "sender_account_number")
    private String senderAccountNumber;

    @Column(name = "bank")
    private String bank;

    @Column(name = "account_owner_name")
    private String accountOwnerName;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "recipient_account_number")
    private String recipientAccountNumber;

    @Column(name = "status_transaction")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "day_trading")
    @Temporal(TemporalType.DATE)
    private Date dateTrading;

    @Column(name = "status")
    private Integer status;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;
}
