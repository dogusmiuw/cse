import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class App {

    static int ac;
    static int pc = 0;
    static int ax;

    static int flag = 0;
    static String methodDecision;
    static int variableDecision;

    static String read;

    // <index of the element we try to reach , ac value>
    static HashMap<Integer, Integer> mapAc = new HashMap<>();

    // program.txt first element of the line (pc value)
    static ArrayList<Integer> splitted0 = new ArrayList<Integer>();
    // program.txt second element of the line (methodName)
    static ArrayList<String> splitted1 = new ArrayList<String>();
    // program.txt third element of the line (value to process)
    static ArrayList<String> splitted2 = new ArrayList<String>();

    static int counter = 0;

    public static void main(String[] args) {

        try {
            // String fileName =
            // "C:\\Users\\dous\\personal\\cse\\CSE206_Computer_Organization\\assignments\\cpu-emulator\\src\\code.txt";
            String fileName = "code.txt";
            File file = new File(fileName);

            @SuppressWarnings("resource")
            BufferedReader buff = new BufferedReader(new FileReader(file));
            String readLine = "";

            while ((readLine = buff.readLine()) != null) {
                read = readLine;
                String[] splittedArray = read.split(" ", 3);
                if (splittedArray[1].trim().equalsIgnoreCase("START")) {
                    splitted0.add(Integer.parseInt(read.split(" ")[0].trim()));
                    splitted1.add(read.split(" ")[1]);
                    splitted2.add("null");

                } else if ((splittedArray[1].trim().equalsIgnoreCase("HALT"))) {
                    splitted0.add(Integer.parseInt(read.split(" ")[0].trim()));
                    splitted1.add(read.split(" ")[1]);
                    splitted2.add("null");
                    break;

                } else if ((splittedArray[1].trim().equalsIgnoreCase("DISP"))) {
                    splitted0.add(Integer.parseInt(read.split(" ")[0].trim()));
                    splitted1.add(read.split(" ")[1]);
                    splitted2.add("null");

                } else if ((splittedArray[1].trim().equalsIgnoreCase("SWAP"))) {
                    splitted0.add(Integer.parseInt(read.split(" ")[0].trim()));
                    splitted1.add(read.split(" ")[1]);
                    splitted2.add("null");

                } else {
                    splitted0.add(Integer.parseInt(read.split(" ")[0].trim()));
                    splitted1.add(read.split(" ")[1]);
                    splitted2.add(read.split(" ")[2].trim());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        sendToDecision(pc);
        System.out.println("Program is executed " + " " + counter + " times");
    }

    /*
     * program flow
     */
    public static void sendToDecision(int pc) {// In order to provide "jump" and/or provide the flow of the program
        int i = pc;
        ++counter;

        if (splitted2.get(i).equalsIgnoreCase("null")) {
            // System.out.println("PC VALUE " + pc);//to try
            decision2(splitted1.get(i));
        } else {
            // System.out.println("PC VALUE " + pc);//to try
            decision1(splitted1.get(i), splitted2.get(i));
        }
    }

    public static void decision1(String string1, String string2) {// 3 values (Integer(pc value) - String(methodName) -
                                                                  // Integer(number to process))
        methodDecision = string1.toUpperCase().trim();
        variableDecision = Integer.parseInt(string2);
        switch (methodDecision) {

            case "LOAD":
                load(variableDecision);
                break;
            case "LOADM":
                loadm(variableDecision);
                break;
            case "STORE":
                store(variableDecision);
                break;
            case "CMPM":
                cmpm(variableDecision);
                break;
            case "CJMP":
                cjmp(variableDecision);
                break;
            case "JMP":
                jmp(variableDecision);
                break;
            case "ADD":
                add(variableDecision);
                break;
            case "ADDM":
                addm(variableDecision);
                break;
            case "SUBM":
                subm(variableDecision);
                break;
            case "SUB":
                sub(variableDecision);
                break;
            case "MUL":
                mul(variableDecision);
                break;
            case "MULM":
                mulm(variableDecision);
                break;
        }
    }

    public static void decision2(String string1) {// 2 values (Integer(pc value) - String(methodName))
        methodDecision = string1.toUpperCase().trim();
        switch (methodDecision) {
            case "START":
                start();
                break;
            case "HALT":
                halt();
                break;
            case "DISP":
                disp();
                break;
        }
    }

    /*
     * program processes
     */
    public static void start() {
        System.out.println("Program is successfully started (START is executed)");
        sendToDecision(++pc);
    }

    public static void load(int param) {
        ac = param;
        sendToDecision(++pc);
    }

    public static void loadm(int param) {
        ac = mapAc.get(param);
        sendToDecision(++pc);
    }

    public static void store(int param) {
        mapAc.put(param, ac);
        sendToDecision(++pc);
    }

    public static void cmpm(int param) {
        if (ac > mapAc.get(param)) {
            flag = 1;
        } else if (ac < mapAc.get(param)) {
            flag = -1;
        } else {
            flag = 0;
        }
        sendToDecision(++pc);
    }

    public static void cjmp(int param) {
        if (flag > 0) {
            pc = param;
            sendToDecision(pc);
        } else {
            sendToDecision(++pc);
        }
    }

    public static void jmp(int param) {
        pc = param;
        sendToDecision(pc);
    }

    public static void add(int param) {
        ac += param;
        sendToDecision(++pc);
    }

    public static void addm(int param) {
        ac += mapAc.get(param);
        sendToDecision(++pc);
    }

    public static void subm(int param) {
        ac -= mapAc.get(param);
        sendToDecision(++pc);
    }

    public static void sub(int param) {
        ac -= param;
        sendToDecision(++pc);
    }

    public static void mul(int param) {
        ac *= param;
        sendToDecision(++pc);
    }

    public static void mulm(int param) {
        ac *= mapAc.get(param);
        sendToDecision(++pc);
    }

    public static void disp() {
        System.out.println(ac);
        sendToDecision(++pc);
    }

    public static void halt() {
        System.out.println("program flow is stopped (HALT is executed)");
    }

}