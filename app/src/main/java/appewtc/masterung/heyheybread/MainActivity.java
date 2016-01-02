package appewtc.masterung.heyheybread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManageTABLE objManageTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connected Database
        objManageTABLE = new ManageTABLE(this);

        //Test Add New Value
        testAddValue();

    }   // onCreate

    private void testAddValue() {
        objManageTABLE.addNewUser("testUser", "testPass", "testName",
                "testSurname", "testAddress", "testPhone", "testComplacency");
        objManageTABLE.addNewBread("testBread", "testPrice", "testAmount", "testImage");
        objManageTABLE.addNewOrder("testName", "testSurname", "testAddress", "testPhone",
                "testBread", "testPrice", "testItem");
    }

}   // Main Class
