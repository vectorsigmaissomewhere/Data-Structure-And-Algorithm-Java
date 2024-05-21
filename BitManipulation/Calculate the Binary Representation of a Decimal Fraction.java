// Description: Given a decimal fraction, return its binary representation.

public class DecimalToBinary {
    public static String decimalToBinary(double num) {
        if (num >= 1 || num <= 0) {
            return "ERROR";
        }

        StringBuilder binary = new StringBuilder();
        binary.append("0.");

        while (num > 0) {
            if (binary.length() >= 32) {
                return "ERROR";
            }

            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }

        return binary.toString();
    }

    public static void main(String[] args) {
        double num = 0.625;
        System.out.println("Binary representation of " + num + " is: " + decimalToBinary(num)); // Output: 0.101
    }
}

// Decimal to Binary: Converts a decimal fraction to binary by repeatedly multiplying by 2.
