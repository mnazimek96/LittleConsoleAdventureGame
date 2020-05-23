package com.zimek;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        locations.put(0, new Location(0, "Exit"));
        locations.put(1, new Location(1, "Road"));
        locations.put(2, new Location(2, "Hill"));
        locations.put(3, new Location(3, "Building"));
        locations.put(4, new Location(4, "Valley"));
        locations.put(5, new Location(5, "Forest"));

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        int loc = 1;
        while (true){
            System.out.println(locations.get(loc).getDescription());
            if(loc == 0){
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.println("Available exits are");
            for (String exit :  exits.keySet()){
                System.out.print(exit + ", ");
            }
            System.out.println();
            String direction = "";
            while (true){
                direction = scanner.nextLine().toUpperCase();
                String[] dir = direction.split(" ");
                if (dir.length == 1){
                    if(dir[0].length() == 1){
                        direction = dir[0];
                        break;
                    } else {
                        if(dir[0].equals("NORTH") || dir[0].equals("WEST") || dir[0].equals("SOUTH") || dir[0].equals("EAST")){
                            direction = dir[0];
                            char[] direct = direction.toCharArray();
                            direction = Character.toString(direct[0]);
                            break;
                        } else {
                            System.out.println("Invalid input: ");
                            System.out.println("You are still on: " + locations.get(loc).getDescription());
                        }
                    }

                } else if (dir.length == 2){
                    if(dir[1].equals("NORTH") || dir[1].equals("WEST") || dir[1].equals("SOUTH") || dir[1].equals("EAST")){
                        direction = dir[1];
                        char[] direct = direction.toCharArray();
                        direction = Character.toString(direct[0]);
                        break;
                    } else {
                        System.out.println("Invalid input: ");
                        System.out.println("You are still on: " + locations.get(loc).getDescription());
                    }
                } else if (dir.length > 2 || dir.length < 0){
                    System.out.println("Invalid input: ");
                    System.out.println("You are still on: " + locations.get(loc).getDescription());
                }
            }

            if(exits.containsKey(direction)){
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}
