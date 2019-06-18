package com.example.prestamov4.ActivityCliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.prestamov4.Adapter.RvAdapter;
import com.example.prestamov4.R;
import com.example.prestamov4.db.DbPrestamo;
import com.example.prestamov4.obj.Cliente;
import com.example.prestamov4.pojo.ClienteConPrestamo;

import java.util.ArrayList;
import java.util.List;

public class VerClientesActivity extends AppCompatActivity {

    private List<ClienteConPrestamo> clienteList = new ArrayList<>();
    private RvAdapter adapter;

    RecyclerView rvCliente;

    static int EditarCliente = 4040;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_clientes);

        clienteList.addAll(DbPrestamo.getAppDatabase(this).clienteDao().obtenerTodo());
        rvCliente = findViewById(R.id.rvClient);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvCliente.setLayoutManager(manager);
        adapter = new RvAdapter(clienteList);

        adapter.setOnClickItemListener(new RvAdapter.OnClickItemListener() {
            @Override
            public void onItemClick(Cliente cliente, int pos) {
                //Intent intent = new Intent(VerClientesActivity.this, )

            }
        });

        adapter.setOnClickEditItemListener(new RvAdapter.OnClickEditItemListener() {
            @Override
            public void onItemClick(ClienteConPrestamo cliente, int pos) {
                Toast.makeText(VerClientesActivity.this, String.valueOf(pos), Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(VerClientesActivity.this, EditClientActivity.class);
                intent2.putExtra("position", pos);
                startActivityForResult(intent2, EditarCliente);
                //startActivity(intent2);
            }
        });

        adapter.setOnClickDeleteItemListener(new RvAdapter.OnClickDeleteItemListener() {
            @Override
            public void onItemClick(final ClienteConPrestamo cliente, final int pos) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerClientesActivity.this);
                builder.setMessage("Desea eliminar "+ cliente.getCliente().getNombre() +"?");
                builder.setNegativeButton("No",null);
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DbPrestamo.getAppDatabase(VerClientesActivity.this).clienteDao().borrar(cliente.getCliente());
                        clienteList.remove(cliente);
                        Toast.makeText(VerClientesActivity.this, "Eliminado!", Toast.LENGTH_SHORT).show();
                        adapter.notifyItemRemoved(pos);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        adapter.setOnClickTextListener(new RvAdapter.OnClickTextListener() {
            @Override
            public void onItemClick(ClienteConPrestamo cliente, int pos) {
                Toast.makeText(VerClientesActivity.this, String.valueOf(pos), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(VerClientesActivity.this, VerUnClienteActivity.class);
                intent.putExtra("position", pos);
                startActivity(intent);
            }
        });

        rvCliente.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 4040)
        {
            //int pos;
            if (resultCode == RESULT_CANCELED)
            {
                /*pos = data.getExtras().getInt("posCancelado");
                Toast.makeText(this, "actualizar cancelado" + pos, Toast.LENGTH_SHORT).show();*/
                Toast.makeText(this, "actualizar cancelado", Toast.LENGTH_SHORT).show();
            }
            else
            {
                /*pos = data.getExtras().getInt("pos");
                adapter.notifyItemChanged(pos);*/

                adapter.notifyDataSetChanged();
                Toast.makeText(this, "actualizar aqui estoy", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
