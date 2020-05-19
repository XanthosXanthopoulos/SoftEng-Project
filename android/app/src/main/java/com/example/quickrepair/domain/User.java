package com.example.quickrepair.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

public class User
{
    private int uid;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String bankAccount;
    private String username;
    private String password;

    public User()
    {
    }

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

        if (name.isEmpty()) throw new IllegalArgumentException("Name can not empty.");

        this.name = name;
    }

    private void setSurname(String surname)
    {
        if (surname == null) throw new NullPointerException("Surname can not be null.");

        if (surname.isEmpty()) throw new IllegalArgumentException("Surname can not empty.");

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
        if (surname == null) throw new NullPointerException("Phone number can not be null.");
        if (!phoneNumber.matches("\\d{10}"))
            throw new IllegalArgumentException("Wrong phone number format. Must have exactly ten digits.");

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
        if (email == null) throw new NullPointerException("Email can not be null.");

        if (!email.matches("((\\w|\\d|\\.)+\\@(\\w|\\d)+\\.\\w+)"))
            throw new IllegalArgumentException("Wrong email address format.");

        this.email = email;
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
        if (bankAccount == null) throw new NullPointerException("Bank number can not be null.");

        if (bankAccount.length() != 22) throw new IllegalArgumentException("Wrong bank account format. Must have length 22.");

        this.bankAccount = bankAccount;
    }

    public void setUsername(String username)
    {
        if (username == null) throw new NullPointerException("Username can not be null.");

        if (username.isEmpty()) throw new IllegalArgumentException("Username can not be empty.");

        this.username = username;
    }

    public void setPassword(String password)
    {
        if (password == null) throw new NullPointerException("Password can not be null.");

        if (password.isEmpty()) throw new IllegalArgumentException("Username can not be empty.");

        this.password = password;
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

    public String getPassword()
    {
        return password;
    }

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(email, user.email) &&
                Objects.equals(bankAccount, user.bankAccount) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, password);
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(name, surname, phoneNumber, email, bankAccount, username);
        result = 31 * result + Objects.hashCode(password);
        return result;
    }
}
