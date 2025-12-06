import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SpinWheel {
    // Define rewards with their probabilities (weights)
    private static final String[] REWARDS = {
        "Free Coffee ☕",
        "Extra Credit 📚",
        "Amazon Gift Card 🎁",
        "Try Again 🔄",
        "Movie Ticket 🎬",
        "Pizza Voucher 🍕",
        "Bonus Points ⭐",
        "Try Again 🔄",
        "Ice Cream Cone 🍦",
        "Try Again 🔄"
    };
    
    private static final int MAX_SPINS = 10;
    private static ArrayList<String> wonRewards = new ArrayList<>();
    private static int spinsRemaining;
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        spinsRemaining = MAX_SPINS;
        
        displayWelcome();
        displayRewardsList();
        
        boolean continueSpinning = true;
        
        while (continueSpinning && spinsRemaining > 0) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("Spins remaining: " + spinsRemaining);
            System.out.print("Press ENTER to spin the wheel... ");
            scanner.nextLine();
            
            String reward = spinWheel();
            displayReward(reward);
            
            // Track rewards (exclude "Try Again")
            if (!reward.contains("Try Again")) {
                wonRewards.add(reward);
            }
            
            spinsRemaining--;
            
            if (spinsRemaining > 0) {
                continueSpinning = askToSpinAgain();
            } else {
                System.out.println("\n⚠️  You've used all your spins!");
            }
        }
        
        displaySummary();
        scanner.close();
    }
    
    private static void displayWelcome() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎡  WELCOME TO THE VIRTUAL SPIN WHEEL!  🎡");
        System.out.println("=".repeat(50));
        System.out.println("\n📋 INSTRUCTIONS:");
        System.out.println("   • Press ENTER to spin the wheel");
        System.out.println("   • You have " + MAX_SPINS + " spins to win amazing rewards!");
        System.out.println("   • Try your luck and see what you win!");
        System.out.println("   • Some rewards appear more often than others");
        System.out.println("\n" + "=".repeat(50));
    }
    
    private static void displayRewardsList() {
        System.out.println("\n🎁 AVAILABLE REWARDS:");
        
        // Display unique rewards only
        ArrayList<String> uniqueRewards = new ArrayList<>();
        for (String reward : REWARDS) {
            if (!uniqueRewards.contains(reward)) {
                uniqueRewards.add(reward);
            }
        }
        
        for (int i = 0; i < uniqueRewards.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + uniqueRewards.get(i));
        }
    }
    
    private static String spinWheel() {
        System.out.println("\n🎡 Spinning the wheel...");
        
        // Spinning animation
        try {
            String[] spinFrames = {"|", "/", "-", "\\", "|", "/", "-", "\\"};
            for (int i = 0; i < 16; i++) {
                System.out.print("\r   " + spinFrames[i % spinFrames.length] + " Spinning... ");
                Thread.sleep(100);
            }
            System.out.println("\r" + " ".repeat(20)); // Clear the line
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Generate random index to select reward
        int randomIndex = random.nextInt(REWARDS.length);
        return REWARDS[randomIndex];
    }
    
    private static void displayReward(String reward) {
        System.out.println("\n" + "★".repeat(50));
        
        if (reward.contains("Try Again")) {
            System.out.println("        😕 OH NO! " + reward.toUpperCase());
            System.out.println("        Better luck on your next spin!");
        } else {
            System.out.println("        🎉 CONGRATULATIONS! 🎉");
            System.out.println("        You won: " + reward.toUpperCase());
        }
        
        System.out.println("★".repeat(50));
    }
    
    private static boolean askToSpinAgain() {
        System.out.print("\n❓ Do you want to spin again? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        // Handle various positive responses
        return response.equals("yes") || response.equals("y") || 
               response.equals("yeah") || response.equals("sure");
    }
    
    private static void displaySummary() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🏁  GAME OVER - THANKS FOR PLAYING!  🏁");
        System.out.println("=".repeat(50));
        
        System.out.println("\n📊 YOUR RESULTS:");
        System.out.println("   • Total spins used: " + (MAX_SPINS - spinsRemaining));
        System.out.println("   • Rewards won: " + wonRewards.size());
        
        if (wonRewards.isEmpty()) {
            System.out.println("\n   😔 No rewards won this time. Better luck next time!");
        } else {
            System.out.println("\n🏆 YOUR REWARDS:");
            for (int i = 0; i < wonRewards.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + wonRewards.get(i));
            }
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("   Come back soon for more spins! 🎡");
        System.out.println("=".repeat(50) + "\n");
    }
}