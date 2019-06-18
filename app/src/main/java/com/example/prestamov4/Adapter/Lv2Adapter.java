package com.example.prestamov4.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.prestamov4.R;
import com.example.prestamov4.obj.Pago;
import com.example.prestamov4.pojo.PagoConPrestamo;

import java.util.List;

public class Lv2Adapter extends BaseAdapter {
    private Activity activity;
    private List<PagoConPrestamo> pagoList;

    public Lv2Adapter(Activity activity, List object)
    {
        this.activity = activity;
        this.pagoList = object;
    }

    @Override
    public int getCount() {
        return pagoList.size();
    }

    @Override
    public Object getItem(int position) {
        return pagoList.get(position);
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
            v = inf.inflate(R.layout.item_pago, null);
        }

        Pago p = pagoList.get(position).getPago();

        TextView tvPago = v.findViewById(R.id.tvpPago);

        tvPago.setText(String.valueOf(p.getCantidad()));

        return v;
    }
}
