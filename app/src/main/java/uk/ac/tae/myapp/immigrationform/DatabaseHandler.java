package uk.ac.tae.myapp.immigrationform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;

import uk.ac.tae.myapp.immigrationform.Immigrant;
import uk.ac.tae.myapp.immigrationform.ImmigrantContact;
import uk.ac.tae.myapp.immigrationform.ImmigrantRecord;

/**
 * Created by Karma on 11/12/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    //For Database Setup
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "immigration_db";

    //For Immigrant Table
    private static final String IMMIGRANT_TABLE = "Immigrants";
    private static final String id = "ID";
    private static final String fname = "First_Name";
    private static final String lname = "Last_Name";
    private static final String gender = "Gender";
    private static final String country = "Nationality";
    private static final String dob = "DOB";
    private static final String imagePath = "Image_Path";// FIXME: 31/12/15

    //For Contact Table
    private static final String CONTACT_TABLE = "Contacts";
    private static final String cID = "Contact_ID";
    private static final String cAddress1 = "Address_Line1";
    private static final String cAddress2 = "Address_Line2";
    private static final String cCity = "City";
    private static final String cPostcode = "Post_Code";
    private static final String cCountry = "Country";
    private static final String cEmail = "Email";
    private static final String cImmigrantID = "Immigrant_ID";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates Immigrant Database
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createImmigrantTable(db);
        createContactTable(db);

    }

    private void createImmigrantTable(SQLiteDatabase db) {
        String CREATE_IMMIGRANT_TABLE = "CREATE TABLE " + IMMIGRANT_TABLE + " (" + id + " INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT, " +
                fname + " VARCHAR(25) NOT NULL, " + lname + " VARCHAR(25) NOTNULL, " + dob + " CHAR(10) NOT NULL, " + gender + " CHAR(6) NOT NULL, " +
                country + " VARCHAR(25) NOT NULL)";
        db.execSQL(CREATE_IMMIGRANT_TABLE);

    }

    private void createContactTable(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + CONTACT_TABLE + "(" + cImmigrantID + " INTEGER PRIMARY KEY, " + cID + " INT FOREIGN KEY, " +
                cEmail + " VARCHAR(30), " + cAddress1 + " VARCHAR(25), " + cAddress2 + " VARCHAR(25) NULL, " + cCity + " VARCHAR(15), " +
                cPostcode + " VARCHAR(10), " + cCountry + " VARCHAR(25), FOREIGN KEY (" + cID + ") REFERENCES " + IMMIGRANT_TABLE + "(" + id + "))";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + IMMIGRANT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);

        // Create tables again
        onCreate(db);
    }

    /**
     * The method inserts data using ContentValue classes.
     *
     * @param immigrant
     */
    public void addImmigrant(Immigrant immigrant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(fname, immigrant.fname);
        cv.put(lname, immigrant.lname);
        cv.put(gender, immigrant.gender);
        cv.put(country, immigrant.country);
        cv.put(dob, immigrant.dob);
        db.insert(IMMIGRANT_TABLE, null, cv);
        db.close();
    }

    /**
     * Insert ContactS into the CONTACT_TABLE BY Using SQL query
     *
     * @param contact
     */
    public void addContact(ImmigrantContact contact) {
        String INSERT_CONTACT = "INSERT INTO " + CONTACT_TABLE + "(" + cEmail + ", " + cAddress1 + ", " + cAddress2 + ", " + cCity + ", " +
                cPostcode + ", " + cCountry + ", " + cImmigrantID + ") VALUES (" + contact.email + ", " + contact.addrLine1 + ", " +
                contact.addrLine2 + ", " + contact.addrCity + ", " + contact.addrPostcode + ", " + contact.addrCountry + ", " + contact.immigrant_id + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(INSERT_CONTACT);
    }

    /**
     * The method returns a record of an immigrant whose id is iKey
     *
     * @param iKey
     */

    public ImmigrantRecord selectImmigrant(int iKey) {
        Immigrant immigrant;
        ImmigrantContact contact;
        ImmigrantRecord immigrantRecord;
        SQLiteDatabase db = this.getReadableDatabase();
        immigrant = getImmigrant(db, iKey);
        contact = getContactRecord(db, iKey);
        immigrantRecord = new ImmigrantRecord(immigrant, contact);
        db.close();
        return immigrantRecord;
    }

    private ImmigrantContact getContactRecord(SQLiteDatabase db, int iKey) {
        ImmigrantContact iContact = new ImmigrantContact();
        ArrayList<ImmigrantContact> contacts = new ArrayList<>();
        Cursor cursor = db.query(CONTACT_TABLE, new String[]{cID, cAddress1, cAddress1, cAddress2, cCity, cPostcode, cCountry, cImmigrantID},
                cImmigrantID + "=?", new String[]{String.valueOf(iKey)}, null, null, null, null);
        //Get Column indeces + Values of those columns
        cursor.moveToFirst();
        do {
            int key = cursor.getInt(cursor.getColumnIndex(cID));
            int key2 = cursor.getInt(cursor.getColumnIndex(cImmigrantID));
            String addrLine1 = cursor.getString(cursor.getColumnIndex(cAddress1));
            String addrLine2 = cursor.getString(cursor.getColumnIndex(cAddress2));
            String addrCity = cursor.getString(cursor.getColumnIndex(cCity));
            String addrPostcode = cursor.getString(cursor.getColumnIndex(cPostcode));
            String addrCountry = cursor.getString(cursor.getColumnIndex(cCountry));
            iContact.setImmigrant_id(key);
            iContact.setContact_id(key2);
            iContact.setAddrLine1(addrLine1);
            iContact.setAddrLine2(addrLine2);
            iContact.setAddrCity(addrCity);
            iContact.setAddrPostcode(addrPostcode);
            iContact.setAddrCountry(addrCountry);
            contacts.add(iContact);
        } while (cursor.moveToNext());

        //iContact.setf;
        return iContact;
    }

    private Immigrant getImmigrant(SQLiteDatabase db, int iKey) {
        Immigrant immigrant = new Immigrant();
        Cursor cursor = db.rawQuery("SELECT * from " + IMMIGRANT_TABLE + " where " + id + " = ? ", new String[]{iKey + ""});
        cursor.moveToFirst();
        do {
            int iID = cursor.getInt(cursor.getColumnIndex(id));
            String iFname = cursor.getString(cursor.getColumnIndex(fname));
            String iLname = cursor.getString(cursor.getColumnIndex(lname));
            String iDob = cursor.getString(cursor.getColumnIndex(dob));
            String iGender = cursor.getString(cursor.getColumnIndex(gender));
            String iNationality = cursor.getString(cursor.getColumnIndex(country));
            String iImagePath = cursor.getString(cursor.getColumnIndex(imagePath));
            immigrant.set_id(iID);
            immigrant.setFname(iFname);
            immigrant.setLname(iLname);
            immigrant.setDob(iDob);
            immigrant.setGender(iGender);
            immigrant.setCountry(iNationality);
            immigrant.setImagePath(iImagePath);

        } while (cursor.moveToNext());
        return immigrant;
    }

    public ArrayList<ImmigrantRecord> getAllImmigrants() {
        ArrayList<ImmigrantRecord> immigrantRecords = new ArrayList<>();
        ImmigrantRecord record;
        Cursor cursor = getReadableDatabase().rawQuery("SELECT " + id + " FROM " + IMMIGRANT_TABLE, null);
        if (cursor.moveToFirst()) {
            do {
                int key = cursor.getInt(cursor.getColumnIndex(id));
                record = selectImmigrant(key);
                immigrantRecords.add(record);
            } while (cursor.moveToNext());
        }
        getReadableDatabase().close();
        return immigrantRecords;
    }
}
