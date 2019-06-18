package com.example.prestamov4.obj;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static android.arch.persistence.room.ForeignKey.RESTRICT;

@Entity(tableName = "PagoTB")
public class Pago {
    @PrimaryKey(autoGenerate = true)
    private int id_pago;
    private Double cantidad;
    @ForeignKey(entity = Prestamo.class,
            parentColumns = "id",
            childColumns = "id_prestamo",
            onDelete = CASCADE,
            onUpdate = RESTRICT)
    private int id_prestamo;

    public Pago() { }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }
}
