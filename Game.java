import java.math.BigInteger;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
class Game {
  public static int k=0;
  public static class Secret{//класс для генерации случайного ключа 16 байт - 128 бит
  private static String GenerateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom random = new SecureRandom();keyGen.init(random);
        SecretKey secretKey = keyGen.generateKey();
        String s = new BigInteger(1, secretKey.getEncoded()).toString(16);
        return s.toUpperCase();}
  }
  public static class CalcHmac{// класс для вычисления HASH от хода
  private static String hmacSha(String KEY, String VALUE, String SHA_TYPE) {
    try {
      SecretKeySpec signingKey = new SecretKeySpec(KEY.getBytes("UTF-8"), SHA_TYPE);
      Mac mac = Mac.getInstance(SHA_TYPE);
      mac.init(signingKey);
      byte[] rawHmac = mac.doFinal(VALUE.getBytes("UTF-8"));
      byte[] hexArray = { (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6',
          (byte) '7', (byte) '8', (byte) '9', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f' };
      byte[] hexChars = new byte[rawHmac.length * 2];
      for (int j = 0; j < rawHmac.length; j++) {
        int v = rawHmac[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 5];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
      }
      return new String(hexChars).toUpperCase();
    } catch (Exception ex) {throw new RuntimeException(ex);}}}

  public static Integer Computermove(Integer k) {//метод генерирующий безопасный случайный ход компьютера
    SecureRandom s = new SecureRandom();
    int move = s.nextInt(k);
    return move;
  }
  public static class Winner{//Класс где определяется победитель
  public static Integer calculate(Integer a, Integer b, Integer n){
    int it = 0;
    int d = (n + a - b) % n;
    if (d==0){it=0;}
    else if (d%2==1){it = 1;}
    else if (d%2==0){it = -1;}
    return it;
  }
  public static void WhoWinner(Integer k){
    if (k == 0){System.out.println("Draw!");}
    else if (k == 1){System.out.println("You are winning!");}
    else {System.out.println("Computer is winning!");}
  }
}

  public static class Helper{
  public static void table(Integer k, String[] args){
    for (int i = 0; i < k; i++) {System.out.print("    "+args[i].charAt(0));}
    System.out.println();
   for (int i = 0; i < k; i++) {
    System.out.print(args[i].charAt(0));
   for (int j = 0; j < k; j++) {
     System.out.print("  "+Winner.calculate(i, j, k)+"  ");
  }
  System.out.println();
}}}
  public static void main(String[] args) throws NoSuchAlgorithmException{
    Scanner in = new Scanner(System.in);
    if (args.length%2!=0 && args.length!=0){
    while (true){
    String key = Secret.GenerateKey();
    Integer computermove = Computermove(args.length);
    k=0;
    System.out.println("HMAC: " + CalcHmac.hmacSha(key, args[computermove], "HmacSHA256"));
    for (String str: args){
      System.out.println(k + " - " + str +",");
      k++;
      if (k==args.length){
        System.out.println(args.length+"  - exit,");
        System.out.println("? - help.");
      }
    }
    System.out.print("Enter your move: ");
    String move = in.nextLine();
    for (int i=0; i < k; i++){
      String ni = Integer.toString(i);
      String nk = Integer.toString(k);
      if (move.equals(ni)){
        System.out.println("Your move: "+args[i]);
        System.out.println("Computer move: " + args[computermove]);
        int c = Winner.calculate(i, computermove, args.length);
        Winner.WhoWinner(c);
        System.out.println("HMAC key: " + key+'\n');
        break;
      } 
      else if (move.equals(nk)){System.out.println("Its end.");System.exit(0);}
      else if (move.equals("?")){Helper.table(args.length, args);break;}   
    }
  }
} else System.out.println("ERROR: Введи не чётное балван!!! и не пустое...");
}}
