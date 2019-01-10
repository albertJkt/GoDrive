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

import org.w3c.dom.Text;

public class FuelActivity extends AppCompatActivity {

    EditText amount;
    EditText price;
    DatabaseReference databaseFuel;
    String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);


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

        amount = (EditText)findViewById(R.id.textAmountOfFuel);
        price = (EditText)findViewById(R.id.textPricePerLiter);

        databaseFuel = FirebaseDatabase.getInstance().getReference("fuel");


    }

    public void SaveFuelInfo(View v)
    {
        if (!TextUtils.isEmpty(amount.getText().toString()) || !TextUtils.isEmpty(price.getText().toString())) {
            Float amountOfFuel = Float.parseFloat(amount.getText().toString());
            Float priceOfFuel = Float.parseFloat(price.getText().toString());

            String fuelID = databaseFuel.push().getKey();

            Fuel fuel = new Fuel(amountOfFuel,priceOfFuel,newString,fuelID);

            databaseFuel.child(fuelID).setValue(fuel);

            Toast.makeText(this, "Pridėta", Toast.LENGTH_LONG).show();
            amount.setText("");
            price.setText("");

        }
        else{
            Toast.makeText(getApplicationContext(),"Neįvestas litrų skaičius arba kainą už litrą", Toast.LENGTH_LONG).show();
        }


    }
    public void homeButtonClick (View v)
    {
        Intent intent = new Intent(this, FirstScreen.class);
        intent.putExtra("USERNAME", newString );
        startActivity(intent);
    }
    public void serviceButtonClick (View v)
    {
        Intent intent = new Intent(this, ServiceActivity.class);
        intent.putExtra("USERNAME", newString );
        startActivity(intent);
    }
    public void camButtonClick(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("USERNAME", newString);
        startActivity(intent);
    }
    public String getUname(String username){

        int uNameIndex = username.lastIndexOf(".");
        String uName = username.substring(0,uNameIndex);

        return uName;
    }
}
