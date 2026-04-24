import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GestionEtudiants {
    // Liste dynamique contenant tous les étudiants.
    private ArrayList<Etudiant> etudiants;

    // Constructeur : crée une liste vide d'étudiants.
    public GestionEtudiants() {
        etudiants = new ArrayList<>();
    }

    // Charge les étudiants depuis un fichier CSV.
    public void chargerEtudiants(String fichier) {
        etudiants = GestionFichier.lireEtudiantsDepuisCSV(fichier);
    }

    // Affiche tous les étudiants chargés.
    public void afficherEtudiants() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant chargé.");
            return;
        }

        for (Etudiant e : etudiants) {
            e.afficher();
        }
    }

    // Affiche uniquement les étudiants valides, triés par moyenne décroissante.
    public void afficherClassement() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant chargé.");
            return;
        }

        ArrayList<Etudiant> valides = new ArrayList<>();

        // On garde seulement les étudiants dont la moyenne peut être calculée.
        for (Etudiant e : etudiants) {
            if (e.isValide()) {
                valides.add(e);
            }
        }

        if (valides.isEmpty()) {
            System.out.println("Aucun étudiant valide à classer.");
            return;
        }

        // Tri décroissant selon la moyenne générale.
        Collections.sort(valides, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                return Double.compare(e2.calculerMoyenne(), e1.calculerMoyenne());
            }
        });

        System.out.println("===== Classement des étudiants valides =====");

        // Affichage du rang de chaque étudiant.
        for (int i = 0; i < valides.size(); i++) {
            System.out.println((i + 1) + ". " + valides.get(i));
        }
    }

    // Sauvegarde les résultats dans un fichier CSV.
    public void sauvegarderResultats(String fichier) {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant à sauvegarder.");
            return;
        }

        ArrayList<Etudiant> listeTriee = new ArrayList<>();

        // Ajouter d'abord les étudiants valides.
        for (Etudiant e : etudiants) {
            if (e.isValide()) {
                listeTriee.add(e);
            }
        }

        // Trier les étudiants valides par moyenne décroissante.
        Collections.sort(listeTriee, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                return Double.compare(e2.calculerMoyenne(), e1.calculerMoyenne());
            }
        });

        // Ajouter les étudiants invalides à la fin.
        for (Etudiant e : etudiants) {
            if (!e.isValide()) {
                listeTriee.add(e);
            }
        }

        // Écriture finale dans le fichier CSV.
        GestionFichier.ecrireResultatsDansCSV(fichier, listeTriee);
    }

    // Retourne le nombre total d'étudiants chargés.
    public int getNombreEtudiants() {
        return etudiants.size();
    }
}
