package no.sensor.service.model;

import no.sensor.db.jpa.UnitOfMeasureEntity;

/**
 * Created by jan.arne.sandnes on 04.01.15.
 */
public class UnitOfMeasure {
    private long id;
    private String unitName;
    private String unitSymbol;

    public UnitOfMeasure() {
    }

    public UnitOfMeasure(UnitOfMeasureEntity entity) {
        this.id = entity.getId();
        this.unitName = entity.getUnitName();
        this.unitSymbol = entity.getUnitSymbol();
    }

    public UnitOfMeasureEntity toUnitOfMeasureEntity() {
        UnitOfMeasureEntity e = new UnitOfMeasureEntity();
        e.setId(id);
        e.setUnitName(unitName);
        e.setUnitSymbol(unitSymbol);
        return e;
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
