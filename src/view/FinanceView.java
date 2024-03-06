package view;

import javax.swing.*;
import java.awt.*;

public class FinanceView extends JPanel {

      public FinanceView(){

          setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

          JPanel totalPanel = new JPanel(new GridLayout(0, 1, 30, 30));
          JLabel title = new JLabel("Page de Financement");
          title.setFont(new Font("Arial", Font.PLAIN, 20));
          title.setHorizontalAlignment(SwingConstants.CENTER);
          totalPanel.add(title);

          JPanel marquePanel = new JPanel(new GridLayout(0, 1));

          JLabel marqueLabel = new JLabel("BMW");
          marqueLabel.setHorizontalAlignment(SwingConstants.LEFT);
          JTextField marqueField = new JTextField(15);
          marquePanel.add(marqueLabel);
          marquePanel.add(marqueField);

          totalPanel.add(marquePanel);

          JPanel modelePanel = new JPanel(new GridLayout(0, 1));

          JLabel modeleLabel = new JLabel("hahahah");
          modeleLabel.setHorizontalAlignment(SwingConstants.LEFT);
          JTextField modeleField = new JTextField(15);
          modelePanel.add(modeleLabel);
          modelePanel.add(modeleField);

          totalPanel.add(modelePanel);

          JPanel anneePanel = new JPanel(new GridLayout(0, 1));

          JLabel anneeLabel = new JLabel("2020");
          anneeLabel.setHorizontalAlignment(SwingConstants.LEFT);
          JTextField anneeField = new JTextField(15);
          anneePanel.add(anneeLabel);
          anneePanel.add(marqueField);

          totalPanel.add(anneePanel);

          JPanel kilometragePanel = new JPanel(new GridLayout(0, 1));

          JLabel kilometrageLabel = new JLabel("55.000");
          kilometrageLabel.setHorizontalAlignment(SwingConstants.LEFT);
          JTextField kilometrageField = new JTextField(15);
          kilometragePanel.add(kilometrageLabel);
          kilometragePanel.add(kilometrageField);

          totalPanel.add(kilometragePanel);

          add(totalPanel);
    }
}
