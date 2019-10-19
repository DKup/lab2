package bsu.rfe.java.group6.lab2.Ozhich.varC2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.*;

public class MainFrame extends JFrame {

    private static final int WIDTH = 510;
    private static final int HEIGHT = 300;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldMem;
    private JTextField textFieldResult;

    private JLabel labelImg = new JLabel();

    private ButtonGroup radioButtonsFormulaType = new ButtonGroup();
    private ButtonGroup radioButtonsMem = new ButtonGroup();

    private Box hBoxMemVariables;
    private Box hBoxFormulaType;

    private int formulaId = 1;
    private int memId = 1;

    private Double doubleMem1;
    private Double doubleMem2;
    private Double doubleMem3;

    private Image img1 = ImageIO.read(new File("src/img1.bmp"));
    private Image img2 = ImageIO.read(new File("src/img2.bmp"));

    private Double calculate1(Double x, Double y, Double z) {
        return pow(log(pow(1 + x, 2)) + cos(PI * pow(z, 3)), sin(y)) + pow(pow(E, pow(x, 2)) + cos(pow(E, z)) + sqrt(1 / y), 1 / x);
    }

    private Double calculate2(Double x, Double y, Double z) {
        return pow(cos(PI * pow(x, 3)) + log(pow(1 + y, 2)), 0.25) * (pow(E, pow(z, 2)) + sqrt(1 / x) + cos(pow(E, y)));
    }

    private void addRadioButtonFormula(String buttonName, final int buttonId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ev) {
                MainFrame.this.formulaId = buttonId;
                // вывод изображения в зависимости от выбранной кнопки
                if (MainFrame.this.formulaId == 1) {
                    labelImg.setIcon(new ImageIcon(img1));
                }
                if (MainFrame.this.formulaId == 2) {
                    labelImg.setIcon(new ImageIcon(img2));
                }
            }
        });
        radioButtonsFormulaType.add(button);
        hBoxFormulaType.add(button);
    }

    private void addRadioButtonMem(String buttonName, final int memId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.memId = memId;
            }
        });
        radioButtonsMem.add(button);
        hBoxMemVariables.add(button);
    }

    public MainFrame() throws IOException {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
        // box с изображением формулы
        labelImg.setIcon(new ImageIcon(img1));
        Box hBoxImg = Box.createHorizontalBox();
        hBoxImg.add(Box.createVerticalGlue());
        hBoxImg.add(labelImg);
        hBoxImg.add(Box.createVerticalGlue());
        // box с формулами
        hBoxFormulaType = Box.createHorizontalBox();
        hBoxFormulaType.add(Box.createHorizontalGlue());
        addRadioButtonFormula("Формула 1", 1);
        addRadioButtonFormula("Формула 2", 2);
        radioButtonsFormulaType.setSelected(radioButtonsFormulaType.getElements().nextElement().getModel(), true);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        hBoxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        // box для значенией x, y, z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hBoxVariables = Box.createHorizontalBox();
        hBoxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hBoxVariables.add(Box.createHorizontalGlue());
        hBoxVariables.add(labelForX);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldX);
        hBoxVariables.add(Box.createHorizontalStrut(30));
        hBoxVariables.add(labelForY);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldY);
        hBoxVariables.add(Box.createHorizontalStrut(30));
        hBoxVariables.add(labelForZ);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldZ);
        hBoxVariables.add(Box.createHorizontalGlue());
        // box выбора ячейки памяти
        hBoxMemVariables = Box.createHorizontalBox();
        hBoxMemVariables.add(Box.createHorizontalGlue());
        addRadioButtonMem("Переменная 1", 1);
        hBoxMemVariables.add(Box.createHorizontalStrut(15));
        addRadioButtonMem("Переменная 2", 2);
        hBoxMemVariables.add(Box.createHorizontalStrut(15));
        addRadioButtonMem("Переменная 3", 3);
        radioButtonsMem.setSelected(radioButtonsMem.getElements().nextElement().getModel(), true);
        hBoxMemVariables.add(Box.createHorizontalGlue());
        // box значения текущей ячейки памяти
        textFieldMem = new JTextField( "0", 20);
        Box hBoxMemValue = Box.createHorizontalBox();
        hBoxMemValue.add(Box.createHorizontalGlue());
        hBoxMemValue.add(textFieldMem);
        hBoxMemValue.add(Box.createHorizontalGlue());
        // box для результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 20);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.add(labelForResult);
        hBoxResult.add(Box.createHorizontalStrut(10));
        hBoxResult.add(textFieldResult);
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        // кнопки
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
            } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
        }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
                textFieldMem.setText("0");
                doubleMem1 = 0.0;
                doubleMem2 = 0.0;
                doubleMem3 = 0.0;
            }
        });
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(memId == 1) doubleMem1 = 0.0;
                if(memId == 2) doubleMem2 = 0.0;
                if(memId == 3) doubleMem3 = 0.0;
                textFieldMem.setText("0");
            }
        });
        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Double result = Double.parseDouble(textFieldResult.getText());
                double mem= 0.0;
                if (memId == 1) {
                    mem = doubleMem1 + result;
                }
                if (memId == 2) {
                    mem = doubleMem2 + result;
                }
                if (memId == 3) {
                    mem = doubleMem3 + result;
                }
                textFieldMem.setText(Double.toString(mem));
            }
        });
        // box для кнопок очистки, вычисления результата и управления памятью
        Box hBoxButtons = Box.createHorizontalBox();
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.add(buttonCalc);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(buttonReset);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(buttonMC);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(buttonMPlus);
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        // сборка всех box в один вертикальный box
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxImg);
        contentBox.add(hBoxFormulaType);
        contentBox.add(hBoxVariables);
        contentBox.add(hBoxMemValue);
        contentBox.add(hBoxMemVariables);
        contentBox.add(hBoxResult);
        contentBox.add(hBoxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws IOException {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
