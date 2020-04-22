package com.example.quickrepair;

public class MeasurementUnit
{
    private Unit unit;

    public MeasurementUnit() { }

    public MeasurementUnit(Unit unit)
    {
        this.unit = unit;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    public enum Unit
    {
        NONE,
        METER,
        SQR_METER,
        INCH,
        FOOT,
        SQR_INCH,
        SQR_FOOT
    }
}
