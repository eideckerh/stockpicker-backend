package de.stockpicker.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Entität zur Speicherung der API-Keys für AlphaVantage
 */
@Entity
@Data
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "api_key")
    private String key;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUse;
}
