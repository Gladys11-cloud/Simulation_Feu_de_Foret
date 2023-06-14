import java.util.Random;

public class Grille {
    private int[][] grille;
    private int taille;
    private int densite;
    private double probabiliteFeu;
    private Random random = new Random();

    public Grille(int taille, int densite, double probabiliteFeu) {
        this.taille = taille;
        this.densite = densite;
        this.probabiliteFeu = probabiliteFeu;
        grille = new int[taille][taille];
        initialiserGrille();
    }

    private void initialiserGrille() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (random.nextInt(100) < densite) {
                    grille[i][j] = 1;
                } else {
                    grille[i][j] = 0;
                }
            }
        }
    }

    public void afficherGrille() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                System.out.print(grille[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void simulationFeu() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] == 2) {
                    grille[i][j] = 0;
                } else if (grille[i][j] == 1) {
                    if (voisinEnFeu(i, j)) {
                        grille[i][j] = 2;
                    } else if (random.nextDouble() < probabiliteFeu) {
                        grille[i][j] = 2;
                    }
                }
            }
        }
    }

    private boolean voisinEnFeu(int i, int j) {
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (k == 0 && l == 0) {
                    continue;
                }
                int x = i + k;
                int y = j + l;
                if (x >= 0 && x < taille && y >= 0 && y < taille && grille[x][y] == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Grille grille = new Grille(10, 50, 0.6);
        grille.afficherGrille();
        for (int i = 0; i < 10; i++) {
            grille.simulationFeu();
            grille.afficherGrille();
        }
    }
}

