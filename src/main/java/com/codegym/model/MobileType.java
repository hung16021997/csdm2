package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "mobileType")
public class MobileType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mobileTypeId;
    private String mobileTypeName;
    private String mobileTypeDescription;
    private String mobileTypeStatus;

    @ManyToOne
    @JoinColumn(name = "producerId")
    private Producer producer;

    @OneToMany (targetEntity = Mobile.class)
    private Set<Mobile> mobiles;

    public MobileType() {
    }

    public MobileType(String mobileTypeName, String mobileTypeDescription, String mobileTypeStatus, Producer producer, Set<Mobile> mobiles) {
        this.mobileTypeName = mobileTypeName;
        this.mobileTypeDescription = mobileTypeDescription;
        this.mobileTypeStatus = mobileTypeStatus;
        this.producer = producer;
        this.mobiles = mobiles;
    }

    public Long getMobileTypeId() {
        return mobileTypeId;
    }

    public void setMobileTypeId(Long mobileTypeId) {
        this.mobileTypeId = mobileTypeId;
    }

    public String getMobileTypeName() {
        return mobileTypeName;
    }

    public void setMobileTypeName(String mobileTypeName) {
        this.mobileTypeName = mobileTypeName;
    }

    public String getMobileTypeDescription() {
        return mobileTypeDescription;
    }

    public void setMobileTypeDescription(String mobileTypeDescription) {
        this.mobileTypeDescription = mobileTypeDescription;
    }

    public String getMobileTypeStatus() {
        return mobileTypeStatus;
    }

    public void setMobileTypeStatus(String mobileTypeStatus) {
        this.mobileTypeStatus = mobileTypeStatus;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Set<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(Set<Mobile> mobiles) {
        this.mobiles = mobiles;
    }
}
