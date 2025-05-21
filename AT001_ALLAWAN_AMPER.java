/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.at001_allawan_amper;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;


/**
 *
 * @author Students Account
 */
public class AT001_ALLAWAN_AMPER {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // --- MONSTER NAME POOL SETUP ---
        LinkedList<String> firstNames = new LinkedList<>();
        LinkedList<String> lastNames = new LinkedList<>();
        // -- FIRST NAME -- 
        firstNames.add("Neill");
        firstNames.add("Joshua");
        firstNames.add("Mitch");
        firstNames.add("Reyian");
        firstNames.add("Nico");
        firstNames.add("Adriane");
        firstNames.add("Qiann");
        firstNames.add("Tans");
        firstNames.add("Super Human");
        firstNames.add("Anton");

        // -- LAST NAME --
        lastNames.add("From Tiggato");
        lastNames.add("of Maa");
        lastNames.add("The Balut");
        lastNames.add("of Maa");
        lastNames.add("Bad genius");
        lastNames.add("Eucare");
        lastNames.add("Pokemon");
        lastNames.add("Eucare");
        lastNames.add("Amper");
        lastNames.add("of Matina Aplaya");
        lastNames.add("Lerasan");
        lastNames.add("Canja");
        lastNames.add("Vergara");
        lastNames.add(" The Hero");
        
        boolean exitGame = false;
        
        // --- ENCOUNTER LOOP ---
        while (!exitGame && !firstNames.isEmpty() && !lastNames.isEmpty()) {
            // --- ENCOUNTER ---
            encounter(firstNames, lastNames, random, scanner);
            
            // --- AFTER BATTLE CHECK ---
            if (!exitGame && (!firstNames.isEmpty() && !lastNames.isEmpty())) {
                while (true) {
                    System.out.print("Do you want to continue to fight another monster? (yes/exit): ");
                    String nextAction = scanner.next();
                    if (nextAction.equalsIgnoreCase("exit")) {
                        System.out.println("You exited the game.");
                        exitGame = true;
                        break;
                    } else if (nextAction.equalsIgnoreCase("yes")) {
                        break; 
                    } else {
                        System.out.println("Invalid input. Please type 'yes' to continue or 'exit' to quit.");
                    }
                }
            } else if (firstNames.isEmpty() || lastNames.isEmpty()) {
                System.out.println("No more unique monster names left. You have defeated all possible monsters!");
                exitGame = true;
            }
        }

        System.out.println("Game Over.");
    }
    
    // --- ENCOUNTER METHOD ---
    // Handles a single player vs. monster encounter, including player and monster turns, skills, and win/loss checks.
    public static void encounter(
        LinkedList<String> firstNames,
        LinkedList<String> lastNames,
        Random random,
        Scanner scanner
    ) {
        // --- MONSTER NAME GENERATION ---
        int firstIndex = random.nextInt(firstNames.size());
        int lastIndex = random.nextInt(lastNames.size());
        String monsterFirst = firstNames.remove(firstIndex);
        String monsterLast = lastNames.remove(lastIndex);
        String monsterName = monsterFirst + " " + monsterLast;

        // --- INITIAL STATS ---
        int playerHp = 100;
        int playerMaxHp = 100;
        int monsterHp = 100; 

        int playerMinDmg = 5;
        int playerMaxDmg = 10;

        int monsterMinDmg = 5;
        int monsterMaxDmg = 10;

        // --- MONSTER STATUS & BUFFS ---
        boolean monsterStunned = false;
        boolean desperateGambitActive = false;
        boolean desperateGambitNerf = false;
        boolean desperateGambitUsed = false;

        // --- PLAYER BUFFS & PASSIVES ---
        Stack<Integer> monsterHpStack = new Stack<>();
        Stack<Integer> desperateGambitStack = new Stack<>();
        Stack<Integer> jinguBuffStack = new Stack<>();
        int jinguHitCounter = 0;

        // --- PLAYER SKILL STATE ARRAYS ---
        int[] doomReflection = new int[2]; 
        doomReflection[0] = 0;
        doomReflection[1] = 0;

        int[] stunCooldown = new int[2]; 
        stunCooldown[0] = 0;
        stunCooldown[1] = 0;

        System.out.println("YOU ENCOUNTERED AN ENEMY: " + monsterName + "!");

        // --- ENCOUNTER MAIN LOOP ---
        while (true) {
            System.out.println("Player Hp: " + playerHp);
            System.out.println(monsterName + " Hp: " + monsterHp);
            System.out.println();

            // --- WIN/LOSS/DRAW CHECKS ---
            if (playerHp <= 0 && monsterHp <= 0) {
                System.out.println("It's a draw!");
                break;
            } else if (playerHp <= 0) {
                System.out.println("You lost! " + monsterName + " wins!");
                break;
            } else if (monsterHp <= 0 && !desperateGambitActive && !desperateGambitNerf && !desperateGambitUsed) {
                System.out.println("Congratulations! You defeated " + monsterName + "!");
                break;
            }

            // --- PLAYER TURN ---
            System.out.println("PLAYER'S TURN!");
            System.out.println("1. Normal Attack");
            String stunStatus = (stunCooldown[0] == 1 ? "Cooldown: " + stunCooldown[1] : "Ready");
            System.out.println("2. Stun Attack (" + stunStatus + ")");
            System.out.println("3. Skip Turn");
            String doomStatus = (doomReflection[0] == 2) ? ("Cooldown: "+doomReflection[1]) 
                                : (doomReflection[0] == 1 ? ("Active: "+doomReflection[1]+" turn(s) left") 
                                : "Ready");
            System.out.println("4. Activate Doom Reflection (" + doomStatus + ")");
            System.out.print("Choose (or type exit): ");

            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("You exited the game.");
                break;
            }

            // --- PLAYER ACTIONS ---
            if (input.equals("1")) {
                // Normal Attack and Jingu Mastery 
                jinguHitCounter++;
                int playerDmg = playerMinDmg + random.nextInt(playerMaxDmg - playerMinDmg + 1);

                if (jinguBuffStack.isEmpty() && jinguHitCounter == 4) {
                    System.out.println("Passive Jingu Mastery is activated!");
                    System.out.println("Jingu Mastery activated! Next 4 normal attacks gain 160% bonus damage and 80% lifesteal!");
                    for (int i = 0; i < 4; i++) {
                        jinguBuffStack.push(1);
                    }
                    jinguHitCounter = 0;
                }

                if (!jinguBuffStack.isEmpty()) {
                    int bonusDmg = (int)Math.round(playerDmg * 1.6);
                    playerDmg += bonusDmg;
                    int lifesteal = (int)Math.round(playerDmg * 0.80);
                    // --- LIFESTEAL CANNOT EXCEED MAX HP ---
                    if (playerHp + lifesteal > playerMaxHp) {
                        lifesteal = playerMaxHp - playerHp;
                        if (lifesteal < 0) lifesteal = 0;
                        playerHp = playerMaxHp;
                    } else {
                        playerHp += lifesteal;
                    }
                    System.out.println("Jingu Mastery buff: +160% bonus (" + bonusDmg + ") damage and +" + lifesteal + " HP (lifesteal)!");
                    jinguBuffStack.pop();
                }

                System.out.println("You dealt " + playerDmg + " damage to " + monsterName + ".");
                monsterHpStack.push(monsterHp);
                monsterHp -= playerDmg;
                if (monsterHp < 0) monsterHp = 0;
                System.out.println(monsterName + " remaining hp: " + monsterHp);
                System.out.println();

                // Desperate Gambit passive
                if (monsterHp <= 0 && !desperateGambitActive && !desperateGambitNerf && !desperateGambitUsed) {
                    int chance = random.nextInt(1); 
                    if (chance == 0) {
                        System.out.println(monsterName + "'s passive activates: 'Desperate Gambit'!");
                        monsterHp = 50; 
                        System.out.println(monsterName + " regains 50% HP: " + monsterHp);
                        System.out.println(monsterName + " is empowered for 3 turns! ( +10% damage )");
                        desperateGambitActive = true;
                        desperateGambitStack.push(3); 
                        desperateGambitUsed = true;
                        monsterMinDmg = 5;
                        monsterMaxDmg = 15;
                    } else {
                        System.out.println("Congratulations! You defeated " + monsterName + "!");
                        break;
                    }
                }
            } else if (input.equals("2")) {
                // Stun Attack and cooldown 
                if (stunCooldown[0] == 1) {
                    System.out.println("Stun Attack is on cooldown! (" + stunCooldown[1] + " turn(s) left)");
                    System.out.println();
                } else {
                    jinguHitCounter = 0; 
                    int stunChance = random.nextInt(4); 
                    if (stunChance == 0) {
                        monsterStunned = true;
                        System.out.println("Stun successful! " + monsterName + " is stunned and will skip its turn.");
                    } else {
                        System.out.println("Stun failed! " + monsterName + " is not stunned.");
                    }
                    stunCooldown[0] = 1;
                    stunCooldown[1] = 4;
                    System.out.println();
                }
            } else if (input.equals("3")) {
                // Skip Turn
                jinguHitCounter = 0;
                System.out.println("You skipped your turn!");
                System.out.println();
            } else if (input.equals("4")) {
                // Doom Reflection skill
                if (doomReflection[0] == 1) {
                    System.out.println("Doom Reflection is already active! (" + doomReflection[1] + " turn(s) left)");
                } else if (doomReflection[0] == 2) {
                    System.out.println("Doom Reflection is on cooldown! (" + doomReflection[1] + " turn(s) left)");
                } else {
                    doomReflection[0] = 1;
                    doomReflection[1] = 3; 
                    System.out.println("You activate DOOM REFLECTION! Next 3 attacks will be reflected.");
                }
                System.out.println();
            } else {
                System.out.println("Invalid input! Please enter 1, 2, 3, 4 or 'exit'.");
                System.out.println();
                continue;
            }

            // --- SKILL COOLDOWN MANAGEMENT ---
            if (stunCooldown[0] == 1) {
                stunCooldown[1]--;
                if (stunCooldown[1] <= 0) {
                    stunCooldown[0] = 0;
                    stunCooldown[1] = 0;
                    System.out.println("Stun Attack cooldown finished! Skill is ready to use again.");
                }
            }

            if (doomReflection[0] == 2) {
                doomReflection[1]--;
                if (doomReflection[1] <= 0) {
                    doomReflection[0] = 0;
                    doomReflection[1] = 0;
                    System.out.println("Doom Reflection cooldown finished! Skill is ready to use again.");
                }
            }

            // --- MONSTER TURN ---
            if (monsterHp > 0) {
                if (monsterStunned) {
                    // If stunned, monster skips this turn
                    System.out.println(monsterName + " is stunned and cannot attack this turn.");
                    System.out.println();
                    monsterStunned = false;
                } else {
                    int monsterDmg = monsterMinDmg + random.nextInt(monsterMaxDmg - monsterMinDmg + 1);

                    // Desperate Gambit empowered/nerfed 
                    if (desperateGambitActive && !desperateGambitStack.isEmpty()) {
                        monsterDmg = monsterDmg + (monsterDmg * 10 / 100);
                        int turnsLeft = desperateGambitStack.pop() - 1;
                        if (turnsLeft > 0) {
                            desperateGambitStack.push(turnsLeft);
                        } else {
                            desperateGambitActive = false;
                            desperateGambitNerf = true;
                            System.out.println("Desperate Gambit's power fades. " + monsterName + " now deals half damage and its max damage returns to normal!");
                            monsterMinDmg = 5;
                            monsterMaxDmg = 10;
                        }
                    } else if (desperateGambitNerf) {
                        monsterDmg = monsterDmg / 2;
                    }

                    // Doom Reflection skill 
                    if (doomReflection[0] == 1 && doomReflection[1] > 0) {
                        int reflectRoll = random.nextInt(4); 
                        if (reflectRoll == 0) {
                            System.out.println("DOOM REFLECTION: Perfect reflect! " + monsterName + " takes " + monsterDmg + " damage, you take 0!");
                            monsterHp -= monsterDmg;
                            if (monsterHp < 0) monsterHp = 0;
                        } else {
                            int reflected = monsterDmg / 2;
                            System.out.println("DOOM REFLECTION: You reflect " + reflected + " damage back! You take " + monsterDmg + " damage.");
                            monsterHp -= reflected;
                            playerHp -= monsterDmg;
                            if (playerHp < 0) playerHp = 0;
                            if (monsterHp < 0) monsterHp = 0;
                        }
                        doomReflection[1]--;
                        if (doomReflection[1] <= 0) {
                            doomReflection[0] = 2; 
                            doomReflection[1] = 6; 
                            System.out.println("Doom Reflection has ended! Cooldown started.");
                        } else {
                            System.out.println("Doom Reflection remains active for " + doomReflection[1] + " more turn(s).");
                        }
                        System.out.println();
                    } else {
                        // Normal monster attack
                        System.out.println(monsterName.toUpperCase() + "'S TURN!");
                        System.out.println(monsterName + " attacks and deals " + monsterDmg + " damage to you.");
                        playerHp -= monsterDmg;
                        if (playerHp < 0) playerHp = 0;
                        System.out.println("Your remaining hp: " + playerHp);
                        System.out.println();
                    }

                    // Monster Regeneration passive
                    int regenChance = random.nextInt(4); 
                    if (!monsterHpStack.isEmpty() && regenChance == 0) {
                        int prevHp = monsterHpStack.peek();
                        System.out.println(monsterName + " activates passive: 'Regeneration'!");
                        System.out.println("Regeneration: " + monsterName + " restores its HP to " + prevHp);
                        monsterHp = prevHp;
                        System.out.println();
                    }
                }
            }
            
            // --- FINAL WIN/LOSS CHECKS ---
            if (monsterHp <= 0) {
                System.out.println("Congratulations! You defeated " + monsterName + "!");
                break;
            }
            if (playerHp <= 0 && monsterHp <= 0) {
                System.out.println("It's a draw!");
                break;
            } else if (playerHp <= 0) {
                System.out.println("You lost! " + monsterName + " wins!");
                break;
            }
        }
    }
}