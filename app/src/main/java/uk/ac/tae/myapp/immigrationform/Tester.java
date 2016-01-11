package uk.ac.tae.myapp.immigrationform;

/**
 * Created by Karma on 10/12/15.
 */
public class Tester {
    public static void main(String[] args){
        Tester test = new Tester();
        String email = ".com";
        System.out.println("The email is "+test.isEmailValid(email));
    }

    public boolean isEmailValid(String email){
        boolean isValid = true;
        isValid = email.matches("^[A-Za-z0-9]@*.*[A-Za-z0-9]$");
        return isValid;
    }
}
