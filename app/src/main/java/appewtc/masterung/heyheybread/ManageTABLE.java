package appewtc.masterung.heyheybread;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 1/2/16 AD.
 */
public class ManageTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String TABLE_USER = "userTABLE";
    public static final String COLUMN_id = "_id";
    public static final String COLUMN_User = "User";
    public static final String COLUMN_Password = "Password";
    public static final String COLUMN_Name = "Name";
    public static final String COLUMN_Surname = "Surname";
    public static final String COLUMN_Address = "Address";
    public static final String COLUMN_Phone = "Phone";
    public static final String COLUMN_Complacency = "Complacency";

    public static final String TABLE_BREAD = "breadTABLE";
    public static final String COLUMN_Bread = "Bread";
    public static final String COLUMN_Price = "Price";
    public static final String COLUMN_Amount = "Amount";
    public static final String COLUMN_Image = "Image";

    public static final String TABLE_ORDER = "orderTABLE";
    public static final String COLUMN_Item = "Item";

    public ManageTABLE(Context context) {

        //Create & Connected
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public long addNewBread(String strBread,
                            String strPrice,
                            String strAmount,
                            String strImage) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_Bread, strBread);
        objContentValues.put(COLUMN_Price, strPrice);
        objContentValues.put(COLUMN_Amount, strAmount);
        objContentValues.put(COLUMN_Image, strImage);

        return writeSqLiteDatabase.insert(TABLE_BREAD, null, objContentValues);
    }

    public long addNewUser(String strUser,
                           String strPassword,
                           String strName,
                           String strSurname,
                           String strAddress,
                           String strPhone,
                           String strComplacency) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_User, strUser);
        objContentValues.put(COLUMN_Password, strPassword);
        objContentValues.put(COLUMN_Name, strName);
        objContentValues.put(COLUMN_Surname, strSurname);
        objContentValues.put(COLUMN_Address, strAddress);
        objContentValues.put(COLUMN_Phone, strPhone);
        objContentValues.put(COLUMN_Complacency, strComplacency);

        return writeSqLiteDatabase.insert(TABLE_USER, null, objContentValues);
    }


}   // Main Class
