package com.example.prestamov4.pojo;

import android.arch.persistence.room.Embedded;

import com.example.prestamov4.obj.Pago;
import com.example.prestamov4.obj.Prestamo;

public class PagoConPrestamo {
    @Embedded
    Prestamo prestamo;

    @Embedded
    Pago pago;

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
