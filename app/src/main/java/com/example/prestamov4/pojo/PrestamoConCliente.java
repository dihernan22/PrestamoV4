package com.example.prestamov4.pojo;

import android.arch.persistence.room.Embedded;

import com.example.prestamov4.obj.Cliente;
import com.example.prestamov4.obj.Prestamo;

public class PrestamoConCliente {
    @Embedded
    Cliente cliente;

    @Embedded
    Prestamo prestamo;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
}
