package com.example.prestamov4.ActivityPrestamo;

import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prestamov4.R;
import com.example.prestamov4.db.DbPrestamo;
import com.example.prestamov4.obj.Prestamo;
import com.example.prestamov4.pojo.ClienteConPrestamo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddPrestamoActivity extends AppCompatActivity {

    //private List<ClienteConPrestamo> clienteList = new ArrayList<>();

    String[] nombres = new String[1];
    ArrayAdapter<String> adapter;

    int interes, plazo = 1, id;
    Double total, cuota, monto = 0.0;

    EditText etMonto, etPlazo, etFecha, etFechaFinal;

    TextView tvMontoPagar, tvMontoCuota;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prestamo);

        spinner = findViewById(R.id.spinner);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            id = bundle.getInt("id_Cliente");
            String value = bundle.getString("Nombres");
            nombres[0] = value;
        }

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        spinner.setAdapter(adapter);

        etMonto = findViewById(R.id.etMontoCredito);
        etPlazo = findViewById(R.id.etPlazo);

        tvMontoPagar = findViewById(R.id.txtMontoPagar);
        tvMontoCuota = findViewById(R.id.txtMontoCuota);

        etFecha = findViewById(R.id.etFecha);

        //Fecha Inicio
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        etFecha.setText(formateador.format(new Date()));

        Spinner spInteres = findViewById(R.id.spInteres);
        spInteres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                interes = Integer.parseInt(parent.getSelectedItem().toString());

                ActualizarDatos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())
                {
                    if (etMonto.getText().hashCode() == s.hashCode()) {
                        monto = Double.parseDouble(s.toString());
                    }
                    else if (etPlazo.getText().hashCode() == s.hashCode())
                    {
                        plazo = Integer.parseInt(s.toString());
                    }

                    ActualizarDatos();
                }
                else
                {
                    if (etMonto.getText().hashCode() == s.hashCode()) {
                        monto = 0.0;
                    }

                    if (etPlazo.getText().hashCode() == s.hashCode()) {
                        plazo = 1;
                    }

                    ActualizarDatos();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        etMonto.addTextChangedListener(textWatcher);
        etPlazo.addTextChangedListener(textWatcher);
    }

    public void ActualizarDatos()
    {
        etFechaFinal = findViewById(R.id.etFechaFinal);

        total = (double) monto + ((monto * interes / 100) * plazo);
        cuota = total / plazo;

        SimpleDateFormat fechaEnd = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,plazo);
        etFechaFinal.setText(fechaEnd.format(calendar.getTime()));

        tvMontoPagar.setText(String.valueOf(total));
        tvMontoCuota.setText(String.valueOf(cuota));
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
                Prestamo p = new Prestamo();

                p.setNombres(spinner.getSelectedItem().toString());
                p.setMontoCred(monto);
                p.setInteres(interes);
                p.setPlazo(plazo);
                p.setFechaIni(etFecha.getText().toString());
                p.setFechaFin(etFechaFinal.getText().toString());
                p.setMontoPagar(Double.parseDouble(tvMontoPagar.getText().toString()));
                p.setMontoCuota(Double.parseDouble(tvMontoCuota.getText().toString()));
                p.setId_cliente(id);

                try {
                    DbPrestamo.getAppDatabase(AddPrestamoActivity.this).prestamoDao().insertar(p);
                    Toast.makeText(AddPrestamoActivity.this, "Guardado!", Toast.LENGTH_SHORT).show();
                }
                catch (SQLiteConstraintException e)
                {
                    Toast.makeText(AddPrestamoActivity.this, "Error \n" +e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                finish();
                break;
            case R.id.mnCancelar:

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
