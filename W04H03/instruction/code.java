package instruction;

import java.util.Scanner;

class InstructionCode {
  
  public static void main(String[] args) {
    int n, f, t, b;
    n = readInt();
    f = 0;
    t = 2;
    b = -1;
    while (n >= t) {
      while (n % t == 0) {
        n = n / t;
        f = f + 1;
        b = t;
      }
      if (t % 2 != 0) {
        t = t + 1;
      }
      t = t + 1;
    }
    System.out.println(f);
    if (b > n) {
      System.out.println(b);
    } else {
      System.out.println(n);
    }
  }
  
  private static int readInt() {
    Scanner in = new Scanner(System.in);
    System.out.print("Geben Sie bitte eine Zahl ein: ");
    return in.nextInt();
  }
  
}