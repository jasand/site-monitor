package no.sensor.db.jpa;

import javax.persistence.*;

/**
 * Created by jan.arne.sandnes on 02.01.15.
 */

@Entity
@Table (name = "units_of_measure")
public class UnitOfMeasureEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "units_of_measure_id_seq")
    @javax.persistence.SequenceGenerator(name="units_of_measure_id_seq", sequenceName = "units_of_measure_id_seq")
    private long id;
    @Column(name = "unit_name")
    private String unitName;
    @Column(name = "unit_symbol")
    private String unitSymbol;

    public UnitOfMeasureEntity() {
    }

    public UnitOfMeasureEntity(String unitName, String unitSymbol) {
        this.unitName = unitName;
        this.unitSymbol = unitSymbol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(String unit_symbol) {
        this.unitSymbol = unit_symbol;
    }
}
