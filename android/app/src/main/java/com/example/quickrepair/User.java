package com.example.quickrepair;

public class User {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String bankAccount;
    private String username;
    private String password;

    /**
     *  Sets the user's personal info performing the necessary checks
     *
     * @throws IllegalArgumentException if the input info is not correct
     *
     */
    public void setUserInfo(
            String name,
            String surname,
            String phoneNumber,
            String email,
            String bankAccount
    ){
        setName(name);
        setSurname(surname);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setBankAccount(bankAccount);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *  Sets the user's phone number if it matches the \\d{10} regex
     *  (it's exactly 10 digits)
     *
     * @throws IllegalArgumentException if the input is not
     * in the form \d{10}
     */
    private void setPhoneNumber(String phoneNumber) {
        //Checks if the phone number is exactly 10 digits long
        if (phoneNumber.matches("(\\d{10})") ) {

            this.phoneNumber = phoneNumber;
        }
        else{
            throw new IllegalArgumentException("phoneNumber");
        }
    }

    /**
     *  Sets the user's phone number if it matches the  regex
     *  is xxx@xxxxx.xxxx
     *
     *  @throws IllegalArgumentException if the input is not
     *       in the form (\\w@\\w\\.\\w)
     */
    private void setEmail(String email) {
        //Checks if the email is xxx@xxxxx.xxxx
        if (email.matches("(\\w+\\@\\w+\\.\\w+)") ) {

            this.email = email;
        }
        else{
            throw new IllegalArgumentException("email");
        }
    }

    /**
     *  Sets the user's bank account if it matches the  regex
     *  ????
     *
     *  @throws IllegalArgumentException if the input is not
     *      *       in the form ????
     */
    private void setBankAccount(String bankAccount) {
        //Checks if the bank account is of the form ???
        if (true ) {

            this.bankAccount = bankAccount;
        }
        else{
            throw new IllegalArgumentException("bankAccount");
        }
    }
}
