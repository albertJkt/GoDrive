package world.test.ajcom.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText loginText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginText = (EditText) findViewById(R.id.loginEmailText);
        passwordText = (EditText) findViewById(R.id.loginPasswordText);

    }

    public void loginClick(View v)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user");
        DatabaseReference user = ref.child(getUname(loginText.getText().toString()));

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                String email = user.getUserName();
                String password = user.getPassword();

                String email1 = loginText.getText().toString();
                String password1 = passwordText.getText().toString();


                if (email.equals(email1))
                {
                    if (password.equals(password1))
                    {
                        Intent intent = new Intent(getApplicationContext(), FirstScreen.class);
                        intent.putExtra("USERNAME", email1);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Blogai įvestas slaptažodis", Toast.LENGTH_LONG ).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Tokio vartotojo nėra", Toast.LENGTH_LONG ).show();
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
}
