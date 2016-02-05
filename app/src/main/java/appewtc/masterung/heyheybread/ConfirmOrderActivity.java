package appewtc.masterung.heyheybread;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class ConfirmOrderActivity extends AppCompatActivity {

    //Explicit
    private TextView dateTextView, nameTextView, addressTextView,
            phoneTextView, totalTextView, idReceiveTextView;
    private String dateString, nameString, surnameString, addressString,
            phoneString, totalString;
    private ListView orderListView;
    private int totalAnInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        //Bind Widget
        bindWidget();

        //Read ALL Data
        readAllData();

        //Find ID receive
        findIDreceive();

        //Show View
        showView();

    }   // Main Method

    private void findIDreceive() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_ORDER_FINISH, null);
        objCursor.moveToFirst();
        objCursor.moveToLast();

        String strIDreceive = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_idReceive));
        Log.d("Receive", "Receive Last = " + strIDreceive);

        objCursor.close();



    }   // findIDreceive

    public void clickFinish(View view) {

        //Read All orderTABLE
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_ORDER, null);
        objCursor.moveToFirst();
        for (int i=0;i<objCursor.getCount();i++) {

            String strDate = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Date));
            String strName = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Name));
            String strSurname = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Surname));
            String strAddress = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Address));
            String strPhone = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Phone));
            String strBread = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Bread));
            String strPrice = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Price));
            String strItem = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Item));

            //Update to mySQL
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                    .Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

            try {

                ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
                objNameValuePairs.add(new BasicNameValuePair(ManageTABLE.COLUMN_Date, strDate));
                objNameValuePairs.add(new BasicNameValuePair(ManageTABLE.COLUMN_Name, strName));
                objNameValuePairs.add(new BasicNameValuePair(ManageTABLE.COLUMN_Surname, strSurname));
                objNameValuePairs.add(new BasicNameValuePair(ManageTABLE.COLUMN_Address, strAddress));
                objNameValuePairs.add(new BasicNameValuePair(ManageTABLE.COLUMN_Phone, strPhone));
                objNameValuePairs.add(new BasicNameValuePair(ManageTABLE.COLUMN_Bread, strBread));
                objNameValuePairs.add(new BasicNameValuePair(ManageTABLE.COLUMN_Price, strPrice));
                objNameValuePairs.add(new BasicNameValuePair(ManageTABLE.COLUMN_Item, strItem));

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/mos/php_add_order_master.php");
                objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                objHttpClient.execute(objHttpPost);

                if (i == (objCursor.getCount() - 1)) {
                    Toast.makeText(ConfirmOrderActivity.this, "Update Order Finish",
                            Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                Log.d("hey", "Error Cannot Update to mySQL ==> " + e.toString());
            }

            objCursor.moveToNext();

        }   // for
        objCursor.close();

        // Intent HubActivity
        Intent objIntent = new Intent(ConfirmOrderActivity.this, HubActivity.class);
        String strID = getIntent().getStringExtra("idUser");
        objIntent.putExtra("ID", strID);
        startActivity(objIntent);

    }   // clickFinish


    public void clickMore(View view) {
        finish();
    }

    private void showView() {
        dateTextView.setText("วันที่ " + dateString);
        nameTextView.setText(nameString + " " + surnameString);
        addressTextView.setText("ที่อยู่ " + addressString);
        phoneTextView.setText("Phone = " + phoneString);
        totalTextView.setText(Integer.toString(totalAnInt));
    }

    private void readAllData() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
        objCursor.moveToFirst();
        dateString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Date));
        nameString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Name));
        surnameString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Surname));
        addressString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Address));
        phoneString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Phone));

        String[] nameOrderStrings = new String[objCursor.getCount()];
        String[] priceStrings = new String[objCursor.getCount()];
        String[] itemStrings = new String[objCursor.getCount()];
        String[] noStrings = new String[objCursor.getCount()];
        String[] amountStrings = new String[objCursor.getCount()];


        for (int i=0; i<objCursor.getCount();i++) {

            nameOrderStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Bread));
            priceStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Price));
            itemStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Item));
            noStrings[i] = Integer.toString(i + 1);
            amountStrings[i] = Integer.toString( Integer.parseInt(itemStrings[i]) * Integer.parseInt(priceStrings[i]) );

            objCursor.moveToNext();

            totalAnInt = totalAnInt + Integer.parseInt(amountStrings[i]);

        }   // for

        objCursor.close();

        //Create Listview
        MyOrderAdapter objMyOrderAdapter = new MyOrderAdapter(ConfirmOrderActivity.this,
                noStrings, nameOrderStrings, itemStrings, priceStrings, amountStrings);
        orderListView.setAdapter(objMyOrderAdapter);

        //Delete Order
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                myDeleteOrder(i);

            }   // event
        });

    }   // readAllData

    private void myDeleteOrder(int position) {

        final SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_ORDER, null);
        objCursor.moveToFirst();
        objCursor.moveToPosition(position);
        String strBread = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Bread));
        String strItem = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Item));
        final String strID = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_id));
        Log.d("Hay", "ID ==> " + strID);


        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_myaccount);
        objBuilder.setTitle("Are You Sure ?");
        objBuilder.setMessage("Delete Order " + strBread + " " + strItem + " ชิ้น");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int intID = Integer.parseInt(strID);
                objSqLiteDatabase.delete(ManageTABLE.TABLE_ORDER,
                        ManageTABLE.COLUMN_id + "=" + intID, null );
                totalAnInt = 0;
                readAllData();
                totalTextView.setText(Integer.toString(totalAnInt));
                dialogInterface.dismiss();
            }
        });
        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.show();

        objCursor.close();

    }   // myDeleteOrder

    private void bindWidget() {

        dateTextView = (TextView) findViewById(R.id.textView18);
        nameTextView = (TextView) findViewById(R.id.textView19);
        addressTextView = (TextView) findViewById(R.id.textView20);
        phoneTextView = (TextView) findViewById(R.id.textView21);
        totalTextView = (TextView) findViewById(R.id.textView22);
        orderListView = (ListView) findViewById(R.id.listView2);
        idReceiveTextView = (TextView) findViewById(R.id.textView28);

    }   // bindWidget

}   // Main Class
