class Winner {
  public static Integer calculate(Integer a, Integer b, Integer n) {
    int it = 0;
    int d = (n + a - b) % n;
    if (d == 0) {it = 0;}
    else if (d % 2 == 1) {it = 1;} 
    else if (d % 2 == 0) {it = -1;}
    return it;
  }

  public static void WhoWinner(Integer k) {
    if (k == 0) {
      System.out.println("Draw!");
    } else if (k == 1) {
      System.out.println("You win!");
    } else {
      System.out.println("Computer win!");
    }
  }
}