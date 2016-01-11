package uk.ac.tae.myapp.immigrationform;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Karma on 10/12/15.
 */
public class FormConfirmParcelable extends Activity {
    TextView tvFFname, tvFLname, tvFDOB, tvFGender, tvFNationality, tvFEmail, tvFAddressLine1, tvFAddressLine2,
            tvFAddressCity, tvFAddressPostcode, tvFAddressCountry;
    IForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ifconfirm);
        tvFFname = (TextView) findViewById(R.id.tv_cFName);
        tvFLname = (TextView) findViewById(R.id.tv_cLName);
        tvFDOB = (TextView) findViewById(R.id.tv_cDOB);
        tvFGender = (TextView) findViewById(R.id.tv_cGender);
        tvFNationality = (TextView) findViewById(R.id.tv_cNationality);
        tvFEmail = (TextView) findViewById(R.id.tv_cEmail);
        tvFAddressLine1 = (TextView) findViewById(R.id.tv_cAddressLine1);
        tvFAddressLine2 = (TextView) findViewById(R.id.tv_cAddressLine2);
        tvFAddressCity = (TextView) findViewById(R.id.tv_cAddressCity);
        tvFAddressPostcode = (TextView) findViewById(R.id.tv_cAddressPostcode);
        tvFAddressCountry = (TextView) findViewById(R.id.tv_cAddressCountry);
        form = getIntent().getParcelableExtra("form");
        checkAndDisplayForm(form);
    }

    private void checkAndDisplayForm(IForm form) {
        if (form != null) {
            tvFFname.setText(form.mIFName);
            tvFLname.setText(form.mILName);
            tvFDOB.setText(form.mIDOB);
            tvFGender.setText(form.mIGender);
            tvFNationality.setText(form.mINationality);
            tvFEmail.setText(form.mIEmail);
            tvFAddressLine1.setText(form.mIAddressLine1);
            tvFAddressLine2.setText(form.mIAddressLine2);
            tvFAddressCity.setText(form.mIAddressCity);
            tvFAddressPostcode.setText(form.mIAddressPostcode);
            tvFAddressCountry.setText(form.mIAddressCountry);
        }
    }
}
