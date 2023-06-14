import java.util.Random;

public class TestGrille {
    private static final int SOL_NU = 0;
    private static final int ARBRE_VIE = 1;
    private static final int ARBRE_FEU = 2;
    private static final int ARBRE_CENDRE = 3;
    private static final int ARBRE_SOL_HUMIDE = 4;
    private static final int ARBRE_PEU_INFLAMMABLE = 5;

    private int[][] grille;
    private int taille;
    private int densite;
    private double probabiliteFeu;
    private double probabiliteHumidite;
    private double probabiliteInflammabilite;
    private Random random = new Random();

    public TestGrille(int taille, int densite, double probabiliteFeu, double probabiliteHumidite, double probabiliteInflammabilite) {
        this.taille = taille;
        this.densite = densite;
        this.probabiliteFeu = probabiliteFeu;
        this.probabiliteHumidite = probabiliteHumidite;
        this.probabiliteInflammabilite = probabiliteInflammabilite;
        grille = new int[taille][taille];
        initialiserGrille();
    }

    private void initialiserGrille() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                double r = random.nextDouble();
                if (r < densite) {
                    grille[i][j] = ARBRE_VIE;
                    if (r < probabiliteHumidite) {
                        grille[i][j] = ARBRE_SOL_HUMIDE;
                    }
                    if (r < probabiliteInflammabilite) {
                        grille[i][j] = ARBRE_PEU_INFLAMMABLE;
                    }
                } else {
                    grille[i][j] = SOL_NU;
                }
            }
        }
    }

    public void afficherGrille() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                switch (grille[i][j]) {
                    case SOL_NU:
                        System.out.print("  ");
                        break;
                    case ARBRE_VIE:
                        System.out.print("T ");
                        break;
                    case ARBRE_FEU:
                        System.out.print("F ");
                        break;
                    case ARBRE_CENDRE:
                        System.out.print("C ");
                        break;
                    case ARBRE_SOL_HUMIDE:
                        System.out.print("H ");
                        break;
                    case ARBRE_PEU_INFLAMMABLE:
                        System.out.print("P ");
                        break;
                    default:
                        System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public void simulationFeu() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                switch (grille[i][j]) {
                    case ARBRE_FEU:
                        grille[i][j] = ARBRE_CENDRE;
                        break;
                    case ARBRE_VIE:
                        if (voisinEnFeu(i, j)) {
                            grille[i][j] = ARBRE_FEU;
                        } else if (random.nextDouble() < probabiliteFeu) {
                            grille[i][j
