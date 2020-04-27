package com.example.quickrepair;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class User
{
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String bankAccount;
    private String username;
    private byte[] password;


    //TODO Store password as an md5 / sha1 / sha265 sum and provide method of validation
    public User(String name, String surname, String phoneNumber, String email, String bankAccount, String username, String password)
    {
        setUserInfo(name, surname, phoneNumber, email, bankAccount, username);
        setPassword(password);
    }

    /**
     * Sets the user's personal info performing the necessary checks
     *
     * @throws IllegalArgumentException if the input info is not correct
     */
    public void setUserInfo(String name, String surname, String phoneNumber, String email, String bankAccount, String username)
    {
        setName(name);
        setSurname(surname);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setBankAccount(bankAccount);
        setUsername(username);
    }

    private void setName(String name)
    {
        if (name == null) throw new NullPointerException("Name can not be null.");

        this.name = name;
    }

    private void setSurname(String surname)
    {
        if (surname == null) throw new NullPointerException("Surname can not be null.");

        this.surname = surname;
    }

    /**
     * Sets the user's phone number if it matches the \\d{10} regex
     * (it's exactly 10 digits)
     *
     * @throws IllegalArgumentException if the input is not
     *                                  in the form \d{10}
     */
    private void setPhoneNumber(String phoneNumber)
    {
        if (surname == null) throw new NullPointerException("Surname can not be null.");
        if (!phoneNumber.matches("\\d{10}"))
            throw new IllegalArgumentException("Wrong phone number format");

        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the user's phone number if it matches the  regex
     * is xxx@xxxxx.xxxx
     *
     * @throws IllegalArgumentException if the input is not
     *                                  in the form ((\w|\d)+\@(\w|\d)+\.\w+)
     */
    private void setEmail(String email)
    {
        //Checks if the email is xxx@xxxxx.xxxx
        if (email.matches("((\\w|\\d|\\.)+\\@(\\w|\\d)+\\.\\w+)"))
        {

            this.email = email;
        }
        else
        {
            throw new IllegalArgumentException("Wrong email address format");
        }
    }

    /**
     * Sets the user's bank account if it matches the  regex
     * ????
     *
     * @throws IllegalArgumentException if the input is not
     *                                  *       in the form ????
     */
    private void setBankAccount(String bankAccount)
    {
        //Checks if the bank account is of the form ???
        if (true)
        {

            this.bankAccount = bankAccount;
        }
        else
        {
            throw new IllegalArgumentException("Wrong bank account format");
        }
    }

    public void setUsername(String username)
    {
        if (username == null) throw new NullPointerException("Username can not be null.");

        this.username = username;
    }

    private void setPassword(String password)
    {
        if (password == null) throw new NullPointerException("Password can not be null.");
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            this.password = hash;
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Platform doesn't support sha256");
        }
    }
    public boolean validatePassword(String password){
        if (password == null) throw new NullPointerException("Password can not be null.");
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Arrays.equals(hash , this.password);
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Platform doesn't support sha256");
        }
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public String getBankAccount()
    {
        return bankAccount;
    }

    public String getUsername()
    {
        return username;
    }
}
