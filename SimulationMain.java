public class SimulationMain {
  public static void main(String[] args) {
    for (int i = 0; i < args.length; ++i) {
      if (args[i].equals("iter")) {
        // since an array is a reference if we used "==" it would be false as memory
        // locations are different using the equals method solves that

        if (i < args.length - 1) {
          i++;
          try {
            int iter = Integer.parseInt(args[i]);
            // set total simulation iterations
            System.out.print(iter);
          } catch (NumberFormatException e) {
            System.out.println("Invalid iter command, total number of iterations is missing");
          }
        }
      }
    }
  }
}
