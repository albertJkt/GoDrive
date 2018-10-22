package world.test.ajcom.helloworld;

import android.app.backup.FullBackupDataOutput;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Fuel> fuelList ;
    Context mContext;

    public  RecyclerViewAdapter (ArrayList<Fuel> fuel, Context context)
    {
        this.fuelList = fuel;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_fuel, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Fuel fuel = fuelList.get(i);
        NumberFormat formatter = new DecimalFormat("#0.00");
        viewHolder.fuelPrice.setText("Kaina € : " + formatter.format(fuel.getOverallPrice()));
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Įpilto kūro kiekis: " + fuel.getAmount().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fuelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView kuroPylimas, fuelPrice;
        ConstraintLayout layout;
        ImageView line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageGas);
            kuroPylimas = itemView.findViewById(R.id.textKuroPylimas);
            fuelPrice = itemView.findViewById(R.id.textFuelPrice);
            layout = itemView.findViewById(R.id.parent_layout);
            line = itemView.findViewById(R.id.imageLine);
        }
    }
}
