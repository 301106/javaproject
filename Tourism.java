package hemanth;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class
abstract class Destination {
    private String name;
    private String mood;
    private int minBudget;
    private int maxBudget;
    private String weather;
    private int freeDays;

    public Destination(String name, String mood, int minBudget, int maxBudget, String weather, int freeDays) {
        this.name = name;
        this.mood = mood;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.weather = weather;
        this.freeDays = freeDays;
    }

    public String getName() { return name; }
    public String getMood() { return mood; }
    public int getMinBudget() { return minBudget; }
    public int getMaxBudget() { return maxBudget; }
    public String getWeather() { return weather; }
    public int getFreeDays() { return freeDays; }

    public abstract void displayInfo();
}

// Local Destination (INR ₹)
class LocalDestination extends Destination {
    public LocalDestination(String name, String mood, int minBudget, int maxBudget, String weather, int freeDays) {
        super(name, mood, minBudget, maxBudget, weather, freeDays);
    }

    @Override
    public void displayInfo() {
        System.out.println("🇮🇳 Local Destination: " + getName());
        System.out.println("Mood: " + getMood());
        System.out.println("Budget: ₹" + getMinBudget() + " - ₹" + getMaxBudget());
        System.out.println("Ideal Free Days: " + getFreeDays());
        System.out.println("Weather: " + getWeather());
        System.out.println("--------------------------");
    }
}

// International Destination (USD $)
class InternationalDestination extends Destination {
    public InternationalDestination(String name, String mood, int minBudget, int maxBudget, String weather, int freeDays) {
        super(name, mood, minBudget, maxBudget, weather, freeDays);
    }

    @Override
    public void displayInfo() {
        System.out.println("🌍 International Destination: " + getName());
        System.out.println("Mood: " + getMood());
        System.out.println("Budget: $" + getMinBudget() + " - $" + getMaxBudget());
        System.out.println("Ideal Free Days: " + getFreeDays());
        System.out.println("Weather: " + getWeather());
        System.out.println("--------------------------");
    }
}

// Recommendation Engine
class RecommendationEngine {
    private List<Destination> destinations = new ArrayList<>();

    public RecommendationEngine() {
        // Local Destinations
        destinations.add(new LocalDestination("Goa", "Party", 10000, 20000, "Sunny", 3));
        destinations.add(new LocalDestination("Manali", "Relaxing", 8000, 15000, "Snowy", 4));
        destinations.add(new LocalDestination("Kerala", "Scenic", 12000, 18000, "Rainy", 5));
        destinations.add(new LocalDestination("Jaipur", "Heritage", 7000, 13000, "Hot", 3));
        destinations.add(new LocalDestination("Darjeeling", "Calm", 9000, 14000, "Foggy", 4));
        destinations.add(new LocalDestination("Shimla", "Relaxing", 8000, 12000, "Cold", 4)); // New Local Destination
        destinations.add(new LocalDestination("Andaman", "Exotic", 15000, 25000, "Sunny", 6)); // New Local Destination
        destinations.add(new LocalDestination("Rishikesh", "Spiritual", 10000, 15000, "Pleasant", 4)); // New Local Destination
        destinations.add(new LocalDestination("Udaipur", "Heritage", 7000, 12000, "Hot", 3)); // New Local Destination
        destinations.add(new LocalDestination("Mysore", "Heritage", 8000, 12000, "Pleasant", 3)); // New Local Destination

        // International Destinations
        destinations.add(new InternationalDestination("Paris", "Romantic", 1200, 1800, "Cloudy", 5));
        destinations.add(new InternationalDestination("Dubai", "Luxury", 900, 1300, "Hot", 4));
        destinations.add(new InternationalDestination("Bali", "Exotic", 700, 1000, "Humid", 6));
        destinations.add(new InternationalDestination("Maldives", "Romantic", 1000, 1500, "Sunny", 6));
        destinations.add(new InternationalDestination("New York", "Energetic", 1300, 1700, "Windy", 5));
        destinations.add(new InternationalDestination("Tokyo", "Energetic", 1100, 1600, "Cool", 4)); // New International Destination
        destinations.add(new InternationalDestination("Sydney", "Luxury", 1000, 1500, "Sunny", 5)); // New International Destination
        destinations.add(new InternationalDestination("London", "Heritage", 1000, 1500, "Rainy", 6)); // New International Destination
        destinations.add(new InternationalDestination("Greece", "Exotic", 1500, 2000, "Sunny", 7)); // New International Destination
        destinations.add(new InternationalDestination("Singapore", "Modern", 1000, 1400, "Tropical", 5)); // New International Destination
    }

    public void recommend(String mood, int budget, int freeDays, boolean isInternational) {
        boolean found = false;

        for (Destination d : destinations) {
            boolean matchBudget = d instanceof LocalDestination && !isInternational
                    ? budget >= d.getMinBudget() && budget <= d.getMaxBudget()
                    : d instanceof InternationalDestination && isInternational
                    && budget >= d.getMinBudget() && budget <= d.getMaxBudget();

            if (d.getMood().equalsIgnoreCase(mood) && matchBudget && d.getFreeDays() == freeDays) {
                d.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ No matching destinations found based on your preferences.");
        }
    }
}

// Main
public class Tourism {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RecommendationEngine engine = new RecommendationEngine();

        System.out.println("✨ Welcome to the Travel Recommendation System ✨");

        System.out.print("Enter your mood (Relaxing, Party, Romantic, Exotic, Luxury, Calm, Scenic, Heritage, Energetic): ");
        String mood = sc.nextLine().trim();

        System.out.print("Do you want a local (INR ₹) or international (USD $) destination? (Type 'local' or 'international'): ");
        String type = sc.nextLine().trim().toLowerCase();
        boolean isInternational = type.equals("international");

        System.out.print("Enter your budget in " + (isInternational ? "USD ($)" : "INR (₹)") + ": ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        int budget = sc.nextInt();

        System.out.print("Enter number of free days: ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        int freeDays = sc.nextInt();

        System.out.println("\n🔎 Finding best matches for you...\n");
        engine.recommend(mood, budget, freeDays, isInternational);

        sc.close();
    }
}
