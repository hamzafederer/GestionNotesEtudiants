import java.util.ArrayList;

public class Etudiant {
    // Informations principales de l'étudiant.
    private String nom;
    private String prenom;

    // Liste des matières de l'étudiant.
    private ArrayList<Matiere> matieres;

    // Indique si l'étudiant possède des données complètes et correctes.
    private boolean valide;

    // Message expliquant pourquoi la moyenne n'est pas calculée.
    private String messageErreur;

    // Constructeur : initialise un étudiant avec une liste vide de matières.
    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.matieres = new ArrayList<>();
        this.valide = true;
        this.messageErreur = "";
    }

    // Retourne le nom complet de l'étudiant.
    public String getNomComplet() {
        return nom + " " + prenom;
    }

    // Retourne la liste des matières.
    public ArrayList<Matiere> getMatieres() {
        return matieres;
    }

    // Indique si l'étudiant est valide.
    public boolean isValide() {
        return valide;
    }

    // Retourne le message d'erreur associé à l'étudiant.
    public String getMessageErreur() {
        return messageErreur;
    }

    // Rend l'étudiant invalide avec un message explicatif.
    public void setInvalide(String messageErreur) {
        this.valide = false;
        this.messageErreur = messageErreur;
    }

    // Ajoute une matière à la liste des matières.
    public void ajouterMatiere(Matiere matiere) {
        matieres.add(matiere);
    }

    // Vérifie si l'étudiant possède une matière donnée.
    public boolean possedeMatiere(String nomMatiere) {
        for (Matiere m : matieres) {
            if (m.getNom().equalsIgnoreCase(nomMatiere)) {
                return true;
            }
        }
        return false;
    }

    // Calcule la moyenne générale de l'étudiant.
    public double calculerMoyenne() {
        if (!valide || matieres.isEmpty()) {
            return 0;
        }

        double somme = 0;

        // Additionne les moyennes de toutes les matières.
        for (Matiere m : matieres) {
            somme += m.calculerMoyenne();
        }

        return somme / matieres.size();
    }

    // Retourne la mention selon la moyenne générale.
    public String getMention() {
        double moyenne = calculerMoyenne();

        if (!valide) {
            return "Non calculée";
        } else if (moyenne >= 16) {
            return "Très bien";
        } else if (moyenne >= 14) {
            return "Bien";
        } else if (moyenne >= 12) {
            return "Assez bien";
        } else if (moyenne >= 10) {
            return "Passable";
        } else {
            return "Échec";
        }
    }

    // Affiche toutes les informations de l'étudiant.
    public void afficher() {
        System.out.println("Étudiant : " + getNomComplet());

        for (Matiere m : matieres) {
            m.afficher();
        }

        if (valide) {
            System.out.printf("Moyenne générale : %.2f%n", calculerMoyenne());
            System.out.println("Mention : " + getMention());
        } else {
            System.out.println("Moyenne non calculée : " + messageErreur);
        }

        System.out.println("-----------------------------------");
    }

    // Représentation textuelle utilisée notamment dans le classement.
    @Override
    public String toString() {
        if (valide) {
            return getNomComplet()
                    + " - Moyenne : " + String.format("%.2f", calculerMoyenne())
                    + " - Mention : " + getMention();
        } else {
            return getNomComplet()
                    + " - Moyenne non calculée : " + messageErreur;
        }
    }
}
