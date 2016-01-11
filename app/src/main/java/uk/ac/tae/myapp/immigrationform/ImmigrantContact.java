package uk.ac.tae.myapp.immigrationform;

/**
 * Created by Karma on 14/12/15.
 */
public class ImmigrantContact {
    int contact_id, immigrant_id;
    String email, addrLine1, addrLine2, addrCity, addrPostcode, addrCountry;

    public ImmigrantContact() {
        email = addrLine1 = addrLine2 = addrCity = addrPostcode = addrCountry = null; //set to null avoid creation of String objects
        contact_id = immigrant_id = -1; // -1 is equivalent to value not yet set
    }

    public ImmigrantContact(int contact_id, int immigrant_id, String email, String addrLine1,
                            String addrLine2, String addrCity, String addrPostcode, String addrCountry) {
        this.contact_id = contact_id;
        this.immigrant_id = immigrant_id;
        this.email = email;
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
        this.addrCity = addrCity;
        this.addrPostcode = addrPostcode;
        this.addrCountry = addrCountry;
    }

    public void addAddress(int contact_id, String addrLine1, String addrLine2, String addrCity,
                           String addrPostcode, String addrCountry) {
        this.contact_id = contact_id;
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
        this.addrCity = addrCity;
        this.addrPostcode = addrPostcode;
        this.addrCountry = addrCountry;
    }

    public int getContact_id() {
        return contact_id;
    }

    public String getAddrCountry() {
        return addrCountry;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public void setAddrCountry(String addrCountry) {
        this.addrCountry = addrCountry;
    }

    public int getImmigrant_id() {
        return immigrant_id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public String getAddrLine2() {
        return addrLine2;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public String getAddrPostcode() {
        return addrPostcode;
    }

    public void setImmigrant_id(int immigrant_id) {
        this.immigrant_id = immigrant_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddrLine1(String addrLine1) {
        this.addrLine1 = addrLine1;
    }

    public void setAddrLine2(String addrLine2) {
        this.addrLine2 = addrLine2;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public void setAddrPostcode(String addrPostcode) {
        this.addrPostcode = addrPostcode;
    }
}
