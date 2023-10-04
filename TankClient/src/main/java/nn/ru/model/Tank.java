package nn.ru.model;

import dto.DTOClient;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Tank {

    private final BufferedImage img1;
    private final BufferedImage img1act;
    BufferedImage img;

    float SHIFT = 30;

    float Y;
    float X;
    float deltaX;
    float deltaY;

    float A;

    private boolean alreadyClicked = false;
    private boolean isFocusable;

    Client client = new Client();


    public Tank(float x, float y) throws IOException {
        URL imgURL1 = getClass().getResource("/tank1.png");
        URL imgURL2 = getClass().getResource("/tank1act.png");
        img1 = ImageIO.read(imgURL1);
        img1act = ImageIO.read(imgURL2);
        img = img1;

        this.X = x;
        this.Y = y;

        this.setFocusable(false);

    }

    private void setFocusable(boolean b) {
        isFocusable = b;
    }

    public boolean isFocusable() {
        return isFocusable;
    }

    public void move(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Constants.backgroundColor);
        g2d.fillArc(
                (int) X - (int) SHIFT / 2,
                (int) Y - (int) SHIFT / 2,
                (int) Constants.TANK_WIDTH + (int) SHIFT,
                (int) Constants.TANK_HEIGHT + (int) SHIFT,
                (int) 0,
                (int) 360);

        if (X >= Constants.SCENA_WIDTH - Constants.TANK_WIDTH - 10) {
            this.deltaX = 0;
            this.deltaY = 0;
            X = Constants.SCENA_WIDTH - Constants.TANK_WIDTH - 15;
        }

        if (Y >= Constants.SCENA_HEIGTH - Constants.TANK_HEIGHT - 10) {
            this.deltaX = 0;
            this.deltaY = 0;
            Y = Constants.SCENA_HEIGTH - Constants.TANK_HEIGHT - 15;
        }

        if (X < Constants.SCENA_BORDER) {
            this.deltaX = 0;
            this.deltaY = 0;
            X = Constants.SCENA_BORDER + 15;
        }

        if (Y < Constants.SCENA_BORDER) {
            this.deltaX = 0;
            this.deltaY = 0;
            Y = Constants.SCENA_BORDER + 15;
        }

        X = X + this.deltaX;
        Y = Y + this.deltaY;

        g2d.fillArc(
                (int) X - (int) SHIFT / 2,
                (int) Y - (int) SHIFT / 2,
                (int) Constants.TANK_WIDTH + (int) SHIFT,
                (int) Constants.TANK_HEIGHT + (int) SHIFT,
                (int) 0,
                (int) 360);
        g2d.setColor(Constants.backgroundColor.brighter());
        g2d.rotate(Math.toRadians(this.A), X + Constants.TANK_WIDTH / 2, Y + Constants.TANK_HEIGHT / 2);
        g2d.drawImage((Image) img, (int) X, (int) Y, (int) Constants.TANK_WIDTH, (int) Constants.TANK_HEIGHT, null);

        g2d.rotate(Math.toRadians(-this.A), X + Constants.TANK_WIDTH / 2, Y + Constants.TANK_HEIGHT / 2);

    }

    //========================================================================
    public void keyEventPressed(KeyEvent e) throws Exception {
        DTOClient dtoOut = new DTOClient(this.A);
        dtoOut.keyCode = e.getKeyCode();

        DTOClient dtoIn = client.run(this, dtoOut);

        this.A = dtoIn.A;
        this.deltaX = dtoIn.deltaX;
        this.deltaY = dtoIn.deltaY;
    }

    public void keyEventReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.deltaX = 0;
            this.deltaY = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.deltaX = 0;
            this.deltaY = 0;
        }
    }

    public void mouseEventClicked(MouseEvent e) {
        if ((e.getPoint().getX() <= X + Constants.TANK_WIDTH)
                && (e.getPoint().getX() >= X)
                && (e.getPoint().getY() <= Y + Constants.TANK_HEIGHT)
                && (e.getPoint().getY() >= Y)
        ) {
            this.setFocusable(true);
            img = img1act;
            System.out.println("Угол равен: " + A);
            System.out.printf("CLICKED on x=%s  y=%s\n", e.getPoint().getX(), e.getPoint().getY());

            if (alreadyClicked) {
                alreadyClicked = false;
            } else {
                alreadyClicked = true;
            }
        } else {
            img = img1;
            this.setFocusable(false);
        }
    }


}
