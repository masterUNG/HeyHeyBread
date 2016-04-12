package appewtc.masterung.heyheybread;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditUser extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText,
            nameEditText, surnameEditText, addressEditText,
            phoneEditText;
    private String userString, passwordString, nameString,
            surnameString, addressString, phoneString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

    } // Main Method

    private void showView() {

        String strID = getIntent().getStringExtra("ID");
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase
                .rawQuery("SELECT * FROM userTABLE WHERE _id = " + "'" + strID + "'", null);
        cursor.moveToFirst();
        String[] resultStrings = new String[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            resultStrings[i] = cursor.getString(i);
        }   //for
        cursor.close();

        userEditText.setText(resultStrings[1]);
        passwordEditText.setText(resultStrings[2]);
        nameEditText.setText(resultStrings[3]);
        surnameEditText.setText(resultStrings[4]);
        addressEditText.setText(resultStrings[5]);
        phoneEditText.setText(resultStrings[6]);


    }   // showView

    public void clickSaveEdit(View view) {

    }   // clickSave

    public void clickCancelEdit(View view) {
        finish();
    }   // clickCancel

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText5);
        surnameEditText = (EditText) findViewById(R.id.editText6);
        addressEditText = (EditText) findViewById(R.id.editText7);
        phoneEditText = (EditText) findViewById(R.id.editText8);
    }

}   // Main Class
