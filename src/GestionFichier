import java.io.*;
import java.util.ArrayList;

public class GestionFichier {

    // Lit le fichier CSV et retourne la liste des étudiants.
    public static ArrayList<Etudiant> lireEtudiantsDepuisCSV(String nomFichier) {
        ArrayList<String> lignes = lireToutesLesLignes(nomFichier);
        ArrayList<String> toutesLesMatieres = extraireToutesLesMatieres(lignes);
        ArrayList<Etudiant> listeEtudiants = new ArrayList<>();

        // Parcours de chaque ligne du fichier.
        for (int i = 0; i < lignes.size(); i++) {
            String ligne = lignes.get(i);
            int numeroLigne = i + 1;

            // split avec -1 permet de conserver les cases vides.
            String[] data = ligne.split(",", -1);

            // Vérifie que la ligne respecte le format attendu.
            if (data.length < 6 || (data.length - 2) % 4 != 0) {
                System.out.println("Ligne ignorée " + numeroLigne + " : format invalide.");
                continue;
            }

            String nom = data[0].trim();
            String prenom = data[1].trim();

            // Vérifie que le nom et le prénom existent.
            if (nom.isEmpty() || prenom.isEmpty()) {
                System.out.println("Ligne ignorée " + numeroLigne + " : nom ou prénom manquant.");
                continue;
            }

            Etudiant etudiant = new Etudiant(nom, prenom);

            // Lecture des matières par groupes de 4 : matière, note1, note2, note3.
            for (int j = 2; j < data.length; j += 4) {
                String matiere = data[j].trim();
                String n1 = data[j + 1].trim();
                String n2 = data[j + 2].trim();
                String n3 = data[j + 3].trim();

                // Vérifie les valeurs manquantes.
                if (matiere.isEmpty() || n1.isEmpty() || n2.isEmpty() || n3.isEmpty()) {
                    etudiant.setInvalide("matière ou note manquante.");
                    break;
                }

                try {
                    // Conversion des notes de String vers double.
                    double note1 = Double.parseDouble(n1);
                    double note2 = Double.parseDouble(n2);
                    double note3 = Double.parseDouble(n3);

                    // Vérifie que les notes sont entre 0 et 20.
                    if (!noteValide(note1) || !noteValide(note2) || !noteValide(note3)) {
                        etudiant.setInvalide("note hors intervalle [0,20].");
                        break;
                    }

                    // Ajoute la matière à l'étudiant.
                    etudiant.ajouterMatiere(new Matiere(matiere, note1, note2, note3));

                } catch (NumberFormatException e) {
                    // Cas où une note n'est pas un nombre.
                    etudiant.setInvalide("note non numérique.");
                    break;
                }
            }

            // Vérifie que l'étudiant possède toutes les matières présentes dans le fichier.
            verifierMatieresCompletes(etudiant, toutesLesMatieres);

            listeEtudiants.add(etudiant);
        }

        return listeEtudiants;
    }

    // Lit toutes les lignes non vides du fichier.
    private static ArrayList<String> lireToutesLesLignes(String nomFichier) {
        ArrayList<String> lignes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;

            while ((ligne = br.readLine()) != null) {
                if (!ligne.trim().isEmpty()) {
                    lignes.add(ligne);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erreur : fichier introuvable -> " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }

        return lignes;
    }

    // Extrait toutes les matières présentes dans le fichier CSV.
    private static ArrayList<String> extraireToutesLesMatieres(ArrayList<String> lignes) {
        ArrayList<String> matieres = new ArrayList<>();

        for (String ligne : lignes) {
            String[] data = ligne.split(",", -1);

            if (data.length >= 6 && (data.length - 2) % 4 == 0) {
                for (int i = 2; i < data.length; i += 4) {
                    String matiere = data[i].trim();

                    // Ajoute la matière seulement si elle n'existe pas déjà.
                    if (!matiere.isEmpty() && !contientMatiere(matieres, matiere)) {
                        matieres.add(matiere);
                    }
                }
            }
        }

        return matieres;
    }

    // Vérifie si un étudiant possède toutes les matières attendues.
    private static void verifierMatieresCompletes(Etudiant etudiant, ArrayList<String> toutesLesMatieres) {
        if (!etudiant.isValide()) {
            return;
        }

        String matieresManquantes = "";

        for (String matiere : toutesLesMatieres) {
            if (!etudiant.possedeMatiere(matiere)) {

                if (!matieresManquantes.isEmpty()) {
                    matieresManquantes += ", ";
                }

                matieresManquantes += matiere;
            }
        }

        // Si une ou plusieurs matières manquent, l'étudiant devient invalide.
        if (!matieresManquantes.isEmpty()) {
            etudiant.setInvalide("matières manquantes : " + matieresManquantes);
        }
    }

    // Vérifie si une matière existe déjà dans la liste.
    private static boolean contientMatiere(ArrayList<String> liste, String matiere) {
        for (String m : liste) {
            if (m.equalsIgnoreCase(matiere)) {
                return true;
            }
        }
        return false;
    }

    // Vérifie qu'une note est bien comprise entre 0 et 20.
    private static boolean noteValide(double note) {
        return note >= 0 && note <= 20;
    }

    // Écrit les résultats dans un fichier CSV.
    public static void ecrireResultatsDansCSV(String nomFichier, ArrayList<Etudiant> liste) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomFichier))) {
            // En-tête du fichier CSV exporté.
            pw.println("Etudiant,Moyenne,Mention,Statut");

            for (Etudiant e : liste) {
                if (e.isValide()) {
                    pw.printf("%s,%.2f,%s,Valide%n",
                            e.getNomComplet(),
                            e.calculerMoyenne(),
                            e.getMention());
                } else {
                    pw.printf("%s,,,%s%n",
                            e.getNomComplet(),
                            e.getMessageErreur());
                }
            }

            System.out.println("Résultats sauvegardés dans : " + nomFichier);

        } catch (IOException e) {
            System.out.println("Erreur d'écriture du fichier : " + e.getMessage());
        }
    }
}
