package com.example.prestamov4.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.prestamov4.obj.Cliente;
import com.example.prestamov4.pojo.ClienteConPrestamo;

import java.util.List;

@Dao
public interface ClienteDao {
    @Insert
    Long insertar(Cliente cliente);

    @Delete
    void borrar(Cliente cliente);

    @Update
    void actualizar(Cliente cliente);

    @Query("Select * from ClienteTB")
    List<ClienteConPrestamo> obtenerTodo();
}
