package com.example.prestamov4.ActivityPrestamo;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prestamov4.Adapter.Lv2Adapter;
import com.example.prestamov4.Adapter.LvAdapter;
import com.example.prestamov4.R;
import com.example.prestamov4.db.DbPrestamo;
import com.example.prestamov4.obj.Pago;
import com.example.prestamov4.pojo.PagoConPrestamo;
import com.example.prestamov4.pojo.PrestamoConCliente;
import com.example.prestamov4.pojo.PrestamoConPago;

import java.util.ArrayList;
import java.util.List;

public class VerUnPrestamoActivity extends AppCompatActivity {

    private List<PagoConPrestamo> pagoList = new ArrayList<>();

    Lv2Adapter adapter;
    ListView lvPagos;

    TextView tvMonto, tvPlazo, tvFecha, tvFechaFinal;
    TextView tvMontoPagar, tvMontoPagado, tvInteres, tvNombres;

    int p, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_un_prestamo);

        List<PrestamoConCliente> prestamoConClientes = new ArrayList<>();
        p = getIntent().getExtras().getInt("position");
        prestamoConClientes.addAll(DbPrestamo.getAppDatabase(this).prestamoDao().obtenerConCliente());

        tvNombres = findViewById(R.id.etNombre);
        tvMonto = findViewById(R.id.etMontoCredito);
        tvInteres = findViewById(R.id.spInteres);
        tvPlazo = findViewById(R.id.etPlazo);
        tvFecha = findViewById(R.id.etFecha);
        tvFechaFinal = findViewById(R.id.etFechaFinal);
        tvMontoPagar = findViewById(R.id.txtMontoPagar);
        tvMontoPagado = findViewById(R.id.txtMontoCuota);

        id = prestamoConClientes.get(p).getPrestamo().getId();
        tvNombres.setText(prestamoConClientes.get(p).getPrestamo().getNombres());
        tvMonto.setText(String.valueOf(prestamoConClientes.get(p).getPrestamo().getMontoCred()));
        tvInteres.setText(String.valueOf(prestamoConClientes.get(p).getPrestamo().getInteres()));
        tvPlazo.setText(String.valueOf(prestamoConClientes.get(p).getPrestamo().getPlazo()));
        tvFecha.setText(prestamoConClientes.get(p).getPrestamo().getFechaIni());
        tvFechaFinal.setText(prestamoConClientes.get(p).getPrestamo().getFechaFin());
        tvMontoPagar.setText(String.valueOf(prestamoConClientes.get(p).getPrestamo().getMontoPagar()));

        pagoList.clear();
        pagoList.addAll(DbPrestamo.getAppDatabase(this).pagoDao().ObtenerConPrestamo(id));
        lvPagos = findViewById(R.id.lvPagos);
        adapter = new Lv2Adapter(this, pagoList);
        lvPagos.setAdapter(adapter);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(VerUnPrestamoActivity.this);
            builder.setTitle("Pago");
            final View view = LayoutInflater.from(VerUnPrestamoActivity.this)
                    .inflate(R.layout.dialog_pago,null,false);
            builder.setView(view);
            builder.setNegativeButton("cancelar",null);
            builder.setPositiveButton("guardar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText etCantidad = view.findViewById(R.id.etPago);

                    Pago pago = new Pago();
                    pago.setCantidad(Double.parseDouble(etCantidad.getText().toString()));
                    pago.setId_prestamo(id);
                    try {
                        DbPrestamo.getAppDatabase(VerUnPrestamoActivity.this).pagoDao().insertar(pago);
                        Toast.makeText(VerUnPrestamoActivity.this, "Pago guardado!",
                                Toast.LENGTH_SHORT).show();
                        //adapter.notifyDataSetChanged();
                    }
                    catch (SQLiteConstraintException e)
                    {
                        Toast.makeText(VerUnPrestamoActivity.this, "Error \n" +e.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
