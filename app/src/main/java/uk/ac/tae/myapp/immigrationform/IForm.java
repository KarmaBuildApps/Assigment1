package uk.ac.tae.myapp.immigrationform;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karma on 10/12/15.
 */
public class IForm implements Parcelable {
    String mIFName, mILName, mIDOB, mIGender, mINationality, mIAddressLine1, mIAddressLine2, mIAddressCity,
            mIAddressPostcode, mIAddressCountry, mIEmail;

    public IForm(String mIFName, String mILName, String mIDOB, String mIGender, String mINationality,
                 String mIAddressLine1, String mIAddressLine2, String mIAddressCity, String mIAddressPostcode,
                 String mIAddressCountry, String mIEmail) {
        this.mIFName = mIFName;
        this.mILName = mILName;
        this.mIDOB = mIDOB;
        this.mIGender = mIGender;
        this.mINationality = mINationality;
        this.mIAddressLine1 = mIAddressLine1;
        this.mIAddressLine2 = mIAddressLine2;
        this.mIAddressCity = mIAddressCity;
        this.mIAddressPostcode = mIAddressPostcode;
        this.mIAddressCountry = mIAddressCountry;
        this.mIEmail = mIEmail;
    }

    protected IForm(Parcel in) {
        mIFName = in.readString();
        mILName = in.readString();
        mIDOB = in.readString();
        mIGender = in.readString();
        mINationality = in.readString();
        mIAddressLine1 = in.readString();
        mIAddressLine2 = in.readString();
        mIAddressCity = in.readString();
        mIAddressPostcode = in.readString();
        mIAddressCountry = in.readString();
        mIEmail = in.readString();
    }

    public static final Creator<IForm> CREATOR = new Creator<IForm>() {
        @Override
        public IForm createFromParcel(Parcel in) {
            return new IForm(in);
        }

        @Override
        public IForm[] newArray(int size) {
            return new IForm[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mIFName);
        dest.writeString(mILName);
        dest.writeString(mIDOB);
        dest.writeString(mIGender);
        dest.writeString(mINationality);
        dest.writeString(mIAddressLine1);
        dest.writeString(mIAddressLine2);
        dest.writeString(mIAddressCity);
        dest.writeString(mIAddressPostcode);
        dest.writeString(mIAddressCountry);
        dest.writeString(mIEmail);
    }
}




