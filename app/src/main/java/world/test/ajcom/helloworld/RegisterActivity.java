package world.test.ajcom.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    DatabaseReference databaseUser;
    EditText emailText;
    EditText passwordText;
    EditText repeatpwText;
    EditText vinText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);


        emailText = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText)findViewById(R.id.passwordText);
        repeatpwText = (EditText) findViewById(R.id.repeatpwText);
        vinText = (EditText)findViewById(R.id.vinText);

        databaseUser = FirebaseDatabase.getInstance().getReference("user");
    }

    public void buttonOnClick(View v) {
        Button button = (Button) v;
        Effects ef = new Effects();
        ef.buttonEffect(button);
        addUser();

    }

    void addUser(){
        String username = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String repeatPass = repeatpwText.getText().toString();
        String vin = vinText.getText().toString();

        if (!TextUtils.isEmpty(username))
        {
            if (isValidEmailAddress(username))
            {
                if (password.equals(repeatPass) && (!TextUtils.isEmpty(password)||!TextUtils.isEmpty(repeatPass)))
                {
                    if (!TextUtils.isEmpty(vin))
                    {
                       String id = databaseUser.push().getKey();

                       User user = new User(username,password,id,vin);

                       databaseUser.child(getUname(username)).setValue(user);

                        Toast.makeText(this,"Registracija sėkminga!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(this,"Neįvestas VIN numeris", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(this,"Nesutampa slaptažodžiai", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(this,"Įvestas blogas el pašto adresas", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this,"Neįvestas el paštas", Toast.LENGTH_LONG).show();
        }
    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    public String getUname(String username){

        int uNameIndex = username.lastIndexOf(".");
        String uName = username.substring(0,uNameIndex);

        return uName;
    }
}
