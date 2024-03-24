public class a120200808071 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Emulator emu = new Emulator(args[0]);

        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);

        System.out.println("Execution time: " + duration + "ms");
    }
}
