import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
class Secret {
  static String GenerateKey() throws NoSuchAlgorithmException{
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    SecureRandom random = new SecureRandom();
    keyGen.init(random);
    SecretKey secretKey = keyGen.generateKey();
    String s = new BigInteger(1, secretKey.getEncoded()).toString(16);
    return s.toUpperCase();
  }
}