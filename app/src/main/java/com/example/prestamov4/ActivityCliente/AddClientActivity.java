package com.example.prestamov4.ActivityCliente;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
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

public class AddClientActivity extends AppCompatActivity {

    EditText etNombre;
    EditText etApellido;
    Spinner spSexo;
    EditText etTelefono;
    EditText etCedula;
    EditText etOcupacion;
    EditText etDireccion;

    private List<ClienteConPrestamo> clienteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        spSexo = findViewById(R.id.spSexo);
        etTelefono = findViewById(R.id.etTelefono);
        etCedula = findViewById(R.id.etCedula);
        etOcupacion = findViewById(R.id.etOcupacion);
        etDireccion = findViewById(R.id.etDireccion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_cancel,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
                    ClienteConPrestamo c = new ClienteConPrestamo();

                    c.getCliente().setNombre(etNombre.getText().toString());
                    c.getCliente().setApellido(etApellido.getText().toString());
                    c.getCliente().setSexo(spSexo.getSelectedItem().toString());
                    c.getCliente().setTelefono(etTelefono.getText().toString());
                    c.getCliente().setCedula(etCedula.getText().toString());
                    c.getCliente().setOcupacion(etOcupacion.getText().toString());
                    c.getCliente().setDireccion(etDireccion.getText().toString());

                    try {
                        Long id = DbPrestamo.getAppDatabase(AddClientActivity.this).clienteDao().insertar(c.getCliente());
                        c.getCliente().setId(id.intValue());
                        clienteList.add(0,c);
                        Toast.makeText(AddClientActivity.this, "Guardado!", Toast.LENGTH_SHORT).show();
                    }
                    catch (SQLiteConstraintException e)
                    {
                        Toast.makeText(AddClientActivity.this, "Error al insertar \n" +
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    String resultado = etNombre.getText().toString();
                    Intent i = getIntent();
                    i.putExtra("ResGuardado", "Ingreso de nuevo cliente " + resultado);
                    setResult(RESULT_OK, i);

                    finish();
                }
                break;
            case R.id.mnCancelar:
                Intent i = getIntent();
                i.putExtra("ResCancelado", "Cancelo ingreso nuevo cliente");
                setResult(RESULT_CANCELED, i);

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
