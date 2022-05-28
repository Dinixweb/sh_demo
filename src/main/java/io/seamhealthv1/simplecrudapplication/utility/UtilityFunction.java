package io.seamhealthv1.simplecrudapplication.utility;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UtilityFunction {

    public String generatedId(){
        String SALTCHARS = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() <8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
