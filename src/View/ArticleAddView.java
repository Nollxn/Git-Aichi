package View;

import Controler.ArticleAddController;

import javax.swing.*;

public class ArticleAddView extends JFrame {
    private JTextField name_field;
    private JTextField price_field;
    private JTextField stock_field;
    private JTextField description_field;
    private JButton ajouterButton;
    private JButton annulerButton;
    private JPanel addArticle_panel;

    public ArticleAddView() {
        this.setTitle("Ajouter un article");
        this.setContentPane(addArticle_panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        new ArticleAddController(this);
    }

    public JButton getAjouterButton() {return ajouterButton;}

    public JButton getAnnulerButton() {return annulerButton;}

    public String getName() {return name_field.getText();}

    public double getPrice() {return Double.parseDouble(price_field.getText());}

    public int getStock() {return Integer.parseInt(stock_field.getText());}

    public String getDescription() {return description_field.getText();}
}
