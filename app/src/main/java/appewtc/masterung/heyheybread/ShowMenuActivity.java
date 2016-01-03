package appewtc.masterung.heyheybread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ShowMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);

        //ListView Controller
        listViewController();

    }   // onCreate

    private void listViewController() {

        //Setup Value Array
        ManageTABLE objManageTABLE = new ManageTABLE(this);
        String[] iconStrings = objManageTABLE.readAllBread(4);
        String[] breadStrings = objManageTABLE.readAllBread(1);
        String[] priceStrings = objManageTABLE.readAllBread(2);
        String[] stockStrings = objManageTABLE.readAllBread(3);

        ListView menuListView = (ListView) findViewById(R.id.listView);
        MenuAdapter objMenuAdapter = new MenuAdapter(ShowMenuActivity.this,
                iconStrings, breadStrings, priceStrings, stockStrings);
        menuListView.setAdapter(objMenuAdapter);


    }   // listViewController

}   // Main Class
