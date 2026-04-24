import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner permet de lire les choix saisis par l'utilisateur.
        Scanner scanner = new Scanner(System.in);

        // Objet principal qui gère les étudiants et les traitements.
        GestionEtudiants gestion = new GestionEtudiants();

        int choix;

        // Boucle du menu : elle continue tant que l'utilisateur ne choisit pas 5.
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Charger les étudiants depuis le fichier CSV");
            System.out.println("2. Afficher les étudiants et leurs moyennes");
            System.out.println("3. Classer les étudiants par moyenne");
            System.out.println("4. Sauvegarder les résultats dans un fichier CSV");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");

            // Vérifie que l'utilisateur entre bien un nombre entier.
            while (!scanner.hasNextInt()) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine();
                System.out.print("Votre choix : ");
            }

            choix = scanner.nextInt();
            scanner.nextLine(); // Vide le retour à la ligne restant.

            // Exécute l'action correspondant au choix de l'utilisateur.
            switch (choix) {
                case 1:
                    gestion.chargerEtudiants("GestionNotesEtudiants-main/data/notes.csv");
                    System.out.println("Nombre d'étudiants chargés : " + gestion.getNombreEtudiants());
                    break;

                case 2:
                    gestion.afficherEtudiants();
                    break;

                case 3:
                    gestion.afficherClassement();
                    break;

                case 4:
                    gestion.sauvegarderResultats("data/resultats.csv");
                    break;

                case 5:
                    System.out.println("Fin du programme.");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 5);

        // Fermeture du scanner à la fin du programme.
        scanner.close();
    }
}
