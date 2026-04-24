public class Matiere {
    // Attributs privés : principe d'encapsulation.
    private String nom;
    private double note1;
    private double note2;
    private double note3;

    // Constructeur : initialise une matière avec son nom et ses trois notes.
    public Matiere(String nom, double note1, double note2, double note3) {
        this.nom = nom;
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
    }

    // Getter qui retourne le nom de la matière.
    public String getNom() {
        return nom;
    }

    // Calcule la moyenne des trois notes de la matière.
    public double calculerMoyenne() {
        return (note1 + note2 + note3) / 3.0;
    }

    // Affiche les notes de la matière et sa moyenne.
    public void afficher() {
        System.out.printf("%s : %.2f, %.2f, %.2f | Moyenne : %.2f%n",
                nom, note1, note2, note3, calculerMoyenne());
    }
}
