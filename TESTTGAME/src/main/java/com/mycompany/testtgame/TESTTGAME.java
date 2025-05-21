/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testtgame;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Students Account
 */
public class TESTTGAME {

   public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // -- LINKED LISTS FOR MONSTER NAME --
        LinkedList<String> firstNames = new LinkedList<>();
        LinkedList<String> lastNames = new LinkedList<>();
        
        // --- FIRST NAME ---
        firstNames.add("Dodong");
        firstNames.add("Inday");
        firstNames.add("Junjun");
        firstNames.add("Mitch");
        firstNames.add("Nene");
        firstNames.add("Boyet");
        firstNames.add("Neneng");
        firstNames.add("Neill");
        firstNames.add("Bong");
        firstNames.add("Attos");
        firstNames.add("Makoy");
        firstNames.add("Diday");
        firstNames.add("Baste");
        firstNames.add("Tata");
        firstNames.add("Kulot");
        firstNames.add("Mitch");
        firstNames.add("Berto");
        firstNames.add("Emeng");
        firstNames.add("Junrix");
        firstNames.add("Reyian");
        firstNames.add("Joshua");
        firstNames.add("Nonoy");
        firstNames.add("Ateng");
        firstNames.add("Mang Kanor");
        firstNames.add("Lynlyn");
        firstNames.add("Jomari");
        firstNames.add("Benok");
        firstNames.add("Tans");
        firstNames.add("Tating");
        firstNames.add("Karding");
        firstNames.add("Dodz");
        firstNames.add("Dencio");
        firstNames.add("Nardo");
        firstNames.add("Iking");
        firstNames.add("Paping");
        firstNames.add("Yoyoy");
        firstNames.add("Lolong");
        firstNames.add("Luningning");
        firstNames.add("Buboy");
        firstNames.add("Dagul");
        firstNames.add("Chiqui");
        firstNames.add("Melai");
        firstNames.add("Tonton");
        firstNames.add("Gorio");
        firstNames.add("Obet");
        firstNames.add("Chacha");
        firstNames.add("Dindin");
        firstNames.add("Bogs");
        firstNames.add("Tisay");
        firstNames.add("Marlon");
        firstNames.add("Teban");
        
        // --- LAST NAME ---
        lastNames.add("Bago Aplaya");
        lastNames.add("Bago Gallera");
        lastNames.add("Baliok");
        lastNames.add("Bucana");
        lastNames.add("Catalunan Grande");
        lastNames.add("Catalunan Pequeno");
        lastNames.add("Dumoy");
        lastNames.add("Langub");
        lastNames.add("Ma-a");
        lastNames.add("Magtuod");
        lastNames.add("Matina Aplaya");
        lastNames.add("Matina Crossing");
        lastNames.add("Matina Pangi");
        lastNames.add("Talomo Proper");
        lastNames.add("Acacia");
        lastNames.add("Alfonso Angliongto Sr.");
        lastNames.add("Buhangin Proper");
        lastNames.add("Cabantian");
        lastNames.add("Callawa");
        lastNames.add("Communal");
        lastNames.add("Indangan");
        lastNames.add("Sasa");
        lastNames.add("Agdao");
        lastNames.add("Toril");
        lastNames.add("Calinan");
        lastNames.add("Tibungco");
        lastNames.add("Mintal");
        lastNames.add("Ulas");
        lastNames.add("Bangkal");
        lastNames.add("San Antonio");
        lastNames.add("Leon Garcia");
        lastNames.add("Bunawan");
        lastNames.add("Panacan");
        lastNames.add("Lasang");
        lastNames.add("Ilang");
        lastNames.add("Riverside");
        lastNames.add("Crossing Bayabas");
        lastNames.add("Mandug");
        lastNames.add("Amper");
        lastNames.add("Manunggabay");
        lastNames.add("Mangulata");
        lastNames.add("Kawatan");
        lastNames.add("Balut");
        lastNames.add("Black");
        lastNames.add("Pitbull");
        lastNames.add("ComputerShop");
        lastNames.add("Netbeans");
        lastNames.add("Google Chrome");
        lastNames.add("Barangay 38-D");
        lastNames.add("Barangay 40-D");
        
        boolean exitGame = false;
        
        // --- GENERATE MONSTER'S NAME ---
        while (!exitGame && !firstNames.isEmpty() && !lastNames.isEmpty()) {
            int firstIndex = random.nextInt(firstNames.size());
            int lastIndex = random.nextInt(lastNames.size());
            String monsterFirst = firstNames.remove(firstIndex);
            String monsterLast = lastNames.remove(lastIndex);
            String monsterName = monsterFirst + " " + monsterLast; 

            // -- INITIAL STATS -- 
            int playerHp = 100;
            int playerMaxHp = 100;
            int monsterHp = 100; 
            int monsterMaxHp = 100;

            int playerMinDmg = 5;
            int playerMaxDmg = 10;

            int monsterMinDmg = 5;
            int monsterMaxDmg = 10;

            // -- MONTSER'S STATUS AND BUFF/NERF --
            boolean monsterStunned = false;
            boolean desperateGambitActive = false;
            boolean desperateGambitNerf = false;
            boolean desperateGambitUsed = false;

            // -- STACKS --
            Stack<Integer> monsterHpStack = new Stack<>();
            Stack<Integer> desperateGambitStack = new Stack<>();
            Stack<Integer> jinguBuffStack = new Stack<>();

            int jinguHitCounter = 0;

            // -- DOOM REFLECTION ARRAY -- 
            int[] doomReflection = new int[2];
            doomReflection[0] = 0;
            doomReflection[1] = 0;

            // -- STUN COOLDOWN ARRAY --
            int[] stunCooldown = new int[2];
            stunCooldown[0] = 0;
            stunCooldown[1] = 0;

            System.out.println("YOU ENCOUNTERED AN ENEMY: " + monsterName + "!");

            gameLoop:
            while (true) {
                System.out.println("Player Hp: " + playerHp);
                System.out.println(monsterName + " Hp: " + monsterHp);
                System.out.println();

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

                // -------- PLAYER'S TURN --------
                System.out.println("PLAYER'S TURN!");
                System.out.println("1. Normal Attack");
                String stunStatus = (stunCooldown[0] == 1 ? "Cooldown: " + stunCooldown[1] : "Ready");
                 String doomStatus = (doomReflection[0] == 2) ? ("Cooldown: "+doomReflection[1]) 
                                    : (doomReflection[0] == 1 ? ("Active: "+doomReflection[1]+" turn(s) left") 
                                    : "Ready");
                System.out.println("2. Stun Attack (" + stunStatus + ")");
                System.out.println("3. Activate Doom Reflection (" + doomStatus + ")");
               
                System.out.println("4. Skip Turn");
                System.out.print("Choose (or type exit): ");

                String input = scanner.next();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("You exited the game.");
                    exitGame = true;
                    break gameLoop;
                }

                // ---- NORMAL ATTACK ----
                if (input.equals("1")) {
                    jinguHitCounter++;
                    int playerDmg = playerMinDmg + random.nextInt(playerMaxDmg - playerMinDmg + 1);

                    if (jinguBuffStack.isEmpty() && jinguHitCounter == 4) {
                        System.out.println("Passive Jingu Mastery is activated!");
                        System.out.println("Jingu Mastery activated! Next 4 normal attacks gain 120% bonus damage and 80% lifesteal!");
                        for (int i = 0; i < 4; i++) {
                            jinguBuffStack.push(1);
                        }
                        jinguHitCounter = 0;
                    }

                    if (!jinguBuffStack.isEmpty()) {
                        int bonusDmg = (int)Math.round(playerDmg * 1.2); 
                        playerDmg += bonusDmg;
                        int lifesteal = (int)Math.round(playerDmg * 0.80);
                        
                        if (playerHp + lifesteal > playerMaxHp) {
                            lifesteal = playerMaxHp - playerHp;
                            if (lifesteal < 0) lifesteal = 0;
                        }
                        playerHp += lifesteal;
                        System.out.println("Jingu Mastery buff: +120% bonus (" + bonusDmg + ") damage and +" + lifesteal + " HP (lifesteal)!");
                        jinguBuffStack.pop();
                    }

                    System.out.println("You dealt " + playerDmg + " damage to " + monsterName + ".");
                    monsterHpStack.push(monsterHp);
                    monsterHp -= playerDmg;
                    if (monsterHp < 0) monsterHp = 0;
                    System.out.println(monsterName + " remaining hp: " + monsterHp);
                    System.out.println();

                    if (monsterHp <= 0 && !desperateGambitActive && !desperateGambitNerf && !desperateGambitUsed) {
                        int chance = random.nextInt(1
                        ); 
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
                } 
                // --- STUN ATTACK ---
                else if (input.equals("2")) {
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
                }
                // --- DOOM REFLECTION ---
                else if (input.equals("3")) {
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
                } 
                // ---- SKIP TURN ---
                else if (input.equals("4")) {
                    jinguHitCounter = 0;
                    System.out.println("You skipped your turn!");
                    System.out.println();
                    
                } else {
                    System.out.println("Invalid input! Please enter 1, 2, 3, 4 or 'exit'.");
                    System.out.println();
                    continue;
                }

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

                // -------- MONSTER'S TURN --------
                if (monsterHp > 0) {
                    if (monsterStunned) {
                        System.out.println(monsterName + " is stunned and cannot attack this turn.");
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
                                System.out.println("Desperate Gambit's power fades. " + monsterName + " now deals half damage and its max damage returns to normal!");
                                monsterMinDmg = 5;
                                monsterMaxDmg = 10;
                            }
                        } else if (desperateGambitNerf) {
                            monsterDmg = monsterDmg / 2;
                        }

                        // -- DOOM REFLECTION CHECK --
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
                            System.out.println(monsterName.toUpperCase() + "'S TURN!");
                            System.out.println(monsterName + " attacks and deals " + monsterDmg + " damage to you.");
                            playerHp -= monsterDmg;
                            if (playerHp < 0) playerHp = 0;
                            System.out.println("Your remaining hp: " + playerHp);
                            System.out.println();
                        }

                        // --- REGENERATION PASSIVE ---
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
                
                // ---- WIN CHECK ----
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

            // --- AFTER BATTLE CHECK ---
            if (!exitGame && (!firstNames.isEmpty() && !lastNames.isEmpty())) {
                System.out.print("Do you want to continue to fight another monster? (yes/exit): ");
                String nextAction = scanner.next();
                if (nextAction.equalsIgnoreCase("exit")) {
                    System.out.println("You exited the game.");
                    exitGame = true;
                } else if (!nextAction.equalsIgnoreCase("yes")) {
                    System.out.println("Invalid input. Exiting the game.");
                    exitGame = true;
                }
            } else if (firstNames.isEmpty() || lastNames.isEmpty()) {
                System.out.println("No more unique monster names left. You have defeated all possible monsters!");
                exitGame = true;
            }
        }

        System.out.println("Game Over.");
    }
}