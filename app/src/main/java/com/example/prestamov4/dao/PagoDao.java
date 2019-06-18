package com.example.prestamov4.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.prestamov4.obj.Pago;
import com.example.prestamov4.pojo.PagoConPrestamo;

import java.util.List;

@Dao
public interface PagoDao {
    @Insert
    Long insertar(Pago pago);

    @Query("Select * from pagotb inner join prestamotb on id=:id_pPago group by id_pago")
    List<PagoConPrestamo> ObtenerConPrestamo(int id_pPago);
}
