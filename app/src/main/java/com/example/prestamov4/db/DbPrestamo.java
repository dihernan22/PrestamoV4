package com.example.prestamov4.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.prestamov4.dao.ClienteDao;
import com.example.prestamov4.dao.PagoDao;
import com.example.prestamov4.dao.PrestamoDao;
import com.example.prestamov4.obj.Cliente;
import com.example.prestamov4.obj.Pago;
import com.example.prestamov4.obj.Prestamo;

@Database(entities = {Cliente.class, Prestamo.class, Pago.class},version = 1)
public abstract class DbPrestamo extends RoomDatabase {
    private static DbPrestamo INSTANCE;
    public abstract ClienteDao clienteDao();
    public abstract PrestamoDao prestamoDao();
    public abstract PagoDao pagoDao();
    public static DbPrestamo getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), DbPrestamo.class, "db")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
}
