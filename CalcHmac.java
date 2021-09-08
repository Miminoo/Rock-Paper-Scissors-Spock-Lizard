import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class CalcHmac {
  public static String byteArrayToHex(byte[] a) {
    StringBuilder sb = new StringBuilder(a.length * 2);
    for (byte b : a)
      sb.append(String.format("%02x", b));
    return sb.toString();
  }

  public static String hmacSha(String KEY, String VALUE) {
    try {
      Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
      sha256_HMAC.init(new SecretKeySpec(KEY.getBytes(), "HmacSHA256"));
      byte[] result = sha256_HMAC.doFinal(VALUE.getBytes());
      return byteArrayToHex(result).toUpperCase();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
