package com.morpion;

import java.util.Scanner;

public class Morpion {

    static void afficherGrille(char[][] grille) {
        System.out.println("\n  ╔═ GRILLE DE JEU ═╗");
        System.out.println("  ║     1   2   3   ║");
        
        for(int i = 0 ; i < 3 ; i++) {
            System.out.println("  ║    --- --- ---  ║");
            System.out.print("  ║ " + (i + 1) + " | " + grille[i][0]);
            for(int j = 1 ; j < 3 ; j++) {
                System.out.print("  | " + grille[i][j]);
            }
            
            System.out.println("  | ║");
        }
        System.out.println("  ║    --- --- ---  ║");

        System.out.println("  ╚═════════════════╝");
    }
    
    static boolean conditionArret(char[][] grille) {
        // Vérifier les lignes
        for (int i = 0; i < 3; i++) {
            if (grille[i][0] == grille[i][1] && grille[i][1] == grille[i][2] && grille[i][0] != '\0') {
                return true;
            }
        }
        // Vérifier les colonnes
        for (int j = 0; j < 3; j++) {
            if (grille[0][j] == grille[1][j] && grille[1][j] == grille[2][j] && grille[0][j] != '\0') {
                return true;
            }
        }
        // Vérifier les diagonales
        if (grille[0][0] == grille[1][1] && grille[1][1] == grille[2][2] && grille[0][0] != '\0') {
            return true;
        }
        if (grille[0][2] == grille[1][1] && grille[1][1] == grille[2][0] && grille[0][2] != '\0') {
            return true;
        }
        return false;
    }
    
    static byte verifierTourJoueur(byte numeroTour) {
        return (byte) ((numeroTour % 2 == 0) ? 2 : 1);
    }
    
    static boolean estCaseOccupee(char[][] grille, int ligne, int colonne) {
        return grille[ligne - 1][colonne - 1] != '\0';
    }
    
    static char[][] affecterValeur(char[][] grille, int ligne, int colonne, char valeur) {
        grille[ligne - 1][colonne - 1] = valeur;
        return grille;
    }
    
    static char[][] dessinerCroix(char[][] grille, int ligne, int colonne) {
        return affecterValeur(grille, ligne, colonne, 'X');
    }
    
    static char[][] dessinerRond(char[][] grille, int ligne, int colonne) {
        return affecterValeur(grille, ligne, colonne, 'O');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char grille[][] = new char[3][3];
        byte numeroTour = 0;
        byte ligne, colonne;
        
        System.out.println("***************************************************");
        System.out.println("**** JEU DU MORPION * ÉVALUATION DU 31/07/2023 ****");
        System.out.println("***************************************************");

        System.out.println(" ╔════════════════╗");
        System.out.println(" ║  JOUEUR 1 : X  ║");
        System.out.println(" ║  JOUEUR 2 : O  ║");
        System.out.println(" ╚════════════════╝");
        
        while(!conditionArret(grille) && numeroTour != 9) {
            numeroTour++;
            
            byte joueur = verifierTourJoueur(numeroTour);
            
            afficherGrille(grille);
            
            System.out.println();
            
            System.out.println(" >>> Au tour du Joueur " + joueur);
            
            do {
                System.out.print(" >>> Numéro de la ligne (1 à 3) : ");
                ligne = sc.nextByte();
                System.out.print(" >>> Numéro de la colonne (1 à 3) : ");
                colonne = sc.nextByte();
                
                if(estCaseOccupee(grille, ligne, colonne)) {
                    System.out.println(" /!\\ La case (" + ligne + "," + colonne + ") n'est pas vide !\n");
                }
            } while(estCaseOccupee(grille, ligne, colonne));
            
            switch(verifierTourJoueur(numeroTour)) {
                case 1:
                    grille = dessinerCroix(grille, ligne, colonne);
                    break;
                case 2:
                    grille = dessinerRond(grille, ligne, colonne);
                    break;
            }
        }
        
        System.out.println();
        
        if(conditionArret(grille)) {
            afficherGrille(grille);
            System.out.println(" >>> Jeu terminé en " + numeroTour + " tours.");
            System.out.println("\tLe gagnant est le Joueur " + verifierTourJoueur(numeroTour) + ".");
        } else {
            System.out.println(" >>> Jeu terminé par un match nul !");
        }
        
        sc.close();
    }
}