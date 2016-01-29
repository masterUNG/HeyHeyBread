package appewtc.masterung.heyheybread;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ConfirmOrderActivity extends AppCompatActivity {

    //Explicit
    private TextView dateTextView, nameTextView, addressTextView,
            phoneTextView, totalTextView;
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

        //Show View
        showView();

    }   // Main Method

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

    }   // readAllData

    private void bindWidget() {

        dateTextView = (TextView) findViewById(R.id.textView18);
        nameTextView = (TextView) findViewById(R.id.textView19);
        addressTextView = (TextView) findViewById(R.id.textView20);
        phoneTextView = (TextView) findViewById(R.id.textView21);
        totalTextView = (TextView) findViewById(R.id.textView22);
        orderListView = (ListView) findViewById(R.id.listView2);

    }   // bindWidget

}   // Main Class
