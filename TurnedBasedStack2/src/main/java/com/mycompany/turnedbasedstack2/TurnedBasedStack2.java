package com.mycompany.turnedbasedstack2;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class TurnedBasedStack2 {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int playerHp = 100;
        int monsterHp = 20; 

        int playerMinDmg = 5;
        int playerMaxDmg = 10;

        int monsterMinDmg = 5;
        int monsterMaxDmg = 10;

        boolean monsterStunned = false;

        Stack<Integer> monsterHpStack = new Stack<>();
        Stack<Integer> desperateGambitStack = new Stack<>();
        boolean desperateGambitActive = false;
        boolean desperateGambitNerf = false;
        boolean desperateGambitUsed = false; 

        System.out.println("YOU ENCOUNTERED AN ENEMY!");

        while (true) {
            System.out.println("Player Hp: " + playerHp);
            System.out.println("Monster Hp: " + monsterHp);
            System.out.println();

            if (playerHp <= 0 && monsterHp <= 0) {
                System.out.println("It's a draw!");
                break;
            } else if (playerHp <= 0) {
                System.out.println("You lost! The monster wins!");
                break;
            } else if (monsterHp <= 0 && !desperateGambitActive && !desperateGambitNerf && !desperateGambitUsed) {
                System.out.println("Congratulations! You defeated the monster!");
                break;
            }

            // -------- Player's Turn --------
            System.out.println("PLAYER'S TURN!");
            System.out.println(" 1. Normal Attack");
            System.out.println(" 2. Thons 3 ");
            System.out.println(" 3. Stun Attack");
            System.out.println(" 4. Skip Turn");
            System.out.print("Choose (or type exit): ");

            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("You exited the game.");
                break;
            }

            if (input.equals("1")) {
                int playerDmg = playerMinDmg + random.nextInt(playerMaxDmg - playerMinDmg + 1);
                System.out.println("You dealt " + playerDmg + " damage to the monster.");
                monsterHpStack.push(monsterHp);
                monsterHp = monsterHp - playerDmg;
                if (monsterHp < 0) monsterHp = 0;
                System.out.println("Monster remaining hp: " + monsterHp);
                System.out.println();

                // Desperate Gambit check: only after player attack, only once
                if (monsterHp <= 0 && !desperateGambitActive && !desperateGambitNerf && !desperateGambitUsed) {
                    int chance = random.nextInt(1
                    ); 
                    if (chance == 0) {
                        System.out.println("Monster's passive activates: 'Desperate Gambit'!");
                        monsterHp = 50; 
                        System.out.println("The monster regains 50% HP: " + monsterHp);
                        System.out.println("The monster is empowered for 3 turns! ( +10% damage )");
                        desperateGambitActive = true;
                        desperateGambitStack.push(3); 
                        desperateGambitUsed = true;
                        // Change the monster's damage range for the buff
                        monsterMinDmg = 5;
                        monsterMaxDmg = 15;
                    } else {
                        System.out.println("Congratulations! You defeated the monster!");
                        break;
                    }
                }
                else if (input.equals("2")){
                    
                }
            } else if (input.equals("3")) {
                int stunChance = random.nextInt(4); 
                if (stunChance == 0) {
                    monsterStunned = true;
                    System.out.println("Stun successful! The monster is stunned and will skip its turn.");
                } else {
                    System.out.println("Stun failed! The monster is not stunned.");
                }
                System.out.println();
            } else if (input.equals("4")) {
                System.out.println("You skipped your turn!");
                System.out.println();
            } else {
                System.out.println("Invalid input! Please enter 1, 2, 3 or 'exit'.");
                System.out.println();
                continue;
            }

            // -------- Monster's Turn --------
            if (monsterHp > 0) {
                if (monsterStunned) {
                    System.out.println("Monster is stunned and cannot attack this turn.");
                    System.out.println();
                    monsterStunned = false;
                } else {
                    int monsterDmg = monsterMinDmg + random.nextInt(monsterMaxDmg - monsterMinDmg + 1);

                    if (desperateGambitActive && !desperateGambitStack.isEmpty()) {
                        monsterDmg = monsterDmg + (monsterDmg * 10 / 100);
                        int turnsLeft = desperateGambitStack.pop() - 1;
                        if (turnsLeft > 0) {
                            desperateGambitStack.push(turnsLeft);
                        } else {
                            desperateGambitActive = false;
                            desperateGambitNerf = true;
                            System.out.println("Desperate Gambit's power fades. Monster now deals half damage and its max damage returns to normal!");
                            monsterMinDmg = 5;
                            monsterMaxDmg = 10;
                        }
                    } else if (desperateGambitNerf) {
                        monsterDmg = monsterDmg / 2;
                    }

                    System.out.println("MONSTER'S TURN!");
                    System.out.println("Monster attacks and deals " + monsterDmg + " damage to you.");
                    playerHp = playerHp - monsterDmg;
                    if (playerHp < 0) playerHp = 0;
                    System.out.println("Your remaining hp: " + playerHp);
                    System.out.println();

                    // --- REGENERATION PASSIVE ---
                    int regenChance = random.nextInt(4); 
                    if (!monsterHpStack.isEmpty() && regenChance == 0) {
                        int prevHp = monsterHpStack.peek();
                        System.out.println("Monster activates passive: 'Regeneration'!");
                        System.out.println("Regeneration: Monster restores its HP to " + prevHp);
                        monsterHp = prevHp;
                        System.out.println();
                    }
                }
            }

            // ---- Win check after monster's turn ----
            if (monsterHp <= 0) {
                System.out.println("Congratulations! You defeated the monster!");
                break;
            }
            if (playerHp <= 0 && monsterHp <= 0) {
                System.out.println("It's a draw!");
                break;
            } else if (playerHp <= 0) {
                System.out.println("You lost! The monster wins!");
                break;
            }   
        }
    }
}