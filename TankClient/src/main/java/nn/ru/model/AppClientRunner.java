package nn.ru.model;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static nn.ru.model.Constants.*;

public class AppClientRunner {
    public static void main(String[] args) throws InterruptedException, IOException {


        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd = gs[0];

        Scena scena1 = new Scena();
        JFrame frame = new JFrame(gd.getDefaultConfiguration());

        frame.setTitle("TANKS MODEL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCENA_WIDTH + SCENA_BORDER * 2, SCENA_HEIGTH + SCENA_BORDER * 2);
        frame.setBackground(backgroundColor);
        frame.setResizable(true);

        frame.add(scena1);
        frame.setVisible(true);

        SimulatorThread simulatorThread = new SimulatorThread("SIMULATOR", (KeyListener) scena1);
        simulatorThread.start();
    }
}
