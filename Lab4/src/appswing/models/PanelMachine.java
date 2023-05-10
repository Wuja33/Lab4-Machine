package appswing.models;

import javax.swing.*;
import java.awt.*;

public class PanelMachine extends JPanel {
    Machine machine;
    int l1 = 0;
    int l2 = 0;
    int h = 0;
    int d = 0;
    int s = 0;

    public PanelMachine (int panelMachineWidth,int panelMachineHeight)
    {
        this.setPreferredSize(new Dimension(panelMachineWidth,panelMachineHeight));
        this.setLayout(new GridBagLayout());
        machine = new Machine();
    }

    public void addParametersMachine(int l1, int l2, int h, int d)
    {
        this.l1 = l1;
        this.l2 = l2;
        this.h = h;
        this.d = d;
    }


    public void setD(int d) {
        this.d = d;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setL1(int l1) {
        this.l1 = l1;
    }

    public void setL2(int l2) {
        this.l2 = l2;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getS() {
        return s;
    }

    public Machine getMachine() {
        return machine;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        machine.draw(g,l1,l2,h,d,s);
    }
}
