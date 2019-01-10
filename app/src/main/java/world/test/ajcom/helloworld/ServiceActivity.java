package world.test.ajcom.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceActivity extends AppCompatActivity {

    String newString ="";
    EditText serviceType;
    EditText servicePrice;
    DatabaseReference databaseService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

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

        serviceType = (EditText)findViewById(R.id.textService);
        servicePrice = (EditText)findViewById(R.id.textServicePrice);

        databaseService = FirebaseDatabase.getInstance().getReference("service");
    }

    public void homeButtonClick(View v)
    {
        Intent intent = new Intent(this, FirstScreen.class);
        intent.putExtra("USERNAME", newString);
        startActivity(intent);
    }

    public void fuelClick(View v)
    {
        Intent intent = new Intent(this, FuelActivity.class);
        intent.putExtra("USERNAME", newString);
        startActivity(intent);
    }
    public void camButtonClick(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("USERNAME", newString);
        startActivity(intent);
    }

    public void SaveService(View v) {
        if (!TextUtils.isEmpty(serviceType.getText().toString()) || !TextUtils.isEmpty(servicePrice.getText().toString())) {
            Float priceOfService = Float.parseFloat(servicePrice.getText().toString());

            String serviceID = databaseService.push().getKey();

            Service service = new Service(serviceType.getText().toString(), priceOfService, newString, serviceID);

            databaseService.child(serviceID).setValue(service);

            Toast.makeText(this, "Pridėta", Toast.LENGTH_LONG).show();
            serviceType.setText("");
            servicePrice.setText("");

        } else {
            Toast.makeText(getApplicationContext(), "Neįvestas remonto tipas arba kainą", Toast.LENGTH_LONG).show();
        }
    }
}
