package com.example.prestamov4;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prestamov4.ActivityCliente.AddClientActivity;
import com.example.prestamov4.ActivityCliente.VerClientesActivity;
import com.example.prestamov4.ActivityPrestamo.VerPrestamosActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvLogs;

    static int NuevoCliente = 4545;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogs = findViewById(R.id.tvLogs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnAddCliente:
                Intent intent = new Intent(MainActivity.this, AddClientActivity.class);
                startActivityForResult(intent, NuevoCliente);
                //startActivity(intent);
                break;
            case R.id.mnVerPrestamo:
                Intent intent2 = new Intent(MainActivity.this, VerPrestamosActivity.class);
                startActivity(intent2);
                break;
            case R.id.mnVerCliente:
                Intent intent3 = new Intent(MainActivity.this, VerClientesActivity.class);
                Toast.makeText(this, "Visualizar los clientes", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 4545)
        {
            String res;
            if (resultCode == RESULT_CANCELED)
            {
                res = data.getExtras().getString("ResCancelado");
                tvLogs.append("\n");
                tvLogs.append(res);
            }
            else
            {
                res = data.getExtras().getString("ResGuardado");
                tvLogs.append("\n");
                tvLogs.append(res);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
