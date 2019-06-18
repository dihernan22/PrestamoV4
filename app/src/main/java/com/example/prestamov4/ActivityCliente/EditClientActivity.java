package com.example.prestamov4.ActivityCliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prestamov4.R;
import com.example.prestamov4.db.DbPrestamo;
import com.example.prestamov4.pojo.ClienteConPrestamo;

import java.util.ArrayList;
import java.util.List;

public class EditClientActivity extends AppCompatActivity {

    private List<ClienteConPrestamo> clienteList = new ArrayList<>();

    EditText etNombre;
    EditText etApellido;
    Spinner spSexo;
    EditText etCedula;
    EditText etTelefono;
    EditText etOcupacion;
    EditText etDireccion;

    int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        p = getIntent().getExtras().getInt("position");
        clienteList.addAll(DbPrestamo.getAppDatabase(this).clienteDao().obtenerTodo());

        etNombre = findViewById(R.id.eteNombre);
        etApellido = findViewById(R.id.eteApellido);
        spSexo = findViewById(R.id.speSexo);
        etTelefono = findViewById(R.id.eteTelefono);
        etCedula = findViewById(R.id.eteCedula);
        etOcupacion = findViewById(R.id.eteOcupacion);
        etDireccion = findViewById(R.id.eteDireccion);

        etNombre.setText(clienteList.get(p).getCliente().getNombre());
        etApellido.setText(clienteList.get(p).getCliente().getApellido());
        spSexo.setSelection(obtenerPosicionItem(spSexo, clienteList.get(p).getCliente().getSexo()));
        etTelefono.setText(clienteList.get(p).getCliente().getTelefono());
        etCedula.setText(clienteList.get(p).getCliente().getCedula());
        etOcupacion.setText(clienteList.get(p).getCliente().getOcupacion());
        etDireccion.setText(clienteList.get(p).getCliente().getDireccion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_cancel, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnGuardar:
                if (etNombre.getText().toString().isEmpty()
                        && etTelefono.getText().toString().isEmpty()
                        && etCedula.getText().toString().isEmpty()
                        && etDireccion.getText().toString().isEmpty()
                )
                {
                    etNombre.setError("");
                    etTelefono.setError("");
                    etCedula.setError("");
                    etDireccion.setError("");
                    Toast.makeText(this, "Revise los Campos", Toast.LENGTH_SHORT).show();
                }
                else if (etNombre.getText().toString().isEmpty())
                {
                    etNombre.setError("Campo Obligatorio");
                }
                else if (etTelefono.getText().toString().isEmpty())
                {
                    etTelefono.setError("Campo Obligatorio");
                }
                else if (etCedula.getText().toString().isEmpty())
                {
                    etCedula.setError("Campo Obligatorio");
                }
                else if (etDireccion.getText().toString().isEmpty())
                {
                    etDireccion.setError("Campo Obligatorio");
                }
                else
                {
                    clienteList.get(p).getCliente().setNombre(etNombre.getText().toString());
                    clienteList.get(p).getCliente().setApellido(etApellido.getText().toString());
                    clienteList.get(p).getCliente().setSexo(spSexo.getSelectedItem().toString());
                    clienteList.get(p).getCliente().setTelefono(etTelefono.getText().toString());
                    clienteList.get(p).getCliente().setCedula(etCedula.getText().toString());
                    clienteList.get(p).getCliente().setOcupacion(etOcupacion.getText().toString());
                    clienteList.get(p).getCliente().setDireccion(etDireccion.getText().toString());

                    DbPrestamo.getAppDatabase(EditClientActivity.this).clienteDao().actualizar(clienteList.get(p).getCliente());

                    Toast.makeText(this, "Cliente Actualizado", Toast.LENGTH_SHORT).show();

                    /*Intent i = getIntent();
                    i.putExtra("pos", p);*/
                    setResult(RESULT_OK);

                    finish();
                }
                break;
            case R.id.mnCancelar:
                /*Intent i = getIntent();
                i.putExtra("posCancelado", p);*/
                setResult(RESULT_CANCELED);

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static int obtenerPosicionItem(Spinner spinner, String sexo)
    {
        int posicion = 0;

        for (int i = 0; i < spinner.getCount(); i++)
        {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(sexo)) {
                posicion = i;
            }
        }

        return posicion;
    }
}
