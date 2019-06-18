package com.example.prestamov4.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.prestamov4.obj.Cliente;
import com.example.prestamov4.obj.Pago;
import com.example.prestamov4.obj.Prestamo;

import java.util.ArrayList;
import java.util.List;

public class PrestamoConPago {
    @Embedded
    Prestamo prestamo2;

    @Relation(parentColumn ="id" ,
            entityColumn = "id_prestamo",
            entity = Pago.class)
    List<Pago> pagoList;

    public PrestamoConPago() {
        prestamo2 = new Prestamo();
        pagoList = new ArrayList<>();
    }

    public PrestamoConPago(Prestamo prestamo, List<Pago> pagoList) {
        this.prestamo2 = prestamo;
        this.pagoList = pagoList;
    }

    public Prestamo getPrestamo2() {
        return prestamo2;
    }

    public void setPrestamo2(Prestamo prestamo) {
        this.prestamo2 = prestamo;
    }

    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }
}
