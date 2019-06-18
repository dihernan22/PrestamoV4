package com.example.prestamov4.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.prestamov4.R;
import com.example.prestamov4.obj.Prestamo;
import com.example.prestamov4.pojo.PrestamoConCliente;

import java.util.List;

public class LvAdapter extends BaseAdapter {
    private Activity activity;
    private List<PrestamoConCliente> prestamoList;

    public LvAdapter(Activity activity, List object)
    {
        this.activity = activity;
        this.prestamoList = object;
    }

    @Override
    public int getCount() {
        return prestamoList.size();
    }

    @Override
    public Object getItem(int position) {
        return prestamoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_prestamo, null);
        }

        Prestamo p = prestamoList.get(position).getPrestamo();

        TextView tvNombre = v.findViewById(R.id.tvNombre);
        TextView tvTotal = v.findViewById(R.id.tvTotal);
        TextView tvPlazo = v.findViewById(R.id.tviPlazo);

        tvNombre.setText(p.getNombres());
        tvTotal.setText(String.valueOf(p.getMontoPagar()));
        tvPlazo.setText(String.valueOf(p.getPlazo()));

        return v;
    }
}
