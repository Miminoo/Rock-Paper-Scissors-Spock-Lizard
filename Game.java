import java.math.BigInteger;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
class Game {
  public static String[] menu = { "rock", "paper", "scissors", "lizard", "spock"}; 
  public static class Secret{//класс для генерации случайного ключа 16 байт - 128 бит
  private static String GenerateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom random = new SecureRandom();
        keyGen.init(random);
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
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
  public static String Computermove() {//метод генерирующий безопасный случайный ход компьютера
    SecureRandom s = new SecureRandom();
    int move = s.nextInt(5);
    return menu[move];
  }
  public static class Winner{//Класс где определяется победитель
  public static Integer calculate(Integer a, Integer b){
    int d = (5 + a - b) % 5; int k=0;
    if (d == 1 || d == 3){k = 1;}
    else if (d==2 || d==4){k = -1;}
    else if (d==0){k = 0;}
    return k;
  }
  public static void WhoWinner(Integer k){
    if (k == 0){System.out.println("Draw!");}
    else if (k == 1){System.out.println("You are winning!");}
    else {System.out.println("Computer is winning!");}
  }
}
  public static Integer IndexMass(String str){//Метод с помощью которого узнаетя index выбранного компом слова
    int index = 0;
    for (int i=0;i<menu.length;i++){if (menu[i]==str){index = i;break;}}return index;
  }

  public static class Helper{
  public static void table(){
    String leftAlignFormat = "| %-15s | %-4d | %-6d | %-9d | %-8d | %-5d |%n";
    String s;
    System.out.format("+-----------------+------+--------+-----------+----------+-------+%n");
    System.out.format("| Comp / USER     | Rock | Paper  | Scissors  | Lizard  | Spock  |%n");
    System.out.format("+-----------------+------+--------+-----------+----------+-------+%n");
    for (int i = 0; i < 5; i++) {
        s = menu[i];
        System.out.format(leftAlignFormat, menu[i].toUpperCase(),Winner.calculate(0,IndexMass(s)) , Winner.calculate(1,IndexMass(s)), Winner.calculate(2,IndexMass(s)), Winner.calculate(3,IndexMass(s)), Winner.calculate(4,IndexMass(s)));
        s = "";
    }
    System.out.format("+-----------------+------+--------+-----------+----------+-------+%n");
  }
}
  public static void main(String[] args) throws NoSuchAlgorithmException{
    Scanner in = new Scanner(System.in);
    Secret secret = new Secret();
    CalcHmac calchmac = new CalcHmac();
    if (menu[0].equals(args[0]) && menu[1].equals(args[1]) && menu[2].equals(args[2]) && menu[3].equals(args[3]) && menu[4].equals(args[4])) {
      while (true) {
        String key = secret.GenerateKey();
        String computermove = Computermove();
        System.out.println("HMAC: " + calchmac.hmacSha(key, computermove, "HmacSHA256"));
        System.out.println(" 1 - rock, \n 2 - paper, \n 3 - scissors \n 4 - lizard \n 5 - spock \n 6 - help \n 0 - exit");
        System.out.print("Enter your move: ");
        int move = in.nextInt();
        switch (move) {
          case 1: {
            System.out.println("Your move: " + menu[0]);
            System.out.println("Computer move: " + computermove);
            int c = Winner.calculate(0,IndexMass(computermove));
            Winner.WhoWinner(c);
            System.out.println("HMAC key: " + key+'\n');
          };break;
          case 2: {
            System.out.println("Your move: " + menu[1]);
            System.out.println("Computer move: " + computermove);
            Winner.calculate(1,IndexMass(computermove));
            int c = Winner.calculate(0,IndexMass(computermove));
            Winner.WhoWinner(c);
            System.out.println("HMAC key: " + key+'\n');
          };break;
          case 3: {           
            System.out.println("Your move: " + menu[2]);
            System.out.println("Computer move: " + computermove);
            Winner.calculate(2,IndexMass(computermove));
            int c = Winner.calculate(0,IndexMass(computermove));
            Winner.WhoWinner(c);
            System.out.println("HMAC key: " + key+'\n');
          };break;
          case 4: {
            System.out.println("Your move: " + menu[3]);
            System.out.println("Computer move: " + computermove);
            Winner.calculate(3,IndexMass(computermove));
            int c = Winner.calculate(0,IndexMass(computermove));
            Winner.WhoWinner(c);
            System.out.println("HMAC key: " + key+'\n');
          }break;
          case 5:{
            System.out.println("Your move: " + menu[4]);
            System.out.println("Computer move: " + computermove);
            Winner.calculate(4,IndexMass(computermove));
            int c = Winner.calculate(0,IndexMass(computermove));
            Winner.WhoWinner(c);
            System.out.println("HMAC key: " + key+'\n');
          }break;
          case 6: {Helper.table();}break;
          case 0: {System.exit(0);}break;
          default: System.out.println("Введите от 0 до 6, и не как иначе!!!"); break;}}} 
    else {System.out.println("Введенные данные не верны!!!");System.exit(0);}}}
