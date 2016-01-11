package uk.ac.tae.myapp.immigrationform;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttOK, buttCancel;
    EditText etFName, etLName, etDOB, etAddrLine1, etAddrLine2, etAddrCity, etAddrPostcode, etAddrCountry, etEmail;
    ImageView photo;
    RadioGroup rgGender;
    Spinner spCountry;
    TextView tvError;
    Calendar calendar;
    String sfname, slname, sdob, sgender, sNationality, sAddressLine1, sAddressLine2, sAddressCity, sAddressPostcode, sAddressCountry, semail = "";
    List<ErrorMessage> errors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initiateViews();
    }

    /**
     * Initiates all the text fields, spinner, buttons and sets listener to buttons.
     */
    private void initiateViews() {
        photo = (ImageView) findViewById(R.id.ivPhoto);
        photo.setOnClickListener(this);
        etFName = (EditText) findViewById(R.id.etFName);
        etLName = (EditText) findViewById(R.id.etLName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        etDOB.setFocusable(false); //make keyboard invisible
//        etDOB.setCursorVisible(false);
        etAddrLine1 = (EditText) findViewById(R.id.etAddrLine1);
        etAddrLine2 = (EditText) findViewById(R.id.etAddrLine2);
        etAddrCity = (EditText) findViewById(R.id.etAddrCity);
        etAddrPostcode = (EditText) findViewById(R.id.etAddrPostcode);
        etAddrCountry = (EditText) findViewById(R.id.etAddrCountry);
        etEmail = (EditText) findViewById(R.id.etEmail);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        spCountry = (Spinner) findViewById(R.id.sNationality);
        startSpinner();
        buttOK = (Button) findViewById(R.id.buttOK);
        buttOK.setOnClickListener(MainActivity.this);
        buttCancel = (Button) findViewById(R.id.buttCancel);
        buttCancel.setOnClickListener(MainActivity.this);
        tvError = (TextView) findViewById(R.id.tvError);

    }

    /**
     * Retrieves country data from string xml and sets to spinner
     */
    public void startSpinner() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.country_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountry.setAdapter(spinnerAdapter);
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sNationality = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /************************************
     * Validation Part
     *********************************************/

    private List<ErrorMessage> validateForm() {
        errors = new ArrayList<>();
        sfname = etFName.getText().toString();
        if (isFieldEmpty(sfname) || sfname.equals(""))
            errors.add(ErrorMessage.FNAME);
        slname = etLName.getText().toString();
        if (isFieldEmpty(slname) || slname.equals(""))
            errors.add(ErrorMessage.LNAME);
        sdob = etDOB.getText().toString();
        if (isFieldEmpty(sdob) || sdob.equals(""))
            errors.add(ErrorMessage.DOB);
        sgender = getGender();
        if (isFieldEmpty(sgender) || sgender.equals(""))
            errors.add(ErrorMessage.GENDER);
        sNationality = spCountry.getSelectedItem().toString();
        if (spCountry.getSelectedItem() == null)
            errors.add(ErrorMessage.NATIONALITY);
        sAddressLine1 = etAddrLine1.getText().toString();
        if (isFieldEmpty(sAddressLine1) || sAddressLine1.equals(""))
            errors.add(ErrorMessage.ADDRLINE1);
        sAddressLine2 = etAddrLine2.getText().toString();
        if (isFieldEmpty(sAddressLine2) || sAddressLine2.equals(""))
            errors.add(ErrorMessage.ADDRLINE2);
        sAddressCity = etAddrCity.getText().toString();
        if (isFieldEmpty(sAddressCity) || sAddressCity.equals(""))
            errors.add(ErrorMessage.ADDRCITY);
        sAddressPostcode = etAddrPostcode.getText().toString();
        if (isFieldEmpty(sAddressPostcode) || sAddressPostcode.equals(""))
            errors.add(ErrorMessage.ADDRPOSTCODE);
        sAddressCountry = etAddrCountry.getText().toString();
        if (isFieldEmpty(sAddressCountry) || sAddressCountry.equals(""))
            errors.add(ErrorMessage.ADDRCOUNTRY);
        semail = etEmail.getText().toString();
        if (isEmailValid(semail))
            errors.add(ErrorMessage.EMAIL);
        return errors;
    }
//    private String validateForm() {
//        String error = "";
//        sfname = etFName.getText().toString();
//        Log.i("VALIDATATION", "First NAME :" + isTextHintText(etFName, "First Name"));
//        if (!isFieldEmpty(sfname) && !sfname.equals("First Name"))
//            error = "First Name Field Value is InValid\n";
//        slname = etLName.getText().toString();
//        Log.i("VALIDATATION", "SURNAME :" + isTextHintText(etLName, "Last Name"));
//        if (!isFieldEmpty(slname) && !slname.equals("Last Name"))
//            error = "Last Name Field Value is Invalid\n";
//        sdob = etDOB.getText().toString();
//        if (!isFieldEmpty(sdob) && !sdob.equals("DOB"))
//            error = "Please Select Date of Birth\n";
//        sgender = getGender();
//        if (!isFieldEmpty(sgender) && !sgender.equals("Gender"))
//            error = "Please specify your gender\n";
//        scountry = spCountry.getSelectedItem().toString();
//        if (!isFieldEmpty(scountry) && !scountry.equals("Country"))
//            error = "Please Select Country from Dropdown\n";
//        saddress = etAddrLine1.getText().toString();
//        if (!isFieldEmpty(saddress))
//            error = "Your Address Field Value is Invalid\n";
//        semail = etEmail.getText().toString();
//        if (!isEmailValid(semail))
//            error = "Your Email Field is Invalid\n";
//        return error;
//    }

    /**
     * This method check if a field has atleat two characters
     *
     * @param fieldEntry
     * @return
     */
    public boolean isFieldEmpty(String fieldEntry) {
//        String name = view.getTag().toString();
        Log.i("Check Empty", fieldEntry);
        return fieldEntry.matches("\\w{2,}");

    }

//    /**
//     * This method checks if the text returned by getText() is hint text or user typed text
//     *
//     * @return
//     */
//    public boolean isTextHintText(EditText editText, String hintText) {
//        String fieldText = editText.getText().toString();
//        return hintText.equals(fieldText);
//    }

    /**
     * Checks if the dob field is empty or equals to same as hint text DOB
     * If either the method returns false as the values suggest the date value
     * has not yet been selected.
     *
     * @return
     */
    public boolean isDOBSelected() {
        String dob = etDOB.getText().toString();
        return !(dob.equals("DOB") || dob.isEmpty());
    }

    public String getGender() {
        String gen = "";
        int genderChecked = rgGender.getCheckedRadioButtonId();
        switch (genderChecked) {
            case R.id.rMale:
                gen = "Male";
                break;
            case R.id.rFemale:
                gen = "Female";
        }
        return gen;
    }

    public boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public void setTvError(View v) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(new RectShape());
        shapeDrawable.getPaint().setColor(Color.RED);
        shapeDrawable.getPaint().setStrokeWidth(5f);
        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
        v.setBackground(shapeDrawable);
    }

    /**
     * This method resets all the fields to its original state except of
     * the field nationality.
     */

    public void resetForm() {
        etFName.setText("");
        etLName.setText("");
        etDOB.setText("");
        rgGender.clearCheck();
        etEmail.setText("");
        etAddrLine1.setText("");
        etAddrLine2.setText("");
        etAddrCity.setText("");
        etAddrPostcode.setText("");
        etAddrCountry.setText("");

    }


    enum ErrorMessage {
        FNAME("First Name Entry is Invalid"),
        LNAME("Last Name Entry is Invalid"),
        DOB("DOB is not Selected"),
        GENDER("Gender is not Checked"),
        NATIONALITY("Is your nationality OK?"),
        EMAIL("Email is not Valid"),
        ADDRLINE1("Address Line 1 is not Valid"),
        ADDRLINE2("Address Line 2 is not Valid"),
        ADDRCITY("Address City is not Valid"),
        ADDRPOSTCODE("Address Postcode is not Valid"),
        ADDRCOUNTRY("Address Country is not Valid");

        String eMessage = "";

        ErrorMessage(String errorMessage) {
            this.eMessage = errorMessage;
        }

        public String getMessage() {
            return this.eMessage;
        }
    }


    /*********************************
     * Validation of form Ends
     **************************************/


    public void onDOBClicked(View view) {
        Log.i("DOB Focus", "" + view.hasFocus());
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String dobFormat = "dd/MM/yy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(dobFormat, Locale.UK);
                etDOB.setText(dateFormat.format(calendar.getTime()));
            }
        };

        new DatePickerDialog(MainActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //Button OnClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttOK:
                List<ErrorMessage> errors = validateForm();
                tvError.setText("");
                if (errors.size() == 0) {
                    Log.i("ButtonClick", "the equal: " + errors);
                    sendAndOpenFormContent();
                } else {
                    String errorMsg = "";
                    for (int i = 0; i < errors.size(); i++) {
                        errorMsg += errors.get(i).eMessage + "\n";
                    }
                    tvError.setTextColor(getResources().getColor(R.color.errorText)); // FIXME: 28/12/15
                    tvError.setText(errorMsg);
                }
                break;
            case R.id.buttCancel:
                resetForm();
                break;
            case R.id.ivPhoto:

                break;
            default:
                break;
        }
    }

    /**
     * This method starts a new activity and pass its data to the new activity using a
     * parcelable.
     */

    public void sendAndOpenFormContent() {
        IForm form = new IForm(sfname, slname, sdob, sgender, sNationality, sAddressLine1, sAddressLine2,
                sAddressCity, sAddressPostcode, sAddressCountry, semail);
        Intent formConfirm = new Intent(MainActivity.this, FormConfirmParcelable.class);
        formConfirm.putExtra("form", form);
        startActivity(formConfirm);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
