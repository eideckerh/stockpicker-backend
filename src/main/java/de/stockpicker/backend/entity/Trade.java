package de.stockpicker.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Entität zur Speicherung von Trades
 */
@Entity
@Data
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "symbol_id", nullable = false)
    private Symbol symbol;

    @Column
    private Double volume;

    @Column
    private Date opened;

    @Column
    private Date closed;

    @Column
    private Double openValue;

    @Column
    private Double closeValue;
}
