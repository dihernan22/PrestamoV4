package com.example.prestamov4.ActivityCliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prestamov4.ActivityPrestamo.AddPrestamoActivity;
import com.example.prestamov4.R;
import com.example.prestamov4.db.DbPrestamo;
import com.example.prestamov4.pojo.ClienteConPrestamo;

import java.util.ArrayList;
import java.util.List;

public class VerUnClienteActivity extends AppCompatActivity {

    private List<ClienteConPrestamo> clienteList = new ArrayList<>();

    TextView tvNombre, tvApellido, tvSexo, tvTelefono;
    TextView tvCedula, tvOcupacion, tvDireccion;

    int p, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_un_cliente);

        p = getIntent().getExtras().getInt("position");
        clienteList.addAll(DbPrestamo.getAppDatabase(this).clienteDao().obtenerTodo());

        tvNombre = findViewById(R.id.etvNombre);
        tvApellido = findViewById(R.id.etvApellido);
        tvSexo = findViewById(R.id.spvSexo);
        tvTelefono = findViewById(R.id.etvTelefono);
        tvCedula = findViewById(R.id.etvCedula);
        tvOcupacion = findViewById(R.id.etvOcupacion);
        tvDireccion = findViewById(R.id.etvDireccion);

        id = clienteList.get(p).getCliente().getId();
        tvNombre.setText(clienteList.get(p).getCliente().getNombre());
        tvApellido.setText(clienteList.get(p).getCliente().getApellido());
        tvSexo.setText(clienteList.get(p).getCliente().getSexo());
        tvTelefono.setText(clienteList.get(p).getCliente().getTelefono());
        tvCedula.setText(clienteList.get(p).getCliente().getCedula());
        tvOcupacion.setText(clienteList.get(p).getCliente().getOcupacion());
        tvDireccion.setText(clienteList.get(p).getCliente().getDireccion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()
                == R.id.mnAdd)
        {
            Toast.makeText(this, "Formulario Añadir Prestamo", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(VerUnClienteActivity.this, AddPrestamoActivity.class);
            intent.putExtra("id_Cliente", id);
            intent.putExtra("Nombres", tvNombre.getText() + " " + tvApellido.getText());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
