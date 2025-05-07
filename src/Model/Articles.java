package Model;

public class Articles {

    private String nom_article;
    private double prix_article;
    private int quantite_article;
    private int ID;
    private String description_article;

    public Articles(){}

    public Articles(String nom_article, double prix_article, int quantite_article, String description_article) {
        this.nom_article = nom_article;
        this.prix_article = prix_article;
        this.quantite_article = quantite_article;
        this.description_article = description_article;
    }

    public Articles(int ID, String nom_article, int quantite_article, String description_article, int prix_article) {
        this.ID = ID;
        this.nom_article = nom_article;
        this.quantite_article = quantite_article;
        this.description_article = description_article;
    }

    public String getNom_article() {return nom_article;}

    public double getPrix_article() {return prix_article;}

    public String getDescription_article() {return description_article;}

    public int getQuantite_article() {return quantite_article;}

    public int get_ID() {return ID;}

    public void setNom_article(String nom_article) {this.nom_article = nom_article;}

    public void setPrix_article(double prix_article) {this.prix_article = prix_article;}

    public void setDescription_article(String description_article) {this.description_article = description_article;}

    public void setQuantite_article(int quantite_article) {this.quantite_article = quantite_article;}

    public void setID(int ID) {this.ID = ID;}

}
