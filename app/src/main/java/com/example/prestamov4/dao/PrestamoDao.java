package com.example.prestamov4.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.prestamov4.obj.Prestamo;
import com.example.prestamov4.pojo.PrestamoConCliente;
import com.example.prestamov4.pojo.PrestamoConPago;

import java.util.List;

@Dao
public interface PrestamoDao {
    @Insert
    Long insertar(Prestamo prestamo);

    @Query("Select * from prestamotb")
    List<Prestamo> obtenerTodo();

    @Query("SELECT * FROM PrestamoTB")
    List<PrestamoConPago> ObtenerTodo();

    @Query("Select * from prestamotb inner join clientetb on _id=id_cliente")
    List<PrestamoConCliente> obtenerConCliente();
}
