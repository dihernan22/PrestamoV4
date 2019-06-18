package com.example.prestamov4.obj;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static android.arch.persistence.room.ForeignKey.RESTRICT;

@Entity(tableName = "PrestamoTB")
public class Prestamo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Nombres;
    private Double montoCred;
    private int plazo;
    private int interes;
    private String fechaIni;
    private String fechaFin;
    private Double montoPagar;
    private Double montoCuota;
    private Double montoPagado;
    @ForeignKey(entity = Cliente.class,
            parentColumns = "_id",
            childColumns = "id_cliente",
            onDelete = CASCADE,
            onUpdate = RESTRICT)
    private int id_cliente;

    public Prestamo(){}

    public int getId() { return id; }

    public void setId(int iD) { id = iD; }

    public int getId_cliente() { return id_cliente; }

    public void setId_cliente(int iD_Cliente) { id_cliente = iD_Cliente; }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public Double getMontoCred() {
        return montoCred;
    }

    public void setMontoCred(Double montoCred) {
        this.montoCred = montoCred;
    }

    public int getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(Double montoPagar) {
        this.montoPagar = montoPagar;
    }

    public Double getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(Double montoCuota) {
        this.montoCuota = montoCuota;
    }

    public Double getMontoPagado() { return montoPagado; }

    public void setMontoPagado(Double montopagado) { montoPagado = montopagado; }
}
