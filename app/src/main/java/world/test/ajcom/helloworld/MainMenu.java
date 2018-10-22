package world.test.ajcom.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void registerButtonClick(View v)
    {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    public void loginButtonClick(View v)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
