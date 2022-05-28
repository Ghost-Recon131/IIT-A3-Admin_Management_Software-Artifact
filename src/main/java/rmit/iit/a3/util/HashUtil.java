package rmit.iit.a3.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class HashUtil {

    public String getHash(String toHash) {
        String HashedResult = null;
        try {
            MessageDigest MD = MessageDigest.getInstance("SHA-256");
            MD.update(toHash.getBytes());

            byte[] digest = MD.digest();
            StringBuffer StringBuffer = new StringBuffer();
            for (byte b : digest) {
                StringBuffer.append(String.format("%02x", b & 0xff));
            }
            HashedResult = StringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Bug in hashing method");
        }
        return HashedResult;
    }

}
