package Lab1;

import java.util.Scanner;
import java.util.stream.IntStream;

public class RomanConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n)
                .forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();
    }
}

// MINE: The most optimized solution for this problem.
class RomanConverter {
    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */
    public static String toRoman(int n) {
        // your solution here

        StringBuilder output = new StringBuilder();
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabic = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int i = 0;

        while (n > 0) {
            if ((n / arabic[i]) >= 1) {
                output.append(roman[i]);
                n -= arabic[i];
                i = 0; // Instead of looping through arabic array, reset the counter. That way we don't get wrong readings for numbers that have multiple thousands/hundreds/tens in them ex. 9896
            } else i++; // Increase the counter for the next number in arabic array that might be equal to 1 (ex. 1.6).
        }
        return output.toString();
    }

}

// MINE: Solution with multiple if statements, not optimal
class RomanConverterWithIfs {
    public static String toRoman(int n) {
        // your solution here
        StringBuilder ouput = new StringBuilder();
        while (n > 0) {
            if ((n / 1000) != 0) {
                ouput.append('M');
                n -= 1000;
            } else if ((n / 500) != 0) {
                if (n >= 900) {
                    ouput.append("CM");
                    n -= 900;
                } else {
                    ouput.append('D');
                    n -= 500;
                }
            } else if ((n / 100) != 0) {
                if (n >= 400) {
                    ouput.append("CD");
                    n -= 400;
                } else {
                    ouput.append('C');
                    n -= 100;
                }
            } else if ((n / 50) != 0) {
                if (n >= 90) {
                    ouput.append("XC");
                    n -= 90;
                } else {
                    ouput.append('L');
                    n -= 50;
                }
            } else if ((n / 10) != 0) {
                if (n >= 40) {
                    ouput.append("XL");
                    n -= 40;
                } else {
                    ouput.append('X');
                    n -= 10;
                }
            } else if ((n / 5) != 0) {
                if (n == 9) {
                    ouput.append("IX");
                    n -= 9;
                } else {
                    ouput.append('V');
                    n -= 5;
                }
            } else {
                if (n == 4) {
                    ouput.append("IV");
                    n -= 4;
                } else {
                    ouput.append('I');
                    n -= 1;
                }
            }

        }
        return ouput.toString();
    }

}

// NOT MINE: Better solution compared to mine with if statements, but worse that my optimized one
class RomanConverterNotSoOptimized {
    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */
    public static String toRoman(int n) {
        // your solution here

        StringBuilder sb = new StringBuilder();
        String[] romaan = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabic = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int tmp;
        for (int i = 0; i < arabic.length; i++) {
            if (n >= arabic[i]) {
                tmp = n / arabic[i];
                n -= tmp * arabic[i];
                while (tmp > 0) {
                    sb.append(romaan[i]);
                    tmp--;
                }
            }
        }
        return sb.toString();
    }
}

/*
Римски броеви
Да се напише метод кој ќе прима еден цел број и ќе ја печати неговата репрезентација како Римски број.

Пример

Aко ако се повика со парамететар 1998, излезот треба да биде MCMXCVIII.

Input:
5
6
18
554
101
1015

Output:
VI
XVIII
DLIV
CI
MXV

Input:
42
746 6763 594 890 9666 6741 8062 3368 9601 5351 2737 1232 993 3686 9741 2886 7219 4321 2955 6054 5956 6680 7384 4130 6770 1226 6904 1718 9707 3997 8674 2397 9193 1483 99 250 1834 6617 1610 5052 323 3276

Output:
DCCXLVI
MMMMMMDCCLXIII
DXCIV
DCCCXC
MMMMMMMMMDCLXVI
MMMMMMDCCXLI
MMMMMMMMLXII
MMMCCCLXVIII
MMMMMMMMMDCI
MMMMMCCCLI
MMDCCXXXVII
MCCXXXII
CMXCIII
MMMDCLXXXVI
MMMMMMMMMDCCXLI
MMDCCCLXXXVI
MMMMMMMCCXIX
MMMMCCCXXI
MMCMLV
MMMMMMLIV
MMMMMCMLVI
MMMMMMDCLXXX
MMMMMMMCCCLXXXIV
MMMMCXXX
MMMMMMDCCLXX
MCCXXVI
MMMMMMCMIV
MDCCXVIII
MMMMMMMMMDCCVII
MMMCMXCVII
MMMMMMMMDCLXXIV
MMCCCXCVII
MMMMMMMMMCXCIII
MCDLXXXIII
XCIX
CCL
MDCCCXXXIV
MMMMMMDCXVII
MDCX
MMMMMLII
CCCXXIII
MMMCCLXXVI

 */
