package com.example.prestamov4.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.prestamov4.obj.Cliente;
import com.example.prestamov4.obj.Prestamo;

import java.util.ArrayList;
import java.util.List;

public class ClienteConPrestamo {
    @Embedded
    Cliente cliente;

    @Relation(parentColumn ="_id" ,
            entityColumn = "id_cliente",
            entity = Prestamo.class)
    List<Prestamo> prestamoList;

    public ClienteConPrestamo() {
        cliente = new Cliente();
        prestamoList = new ArrayList<>();
    }

    public ClienteConPrestamo(Cliente cliente, List<Prestamo> prestamoList) {
        this.cliente = cliente;
        this.prestamoList = prestamoList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }
}
