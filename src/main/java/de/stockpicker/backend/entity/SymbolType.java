package de.stockpicker.backend.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Entität zur Speicherung der Index-Typen
 */
@Entity
@Data
public class SymbolType {

    public SymbolType() {
    }

    public SymbolType(String name, String key) {
        this.name = name;
        this.key = key;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type_name")
    private String name;

    @Column(name = "type_key")
    private String key;
}
