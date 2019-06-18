package com.example.prestamov4.ActivityPrestamo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.prestamov4.ActivityCliente.AddClientActivity;
import com.example.prestamov4.Adapter.LvAdapter;
import com.example.prestamov4.MainActivity;
import com.example.prestamov4.R;
import com.example.prestamov4.db.DbPrestamo;
import com.example.prestamov4.pojo.PrestamoConCliente;

import java.util.ArrayList;
import java.util.List;

public class VerPrestamosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private LvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_prestamos);

        List<PrestamoConCliente> prestamoConClientes = new ArrayList<>();
        prestamoConClientes.addAll(DbPrestamo.getAppDatabase(this).prestamoDao().obtenerConCliente());
        ListView lvPrestamo = findViewById(R.id.lvVerPrestamo);
        adapter = new LvAdapter(this, prestamoConClientes);
        lvPrestamo.setAdapter(adapter);

        lvPrestamo.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(VerPrestamosActivity.this, VerUnPrestamoActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
