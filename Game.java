import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

class Game {
  public static int k = 0;

  public static Integer Computermove(Integer k) {
    SecureRandom s = new SecureRandom();
    int move = s.nextInt(k);
    return move;
  }

  public static void main(String[] args) throws NoSuchAlgorithmException {
    Scanner in = new Scanner(System.in);
    if (args.length % 2 != 0 && args.length != 0) {
      while (true) {
        String key = Secret.GenerateKey();
        Integer computermove = Computermove(args.length);
        k = 1;
        System.out.println("HMAC: " + CalcHmac.hmacSha(key, args[computermove]));
        for (String str : args) {
          System.out.println(k + " - " + str + ",");
          k++;
          if (k - 1 == args.length) {
            System.out.println("0" + " - exit,");
            System.out.println("? - help.");
          }
        }
        System.out.print("Enter your move: ");
        String move = in.nextLine();
        for (int i = 0; i < k; i++) {
          String ni = Integer.toString(i + 1);
          if (move.equals(ni)) {
            System.out.println("Your move: " + args[i]);
            System.out.println("Computer move: " + args[computermove]);
            int c = Winner.calculate(i, computermove, args.length);
            Winner.WhoWinner(c);
            System.out.println("HMAC key: " + key + '\n');
            break;
          } else if (move.equals("0")) {
            System.out.println("Bye, come again!.");
            System.exit(0);
          } else if (move.equals("?")) {
            Help.table(args.length, args);
            break;
          }
        }
      }
    } 
    else System.out.println("ERROR: Input odd number and not null!!!");
  }
}

