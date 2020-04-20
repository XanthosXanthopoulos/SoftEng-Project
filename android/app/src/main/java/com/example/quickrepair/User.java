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
    //TODO  null pointer checks that throw nullpointer exceptions and not illegalargument

    private void setName(String name) {
        if(name == null) throw new NullPointerException("Name can not be null.");

        this.name = name;
    }

    private void setSurname(String surname)
    {
        if(surname == null) throw new NullPointerException("Surname can not be null.");

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
        if (!phoneNumber.matches("\\d{10}")) throw new IllegalArgumentException("Phone number must have ten digits.");

        this.phoneNumber = phoneNumber;
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
        if (email.matches("(\\w+\\@\\w+\\.\\w+)")) {

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

    //TODO set username password
}
