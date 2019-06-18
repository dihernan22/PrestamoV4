package com.example.prestamov4.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.prestamov4.R;
import com.example.prestamov4.obj.Cliente;
import com.example.prestamov4.pojo.ClienteConPrestamo;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ClienteHolder> {
    private List<ClienteConPrestamo> clienteList;

    private OnClickDeleteItemListener onClickDeleteItemListener;
    private OnClickEditItemListener onClickEditItemListener;
    private OnClickItemListener onClickItemListener;
    private OnClickTextListener onClickTextListener;

    public interface OnClickDeleteItemListener
    {
        void onItemClick(ClienteConPrestamo cliente, int pos);
    }

    public interface OnClickEditItemListener
    {
        void onItemClick(ClienteConPrestamo cliente, int pos);
    }

    public interface OnClickItemListener
    {
        void onItemClick(Cliente cliente, int pos);
    }

    public interface OnClickTextListener
    {
        void onItemClick(ClienteConPrestamo cliente, int pos);
    }


    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public void setOnClickEditItemListener(OnClickEditItemListener onClickEditItemListener) {
        this.onClickEditItemListener = onClickEditItemListener;
    }

    public void setOnClickDeleteItemListener(OnClickDeleteItemListener onClickDeleteItemListener) {
        this.onClickDeleteItemListener = onClickDeleteItemListener;
    }

    public void setOnClickTextListener(OnClickTextListener onClickTextListener) {
        this.onClickTextListener = onClickTextListener;
    }

    public RvAdapter(List<ClienteConPrestamo> clienteList) {
        this.clienteList = clienteList;
    }

    @NonNull
    @Override
    public ClienteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_cliente,viewGroup,false);
        return new ClienteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteHolder clienteHolder, int i) {
        clienteHolder.tvNombre.setText(clienteList.get(i).getCliente().getNombre());
        clienteHolder.tvApellido.setText(clienteList.get(i).getCliente().getApellido());
    }

    @Override
    public int getItemCount() {
        return clienteList.size();
    }

    public class ClienteHolder extends RecyclerView.ViewHolder
    {
        private TextView tvNombre;
        private TextView tvApellido;
        private ImageButton btnClientePrestamo;
        private ImageButton btnBorrar;
        private ImageButton btnEditar;

        public ClienteHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tviNombre);
            tvApellido = itemView.findViewById(R.id.tviApellido);

            btnClientePrestamo = itemView.findViewById(R.id.ibClientePrestamo);
            btnClientePrestamo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItemListener.onItemClick(clienteList.get(getAdapterPosition()).getCliente(), getAdapterPosition());
                }
            });

            btnEditar =  itemView.findViewById(R.id.ibEditar);
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEditItemListener.onItemClick(clienteList.get(getAdapterPosition()),getAdapterPosition());
                }
            });

            btnBorrar = itemView.findViewById(R.id.ibDelete);
            btnBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickDeleteItemListener.onItemClick(clienteList.get(getAdapterPosition()), getAdapterPosition());
                }
            });

            tvNombre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickTextListener.onItemClick(clienteList.get(getAdapterPosition()), getAdapterPosition());
                }
            });

            tvApellido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickTextListener.onItemClick(clienteList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
