package world.test.ajcom.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.Map;

public class FirstScreen extends AppCompatActivity {

    String newString;
    TextView textView;
    TextView serviceText;
    ArrayList<Fuel> fuels = new ArrayList<>();
    ArrayList<Service> services = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("USERNAME");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("USERNAME");
        }

        textView = (TextView)findViewById(R.id.textFuelPriceAll);
        serviceText = (TextView)findViewById(R.id.textServicePriceAll);

        fetchDataService();
        fetchDataFuel();
        initRecyclerView(services,fuels);

    }

    public void fuelButtonClick(View v)
    {
        Intent intent = new Intent(this, FuelActivity.class);
        intent.putExtra("USERNAME", newString);
        startActivity(intent);
    }
    public void serviceButtonClick(View v)
    {
        Intent intent = new Intent(this, ServiceActivity.class);
        intent.putExtra("USERNAME", newString);
        startActivity(intent);
    }
    void fetchDataFuel()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("fuel");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Float overallFuelUsage=0f;
                for (DataSnapshot child : children)
                {
                   System.out.println("DataSnapshot");
                   Fuel fuel =  child.getValue(Fuel.class);
                   System.out.println(fuel);

                    if (fuel.getUsername().equals(newString))
                    {
                        fuels.add(fuel);
                        System.out.println(fuel);
                        overallFuelUsage +=fuel.getOverallPrice();
                    }
                }
                NumberFormat formatter = new DecimalFormat("#0.00");
                textView.setText("Iš viso išleista kūrui €: "+formatter.format(overallFuelUsage));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    void fetchDataService()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("service");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                Float overallService=0f;
                for (DataSnapshot child : children)
                {
                    System.out.println("DataSnapshot");
                    Service service =  child.getValue(Service.class);
                    System.out.println(service.getServiceType());

                    if (service.getUsername().equals(newString))
                    {
                        services.add(service);
                        System.out.println(service);
                        overallService += service.getPrice();

                    }

                    NumberFormat formatter = new DecimalFormat("#0.00");
                    serviceText.setText("Iš viso išleista servisui €: "+formatter.format(overallService));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public String getUname(String username){

        int uNameIndex = username.lastIndexOf(".");
        String uName = username.substring(0,uNameIndex);

        return uName;
    }

    private void initRecyclerView (ArrayList<Service> services, ArrayList<Fuel> arrayList)
    {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(services, arrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
