class Help {
  public static void table(Integer k, String[] args) {

    int width_and_height = k;
    String str2 = "+-----------";
    System.out.println(str2.repeat(k + 1));
    System.out.format("|Comp/User  | %10s", args[0]);
    for (int i = 1; i < width_and_height; i++) {
      System.out.format("| %10s", args[i]);
      if (i == width_and_height - 1) { // closing | for last column
        System.out.print("|");
      }
    }
    System.out.println();
    for (int i = 0; i < width_and_height; i++) {
      System.out.println(str2.repeat(k + 1));
      System.out.format("| %10s", args[i]);
      for (int j = 0; j < width_and_height; j++) {
        int w = Winner.calculate(i, j, k);
        if (w == 0) {
          System.out.format("| %10s", "DRAW");
        }
        if (w == 1) {
          System.out.format("| %10s", " WIN");
        }
        if (w == -1) {
          System.out.format("| %10s", "LOSE");
        }
        if (j == width_and_height - 1) { // closing | for last column
          System.out.print("|");
        }
      }
      System.out.println();
      if (i == width_and_height - 1) { // closing line for last row
        System.out.println(str2.repeat(k + 1));
      }
    }
  }
}