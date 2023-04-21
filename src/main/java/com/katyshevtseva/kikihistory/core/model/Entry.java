package com.katyshevtseva.kikihistory.core.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value1;

    private String value2;

    @Column(name = "image_1")
    private String image1;

    @Enumerated(EnumType.STRING)
    private EntryType type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
