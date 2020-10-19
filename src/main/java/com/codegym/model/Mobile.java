package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "mobile")
public class Mobile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mobileId;
    @Size (min = 5, message = "Mobile name must be minimum 5 character")
    private String mobileName;
    @Min(value = 1000000, message = "Price must be minimum 1.000.000 VND")
    private Double mobilePrice;
    @Size(min = 5 , message = "Description must be minimum 5 character")
    private String mobileDescription;
    private boolean mobileStatus;
    private String mobileImage;

    @ManyToOne
    @JoinColumn (name = "mobileTypedId")
    private MobileType mobileType;

    @ManyToOne
    @JoinColumn (name = "producerId")
    private Producer producer;

    @ManyToOne
    @JoinColumn (name = "billDetailId")
    private BillDetail billDetail;

    public Mobile() {
    }

    public Mobile(@Size(min = 5) String mobileName, @Min(1000000) Double mobilePrice, @Size(min = 5) String mobileDescription, boolean mobileStatus, String mobileImage, MobileType mobileType, Producer producer, BillDetail billDetail) {
        this.mobileName = mobileName;
        this.mobilePrice = mobilePrice;
        this.mobileDescription = mobileDescription;
        this.mobileStatus = mobileStatus;
        this.mobileImage = mobileImage;
        this.mobileType = mobileType;
        this.producer = producer;
        this.billDetail = billDetail;
    }

    public Long getMobileId() {
        return mobileId;
    }

    public void setMobileId(Long mobileId) {
        this.mobileId = mobileId;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public Double getMobilePrice() {
        return mobilePrice;
    }

    public void setMobilePrice(Double mobilePrice) {
        this.mobilePrice = mobilePrice;
    }

    public String getMobileDescription() {
        return mobileDescription;
    }

    public void setMobileDescription(String mobileDescription) {
        this.mobileDescription = mobileDescription;
    }

    public boolean isMobileStatus() {
        return mobileStatus;
    }

    public void setMobileStatus(boolean mobileStatus) {
        this.mobileStatus = mobileStatus;
    }

    public String getMobileImage() {
        return mobileImage;
    }

    public void setMobileImage(String mobileImage) {
        this.mobileImage = mobileImage;
    }

    public MobileType getMobileType() {
        return mobileType;
    }

    public void setMobileType(MobileType mobileType) {
        this.mobileType = mobileType;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public BillDetail getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(BillDetail billDetail) {
        this.billDetail = billDetail;
    }
}
