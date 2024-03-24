import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Dogukan Celik
 * @date 24 March 2024
 */
public class Emulator {
    File file;

    // COMPONENTS
    int ac = 0; // accumulator
    int pc = 0; // program counter
    HashMap<Integer, Integer> M = new HashMap<>(); // memory

    // FLAGS
    int f = 0;

    // PROGRAM FLOW
    HashMap<Integer, String> opcodes = new HashMap<>();
    ArrayList<Integer> operands = new ArrayList<>();

    Scanner sc;

    public Emulator(String fileName) {
        this.file = new File(fileName);

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            // ignore comments
            if (!line.startsWith("%") && !line.startsWith(";")) {
                String[] splittedArray = line.split(" ", 3);

                int lineNumber = Integer.valueOf(splittedArray[0].trim());
                String opcode = splittedArray[1].trim();
                int operand = -999;

                if (!opcode.equalsIgnoreCase("START") &&
                        !opcode.equalsIgnoreCase("DISP") &&
                        !opcode.equalsIgnoreCase("HALT"))
                    operand = Integer.valueOf(splittedArray[2]);

                opcodes.put(lineNumber, opcode);
                operands.add(operand);
            }
        }

        // System.out.println("M => " + opcodes.keySet().toString());
        // System.out.println("M => " + opcodes.values().toString());

        execute();
        // System.out.println(lineNumbers);
        // System.out.println(opcodes);
        // System.out.println(operands);
    }

    public void execute() {
        while (pc != -1) {
            // System.out.println("############################");

            String opcode = opcodes.get(pc);
            int operand = operands.get(pc);

            // System.out.println("PC: " + pc + " | " + opcode + " " + operand);

            switch (opcode) {
                case "START":
                    start();
                    break;
                case "LOAD":
                    load(operand);
                    break;
                case "LOADM":
                    loadm(operand);
                    break;
                case "STORE":
                    store(operand);
                    break;
                case "CMPM":
                    cmpm(operand);
                    break;
                case "CJMP":
                    cjmp(operand);
                    break;
                case "JMP":
                    jmp(operand);
                    break;
                case "ADD":
                    add(operand);
                    break;
                case "SUB":
                    sub(operand);
                    break;
                case "ADDM":
                    addm(operand);
                    break;
                case "SUBM":
                    subm(operand);
                    break;
                case "MUL":
                    mul(operand);
                    break;
                case "MULM":
                    mulm(operand);
                    break;
                case "DISP":
                    disp();
                    break;
                case "HALT":
                    halt();
                    break;
            }
        }
    }

    public void start() {
        System.out.println("Program started.");
        pc += 1;
    }

    public void load(int x) {
        ac = x;
        pc += 1;
    }

    public void loadm(int x) {
        ac = M.get(x);
        pc += 1;
    }

    public void store(int x) {
        M.put(x, ac);
        pc += 1;
    }

    public void cmpm(int x) {
        if (ac > M.get(x))
            f = 1;
        else if (ac < M.get(x))
            f = -1;
        else
            f = 0;

        pc += 1;
    }

    public void cjmp(int x) {
        if (f > 0)
            pc = x;
        else
            pc += 1;
    }

    public void jmp(int x) {
        pc = x;
    }

    public void add(int x) {
        ac += x;
        pc += 1;
    }

    public void sub(int x) {
        ac -= x;
        pc += 1;
    }

    public void addm(int x) {
        ac += M.get(x);
        pc += 1;
    }

    public void subm(int x) {
        ac -= M.get(x);
        pc += 1;
    }

    public void mul(int x) {
        ac *= x;
        pc += 1;
    }

    public void mulm(int x) {
        ac *= M.get(x);
        pc += 1;
    }

    public void disp() {
        System.out.println("AC value = " + ac);
        pc += 1;
    }

    public void halt() {
        System.out.println("Program halted.");
        pc = -1;
    }

}
