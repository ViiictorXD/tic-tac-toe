package com.deslocher;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    private static final List<Integer[]> POSSIBLE_WIN_SEQUENCE = Arrays.asList(
      new Integer[] { 0, 1, 2 },
      new Integer[] { 0, 4, 8 },
      new Integer[] { 0, 3, 6 },
      new Integer[] { 3, 4, 5 },
      new Integer[] { 6, 7, 8 },
      new Integer[] { 2, 4, 6 },
      new Integer[] { 1, 4, 7 },
      new Integer[] { 2, 5, 8 }
    );

    public static void main(String[] args) {
        String[] table = new String[9];

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do primeiro jogador:");
        String xPlayerName = scanner.next();

        System.out.println("Digite o nome do segundo jogador:");
        String oPlayerName = scanner.next();

        String lastPlayerLetter = "X";

        showTable(table);

        while(true) {
            System.out.println("Digite o número da casa em que você deseja jogar.");
            int positionIndex = scanner.nextInt();

            if (positionIndex < 0 || positionIndex > 8) {
                System.out.println("O número da casa deve estar entre 0-8.");
                continue;
            }

            String letterFound = table[positionIndex];
            if (letterFound != null) {
                System.out.println("Essa casa já está ocupada pelo jogador: " + (letterFound.equals("X") ? xPlayerName : oPlayerName));
                continue;
            }

            table[positionIndex] = lastPlayerLetter;

            showTable(table);

            if (hasWinner(table)) {
                System.out.println("O vencedor foi: " + (lastPlayerLetter.equals("X") ? xPlayerName : oPlayerName));
                break;
            }

            if (canFinishGame(table)) {
                System.out.println("O jogo foi encerrado pois não houve um vencedor.");
                break;
            }

            lastPlayerLetter = lastPlayerLetter.equals("X") ? "O" : "X";
        }
    }

    public static void showTable(String[] table) {
        System.out.print("-".repeat(5));
        for (int index = 0; index < table.length; index++) {
            if (index % 3 == 0) System.out.println();

            String letterOrNull = table[index];

            if (letterOrNull == null) System.out.print("- ");
            else System.out.print(letterOrNull + " ");
        }
        System.out.println();
        System.out.println("-".repeat(5));
    }

    public static boolean canFinishGame(String[] table) {
        for (String line : table) {
            if (line == null) return false;
        }
        return true;
    }

    public static boolean hasWinner(String[] table) {
        for (Integer[] possibleWin : POSSIBLE_WIN_SEQUENCE) {
            String first = table[possibleWin[0]];
            String second = table[possibleWin[1]];
            String third = table[possibleWin[2]];

            if (first != null
              && second != null
              && third != null
              && first.equalsIgnoreCase(second)
              && first.equalsIgnoreCase(third)) {
                return true;
            }
        }
        return false;
    }
}
