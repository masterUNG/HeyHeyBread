package appewtc.masterung.heyheybread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    public void clickSave(View view) {

        //Check Space
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        addressString = addressEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();

        if (userString.equals("") ||
                passwordString.equals("") ||
                nameString.equals("") ||
                surnameString.equals("") ||
                addressString.equals("") ||
                phoneString.equals("")) {

            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(RegisterActivity.this, "Have Space", "กรุณากรอกทุกช่อง คะ");

        } else {

            //No Space


        } // if

    }   // clickSave

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPass);
        nameEditText = (EditText) findViewById(R.id.edtName);
        surnameEditText = (EditText) findViewById(R.id.edtSurname);
        addressEditText = (EditText) findViewById(R.id.edtAddress);
        phoneEditText = (EditText) findViewById(R.id.edtPhone);
    }

}   // Main Class
