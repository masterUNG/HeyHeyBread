package appewtc.masterung.heyheybread;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManageTABLE objManageTABLE;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    public String TAG = "hey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Connected Database
        objManageTABLE = new ManageTABLE(this);

        //Test Add New Value
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }   // onCreate

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
    }

    public void clickLogin(View view) {

        //Check Space
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(MainActivity.this, "Have Space", "Please Fill All Every Blank");
        } else {
            //No Space
            checkUser();
        }

    }   // clickLogin

    private void checkUser() {

    }   // checkUser


    private void synJSONtoSQLite() {

        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTimes = 1;
        while (intTimes <= 2) {

            InputStream objInputStream = null;
            String strJSON = null;
            String strURLuser = "http://swiftcodingthai.com/mos/php_get_user_master.php";
            String strURLbread = "http://swiftcodingthai.com/mos/php_get_bread_master.php";

            HttpPost objHttpPost = null;

            //1. Create InputStream
            try {

                HttpClient objHttpClient = new DefaultHttpClient();

                switch (intTimes) {
                    case 1:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strURLbread);
                        break;
                }   // switch

                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d(TAG, "InputStream ==> " + e.toString());
            }


            //2. Create JSON String
            try {

                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }   // while
                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d(TAG, "strJSON ==> " + e.toString());
            }


            //3. Update JSON String to SQLite
            try {

                JSONArray objJsonArray = new JSONArray(strJSON);

                for (int i=0;i<objJsonArray.length();i++) {

                    JSONObject object = objJsonArray.getJSONObject(i);

                    switch (intTimes) {
                        case 1:

                            String strUser = object.getString(ManageTABLE.COLUMN_User);
                            String strPassword = object.getString(ManageTABLE.COLUMN_Password);
                            String strName = object.getString(ManageTABLE.COLUMN_Name);
                            String strSurname = object.getString(ManageTABLE.COLUMN_Surname);
                            String strAddress = object.getString(ManageTABLE.COLUMN_Address);
                            String strPhone = object.getString(ManageTABLE.COLUMN_Phone);
                            String strComplacency = object.getString(ManageTABLE.COLUMN_Complacency);

                            objManageTABLE.addNewUser(strUser, strPassword, strName, strSurname,
                                    strAddress, strPhone, strComplacency);

                            break;
                        case 2:

                            String strBread = object.getString(ManageTABLE.COLUMN_Bread);
                            String strPrice = object.getString(ManageTABLE.COLUMN_Price);
                            String strAmount = object.getString(ManageTABLE.COLUMN_Amount);
                            String strImage = object.getString(ManageTABLE.COLUMN_Image);

                            objManageTABLE.addNewBread(strBread, strPrice, strAmount, strImage);

                            break;
                    }   // switch

                }   // for

            } catch (Exception e) {
                Log.d(TAG, "Update ==> " + e.toString());
            }


            intTimes += 1;
        }   // while


    }   // synJSONtoSQLite

    public void clickNewRegister(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }

    private void deleteAllSQLite() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_USER, null, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_BREAD, null, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_ORDER, null, null);
    }

    private void testAddValue() {
        objManageTABLE.addNewUser("testUser", "testPass", "testName",
                "testSurname", "testAddress", "testPhone", "testComplacency");
        objManageTABLE.addNewBread("testBread", "testPrice", "testAmount", "testImage");
        objManageTABLE.addNewOrder("testDate", "testName", "testSurname", "testAddress", "testPhone",
                "testBread", "testPrice", "testItem");
    }

}   // Main Class
