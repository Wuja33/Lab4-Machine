package appswing.main;

import appswing.models.Graph;
import appswing.models.PanelMachine;
import appswing.tools.AppTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {

    //KONTENERY
    JPanel panelTexts;
    PanelMachine panelMachine;
    JPanel panelGraphs;
    Graph graphY;
    Graph graphX;
    JPanel panelNorth;
    JPanel panelSouth;
    JTextField field_l1;
    JTextField field_l2;
    JTextField field_s;
    JTextField field_d;
    JTextField field_h;
    JButton button_l1;
    JButton button_l2;
    JButton button_s;
    JButton button_d;
    JButton button_h;
    JButton button_start;
    JLabel label_l1;
    JLabel label_l2;
    JLabel label_s;
    JLabel label_d;
    JLabel label_h;
    //WIELKOŚCI KONTENERÓW
    public static final int panelTextHeight = 600;
    public static final int panelTextWidth = 200;
    public static final int panelMachineHeight = 400;
    public static final int panelMachineWidth = 600;
    public static final int panelGraphsHeight = 300;
    public static final int panelGraphsWidth = 300;
    public static final int panelNorthHeight = 50;
    public static final int panelNorthWidth = panelTextWidth+panelGraphsWidth+panelMachineWidth;
    public static final int panelSouthHeight = 50;
    public static final int panelSouthWidth = panelTextWidth+panelGraphsWidth+panelMachineWidth;
    public static final int buttonHeight = 30;
    public static final int buttonWidth = 60;

    //ZMIENNE PRZECHOWUJĄCE WARTOŚCI PARAMETRÓW
    private int l1 = 0;
    private int l2 = 0;
    private int s = 0;
    private int d = 0;
    private int h = 0;

    boolean machineStatus = true;

    //ZMIENNE OGRANICZAJĄCE PARAMETRY
    private int min_l1 = 0;
    private final int max_l1 = panelMachineHeight/2;
    private int min_l2 = 0;
    private int max_l2 = panelMachineWidth/2;
    private int min_s = 0;
    private int max_s = 0;
    private int min_d = 0;
    private int max_d = panelMachineWidth/2;
    private int min_h = 0;
    private int max_h = panelMachineHeight/2;

    public MainPanel()
    {
        // PANEL DO PARAMETRÓW
        panelTexts = new JPanel();
        panelTexts.setPreferredSize(new Dimension(panelTextWidth,panelTextHeight));
        panelTexts.setLayout(new GridLayout(6,1,40,70));

        //PANEL DO MASZYNY
        panelMachine = new PanelMachine(panelMachineWidth,panelMachineHeight);

        //PANEL DO WYKRESU
        panelGraphs = new JPanel();
        panelGraphs.setLayout(new GridLayout(2,1,20,0));
        panelGraphs.setPreferredSize(new Dimension(panelGraphsWidth,panelGraphsHeight));

        //PANEL GÓRNY
        panelNorth = new JPanel();
        panelNorth.setPreferredSize(new Dimension(panelNorthWidth,panelNorthHeight));

        //PANEL DOLNY
        panelSouth = new JPanel();
        panelSouth.setPreferredSize(new Dimension(panelSouthWidth,panelSouthHeight));

        //POLA TEKSTOWE
        field_l1 = new JTextField();
        field_l2 = new JTextField();
        field_s = new JTextField();
        field_d = new JTextField();
        field_h = new JTextField();

        //PRZYCISKI DO PÓL TEKSTOWYCH
        button_l1 = new JButton("OK");
        button_l2 = new JButton("OK");
        button_s = new JButton("OK");
        button_d = new JButton("OK");
        button_h = new JButton("OK");

        //PRZYCISK STARTU
        button_start = new JButton("START");

        button_l1.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        button_l2.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        button_d.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        button_s.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        button_h.setPreferredSize(new Dimension(buttonWidth,buttonHeight));

        button_l1.addActionListener(this);
        button_l2.addActionListener(this);
        button_d.addActionListener(this);
        button_s.addActionListener(this);
        button_h.addActionListener(this);
        button_start.addActionListener(this);

        //ETYKIETY PÓL TEKSTOWYCH
        label_l1 = new JLabel("L1", SwingConstants.CENTER);
        label_l2 = new JLabel("L2",SwingConstants.CENTER);
        label_s = new JLabel("S",SwingConstants.CENTER);
        label_d = new JLabel("D",SwingConstants.CENTER);
        label_h = new JLabel("H",SwingConstants.CENTER);
        refreshLabels();

        //USTAWIANIE GŁÓWNEGO PANELU
        this.setLayout(new BorderLayout());
        this.add(panelNorth,BorderLayout.NORTH);
        this.add(panelSouth,BorderLayout.SOUTH);
        this.add(panelTexts,BorderLayout.WEST);
        this.add(panelMachine,BorderLayout.CENTER);
        this.add(panelGraphs,BorderLayout.EAST);

        //dodawanie panelów tekstowych, przycisków i etykiet do panelu bocznego z parametrami
        panelTexts.add(AppTools.createPanel(label_l1,field_l1,button_l1));
        panelTexts.add(AppTools.createPanel(label_s,field_s,button_s));
        panelTexts.add(AppTools.createPanel(label_d,field_d,button_d));
        panelTexts.add(AppTools.createPanel(label_h,field_h,button_h));
        panelTexts.add(AppTools.createPanel(label_l2,field_l2,button_l2));
        panelTexts.add(button_start);

        //tworzenie i dodawanie wykresów do panelu bocznego z wykresami
        graphY = new Graph("Velocity Y of point P","Moves","Velocity","Velocity Y (unit/move * 100)");
        graphX = new Graph("Velocity X of point P","Moves","Velocity","Velocity X (unit/move * 100)");
        panelGraphs.add(graphY);
        panelGraphs.add(graphX);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //DZIAŁANIE PRZYCISKÓW
        if(e.getSource()==button_l1)
        {
            if (AppTools.getValueFromText(field_l1.getText(),min_l1,max_l1) != 0) //pobrana liczba nie może byc zerem
                l1 = AppTools.getValueFromText(field_l1.getText(),min_l1,max_l1);
            refreshLimits(); //odświeżenie limitów układu

            panelMachine.setL1(l1);
            panelMachine.repaint();
        }
        else if(e.getSource()==button_l2)
        {
            if (AppTools.getValueFromText(field_l2.getText(),min_l2,max_l2) != 0) //pobrana liczba nie może byc zerem
                l2 = AppTools.getValueFromText(field_l2.getText(),min_l2,max_l2);
            refreshLimits(); //odświeżenie limitów układu
            panelMachine.setL2(l2);
            panelMachine.repaint();
        }
        else if(e.getSource()==button_s)
        {
            if (AppTools.getValueFromText(field_s.getText(),min_s,max_s) != 0) //pobrana liczba nie może byc zerem
                s = AppTools.getValueFromText(field_s.getText(),min_s,max_s);
            refreshLimits(); //odświeżenie limitów układu
        }
        else if(e.getSource()==button_d)
        {
            if (AppTools.getValueFromText(field_d.getText(),min_d,max_d) != 0) //pobrana liczba nie może byc zerem
                d = AppTools.getValueFromText(field_d.getText(),min_d,max_d);
            refreshLimits(); //odświeżenie limitów układu
            panelMachine.setD(d);
            panelMachine.repaint();
        }
        else if(e.getSource()==button_h)
        {
            if (AppTools.getValueFromText(field_h.getText(),min_h,max_h) != 0) //pobrana liczba nie może byc zerem
                h = AppTools.getValueFromText(field_h.getText(),min_h,max_h);
            refreshLimits(); //odświeżenie limitów układu
            panelMachine.setH(h);
            panelMachine.repaint();
        }
        else if(e.getSource()==button_start)
        {
            if(this.checkParameters())
            {
                this.startMachine(button_start);
            }
            else
              JOptionPane.showMessageDialog(null,"Niepoprawne wartości parametrów!","ERROR",JOptionPane.WARNING_MESSAGE);
        }
    }


    public void refreshLimits()
    {
        //wybierz mi minimalne l2, w zależności od parametrów
        min_l2 = Math.max((int) Math.sqrt(Math.pow(h - l1 + s, 2) + Math.pow(d, 2)), (int) Math.sqrt(Math.pow(h - l1, 2) + Math.pow(d, 2)));
        max_s = l1;
        //max_h = 0;
        refreshLabels();
    }
    public boolean checkParameters()
    {
        if ((l1>min_l1&&l1<max_l1)&&
                (l2>min_l2&&l2<max_l2)&&
                (d>min_d&&d<max_d)&&
                (h>min_h&&h<max_h)&&
                (s>min_s&&s<max_s))
        {
            panelMachine.addParametersMachine(l1,l2,h,d);
            return true;
        }
        else
            return false;
    }
    public void refreshLabels()
    {
        label_l1.setText("L1: "+l1+"       "+min_l1+"<L1<"+max_l1);
        label_l2.setText("L2: "+l2+"       "+min_l2+"<L2<"+max_l2);
        label_s.setText("S: "+s+"       "+min_s+"<S<"+max_s);
        label_d.setText("D: "+d+"       "+min_d+"<D<"+max_d);
        label_h.setText("H: "+h+"       "+min_h+"<H<"+max_h);
    }
    public void startMachine(JButton button)
    {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                //Usuwanie możliwości użycia przycisku podczas symulacji
                button_start.setEnabled(false);

                graphX.clear();
                graphY.clear();
                double xPrevious = panelMachine.getMachine().getX2();
                double yPrevious = panelMachine.getMachine().getY2();
                double x;
                double y;
                double velocityX;
                double velocityY;
                for (int i = 0; i <= s; i++) {
                    panelMachine.setS(i);
                    panelMachine.repaint();

                    x = panelMachine.getMachine().getX2();
                    y = panelMachine.getMachine().getY2();

                    velocityX = x - xPrevious;
                    velocityY = y - yPrevious;
                    graphX.add(i,velocityX*100);
                    graphY.add(i,velocityY*100);

                    xPrevious = x;
                    yPrevious = y;
                    try {
                        Thread.sleep(50);
                    }
                    catch(Exception e)
                    {}
                }
                for (int i = s; i >= 0; i--) {
                    panelMachine.setS(i);
                    panelMachine.repaint();

                    velocityX = panelMachine.getMachine().getX2()-xPrevious;
                    velocityY = panelMachine.getMachine().getY2()-yPrevious;

                    xPrevious = panelMachine.getMachine().getX2();
                    yPrevious = panelMachine.getMachine().getY2();

                    graphX.add(s + s-i,velocityX*100);
                    graphY.add(s + s-i,velocityY*100);

                    try {
                        Thread.sleep(50);
                    }
                    catch(Exception e)
                    {}
                }
                //Wznawianie możliwości użycia przycisku
                button_start.setEnabled(true);
            }
        });
        thread.start();
    }
}
