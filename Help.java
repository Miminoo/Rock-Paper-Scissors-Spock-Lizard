class Help {
  public static void table(Integer k, String[] args) {
    int maxlen = 0;
    String[] args2 = new String[k];
    String str = "- - - - - - - - -  ";
    for (int i = 0; i < k; i++) {
      if (args[i].length() > maxlen) {
        maxlen = args[i].length();
      }
    }
    for (int i = 0; i < k; i++) {
      args2[i] = args[i];
      if (args[i].length() < maxlen) {
        args2[i] += String.format("%" + (maxlen - args[i].length()) + "s", " ");
      }
    }
    for (int i = 0; i < k; i++) {
      System.out.format("%10s%7s%1s", "|", args[i], "|");
    }
    System.out.println("\n" + str.repeat(k));
    for (int i = 0; i < k; i++) {
      System.out.print("|" + args2[i]);
      for (int j = 0; j < k; j++) {
        int w = Winner.calculate(i, j, k);
        if (w == 0) {
          System.out.format("%9s%" + maxlen + "s", "|  DRAW  |", " ");
        }
        if (w == 1) {
          System.out.format("%9s%" + maxlen + "s", "|  WIN   |", " ");
        }
        if (w == -1) {
          System.out.format("%9s%" + maxlen + "s", "|  LOSE  |", " ");
        }
      }
      System.out.println("\n" + str.repeat(k));
    }
  }
}