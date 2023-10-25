package org.dinosat.gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Leaderboard {

    public static boolean exitGameAndShowLeaderboard(Map<String, Integer> leaderboard) {
        printLeaderboard(leaderboard);
        return false;
    }

    private static void printLeaderboard(Map<String, Integer> leaderboard) {
        List<Map.Entry<String, Integer>> sortedLeaderboard = new ArrayList<>(leaderboard.entrySet());
        sortedLeaderboard.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        System.out.println("\n" + " .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" + "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" + "| |   _____      | || |  _________   | || |      __      | || |  ________    | || |  _________   | || |  _______     | || |   ______     | || |     ____     | || |      __      | || |  _______     | || |  ________    | || |              | |\n" + "| |  |_   _|     | || | |_   ___  |  | || |     /  \\     | || | |_   ___ `.  | || | |_   ___  |  | || | |_   __ \\    | || |  |_   _ \\    | || |   .'    `.   | || |     /  \\     | || | |_   __ \\    | || | |_   ___ `.  | || |      _       | |\n" + "| |    | |       | || |   | |_  \\_|  | || |    / /\\ \\    | || |   | |   `. \\ | || |   | |_  \\_|  | || |   | |__) |   | || |    | |_) |   | || |  /  .--.  \\  | || |    / /\\ \\    | || |   | |__) |   | || |   | |   `. \\ | || |     (_)      | |\n" + "| |    | |   _   | || |   |  _|  _   | || |   / ____ \\   | || |   | |    | | | || |   |  _|  _   | || |   |  __ /    | || |    |  __'.   | || |  | |    | |  | || |   / ____ \\   | || |   |  __ /    | || |   | |    | | | || |      _       | |\n" + "| |   _| |__/ |  | || |  _| |___/ |  | || | _/ /    \\ \\_ | || |  _| |___.' / | || |  _| |___/ |  | || |  _| |  \\ \\_  | || |   _| |__) |  | || |  \\  `--'  /  | || | _/ /    \\ \\_ | || |  _| |  \\ \\_  | || |  _| |___.' / | || |     (_)      | |\n" + "| |  |________|  | || | |_________|  | || ||____|  |____|| || | |________.'  | || | |_________|  | || | |____| |___| | || |  |_______/   | || |   `.____.'   | || ||____|  |____|| || | |____| |___| | || | |________.'  | || |              | |\n" + "| |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |\n" + "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" + " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n");
        for (int i = 0; i < sortedLeaderboard.size(); i++) {
            Map.Entry<String, Integer> entry = sortedLeaderboard.get(i);
            String playerName = entry.getKey();
            int score = entry.getValue();

            String rank = String.format("%2d. ", i + 1);
            String scoreText = String.format("%4d", score);

            String leaderboardEntry = String.format("%-5s %-6s: %10s", rank, playerName, scoreText);
            System.out.println(leaderboardEntry);
        }
    }
}