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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    final int VIEW_TYPE_FUEL = 1;
    final int VIEW_TYPE_SERVICE = 0;
    private ArrayList<Fuel> fuelList ;
    private ArrayList<Service> serviceList;
    Context mContext;

    public  RecyclerViewAdapter (ArrayList<Service> services,ArrayList<Fuel> fuel, Context context)
    {
        this.fuelList = fuel;
        this.serviceList = services;
        this.mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (i == VIEW_TYPE_FUEL)
        {
            System.out.println("onCreateViewHolder position: " + i);
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_fuel, parent, false);

            return new FuelViewHolder(itemView);
        }

        if (i == VIEW_TYPE_SERVICE)
        {
            System.out.println("onCreateViewHolder position: " + i);
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_service, parent, false);

            return new ServiceViewHolder(itemView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FuelViewHolder)
        {
            final Fuel fuel = fuelList.get(i-serviceList.size());
            NumberFormat formatter = new DecimalFormat("#0.00");
            ((FuelViewHolder) viewHolder).fuelPrice.setText("Kaina € : " + formatter.format(fuel.getOverallPrice()));
            ((FuelViewHolder) viewHolder).layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Įpilto kūro kiekis: " + fuel.getAmount().toString(), Toast.LENGTH_LONG).show();
                }
            });

        }
        if (viewHolder instanceof ServiceViewHolder)
        {
            final Service service = serviceList.get(i);
            NumberFormat formatter = new DecimalFormat("#0.00");
            ((ServiceViewHolder) viewHolder).serviceType.setText(service.getServiceType());
            ((ServiceViewHolder) viewHolder).servicePrice.setText("Kaina € : " + formatter.format(service.getPrice()));
        }

    }

    @Override
    public int getItemCount() {
        return fuelList.size()+serviceList.size();
    }
    @Override
    public int getItemViewType(int position){
        if(position < serviceList.size()){
            return VIEW_TYPE_SERVICE;
        }

        if(position - serviceList.size() < fuelList.size()){
            return VIEW_TYPE_FUEL;
        }

        return -1;
    }

    public class FuelViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView kuroPylimas, fuelPrice;
        ConstraintLayout layout;
        ImageView line;

        public FuelViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageGas);
            kuroPylimas = itemView.findViewById(R.id.textKuroPylimas);
            fuelPrice = itemView.findViewById(R.id.textFuelPrice);
            layout = itemView.findViewById(R.id.parent_layout);
            line = itemView.findViewById(R.id.imageLine);
        }
    }
    public class ServiceViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView serviceType, servicePrice;
        ConstraintLayout layout;
        ImageView line;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageService);
            serviceType = itemView.findViewById(R.id.ServiceText);
            servicePrice = itemView.findViewById(R.id.ServicePriceText);
            layout = itemView.findViewById(R.id.parent_layout_service);
            line = itemView.findViewById(R.id.imageLineService);
        }
    }
}
