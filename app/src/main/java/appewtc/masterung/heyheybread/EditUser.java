package appewtc.masterung.heyheybread;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class EditUser extends AppCompatActivity {

    //Explicit
    private EditText passwordEditText,
            nameEditText, surnameEditText, addressEditText,
            phoneEditText;
    private TextView userTextView;
    private String userString, passwordString, nameString,
            surnameString, addressString, phoneString;
    private static final String urlSTRING = "http://swiftcodingthai.com/mos/php_edit_user_master.php";
    private String strID;

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

        strID = getIntent().getStringExtra("ID");
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

        userTextView.setText(resultStrings[1]);
        passwordEditText.setText(resultStrings[2]);
        nameEditText.setText(resultStrings[3]);
        surnameEditText.setText(resultStrings[4]);
        addressEditText.setText(resultStrings[5]);
        phoneEditText.setText(resultStrings[6]);


    }   // showView

    public void clickSaveEdit(View view) {


        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        addressString = addressEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();

        //Check Space
        if (checkSpace()) {

            //Have Space
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.errorDialog(this, "มีช่องว่าง", "กรุณากรอกให้ครบทุกช่อง คะ");

        } else {
            // No Space
            updateValueToServer();
        }

    }   // clickSave

    private void updateValueToServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("id", strID)
                .add("Password", passwordString)
                .add("Name", nameString)
                .add("Surname", surnameString)
                .add("Address", addressString)
                .add("Phone", phoneString)
                .build();
        Request.Builder builder = new Request.Builder();
        final Request request = builder.url(urlSTRING).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(EditUser.this, "แก้ไข้ให้แล้วคะ", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditUser.this, "แก้ไข้ให้ไม่ได้คะ", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        finish();

    }   // updateValue


    private boolean checkSpace() {
        return passwordString.equals("") ||
                nameString.equals("") ||
                surnameString.equals("") ||
                addressString.equals("") ||
                phoneString.equals("");
    }

    public void clickCancelEdit(View view) {
        finish();
    }   // clickCancel

    private void bindWidget() {
        userTextView = (TextView) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText5);
        surnameEditText = (EditText) findViewById(R.id.editText6);
        addressEditText = (EditText) findViewById(R.id.editText7);
        phoneEditText = (EditText) findViewById(R.id.editText8);
    }

}   // Main Class
