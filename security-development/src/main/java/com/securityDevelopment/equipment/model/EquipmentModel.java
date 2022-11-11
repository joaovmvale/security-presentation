package com.securityDevelopment.equipment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "EQUIPMENT")
public class EquipmentModel {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "serial", nullable = false, unique = true)
    private UUID serial;

    @Column(name = "model", nullable = false, unique = true)
    private String model;

    @Column(name = "memory", nullable = false)
    private String memory;

    @Column(name = "processor", nullable = false)
    private String processor;

    @Column(name = "storage", nullable = false)
    private String storage;

    public EquipmentModel(UUID serial, String model, String memory, String processor, String storage) {
        this.serial = serial;
        this.model = model;
        this.memory = memory;
        this.processor = processor;
        this.storage = storage;
    }

    @PrePersist
    protected void onCreate() {
        setSerial(java.util.UUID.randomUUID());
        setId(java.util.UUID.randomUUID());
    }
}
