package uk.ac.tae.myapp.immigrationform;

/**
 * Created by Karma on 14/12/15.
 */
public class ImmigrantRecord {
    Immigrant immigrant;
    ImmigrantContact contact;
    public ImmigrantRecord(Immigrant immigrant, ImmigrantContact contact){
        this.immigrant = immigrant;
        this.contact = contact;
    }

    public Immigrant getImmigrant() {
        return immigrant;
    }

    public ImmigrantContact getContact() {
        return contact;
    }

    public void setContact(ImmigrantContact contact) {
        this.contact = contact;
    }

    public void setImmigrant(Immigrant immigrant) {
        this.immigrant = immigrant;
    }
}
