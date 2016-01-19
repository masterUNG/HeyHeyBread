package appewtc.masterung.heyheybread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ConfirmOrderActivity extends AppCompatActivity {

    //Explicit
    private TextView dateTextView, nameTextView, addressTextView,
            phoneTextView, totalTextView;
    private String dateString, nameString, addressString,
            phoneString, totalString;
    private ListView orderListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        //Bind Widget
        bindWidget();

    }   // Main Method

    private void bindWidget() {

        dateTextView = (TextView) findViewById(R.id.textView18);
        nameTextView = (TextView) findViewById(R.id.textView19);
        addressTextView = (TextView) findViewById(R.id.textView20);
        phoneTextView = (TextView) findViewById(R.id.textView21);
        totalTextView = (TextView) findViewById(R.id.textView22);
        orderListView = (ListView) findViewById(R.id.listView2);

    }   // bindWidget

}   // Main Class
