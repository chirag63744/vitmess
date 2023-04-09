package com.example.androidproject;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class adapter_rec2 extends FirebaseRecyclerAdapter<user2, adapter_rec2.personsViewholder> {
    public adapter_rec2(@NonNull FirebaseRecyclerOptions<user2> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull personsViewholder holder, int position, @NonNull user2 model) {

        holder.itemName.setText(model.getItem());
        holder.Quantity.setText(""+model.getQuantity());
        holder.price.setText(model.getPrice());
    }


    @NonNull
    @Override
    public adapter_rec2.personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);
        return new personsViewholder(view);
    }


    static class personsViewholder extends RecyclerView.ViewHolder
    {
        TextView itemName,Quantity,price;


        public personsViewholder(@NonNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.itemName);
            Quantity=itemView.findViewById(R.id.Quantity);
            price=itemView.findViewById(R.id.Price);
        }
    }
}

