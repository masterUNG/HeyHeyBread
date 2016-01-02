package appewtc.masterung.heyheybread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText, nameEditText,
            surnameEditText, addressEditText, phoneEditText;
    private String userString, passwordString, nameString,
            surnameString, addressString, phoneString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Bind Widget
        bindWidget();

    }   // onCreate

    private void bindWidget() {

    }

}   // Main Class
