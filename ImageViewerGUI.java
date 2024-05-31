import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton=new JButton("Back");
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath ="Users/ADMIN/Downloads";
    File file;

       JFileChooser fileChooser = new JFileChooser(filePath);
        int h = 900;
        int w = 1200;
        float brightenFactor = 1;



    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
    }

    public void mainPanel(){
        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));
        buttonsPanel.setBounds(150,80,400,100);
        JLabel label=new JLabel("Image Viewer");
        label.setBounds(300,20,100,100);
        mainPanel.add(label);
        selectFileButton=new JButton("SelectFile");
        showImageButton=new JButton("ShowImage");
        resizeButton=new JButton("Resize");
        grayscaleButton=new JButton("Grayscale");
        brightnessButton=new JButton("Brightness");
        closeButton=new JButton("Exit");


        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        resizeButton.addActionListener(this);
        brightnessButton.addActionListener(this);

        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);

    }

    public void resizePanel(){

        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(new GridLayout(3, 2));
        resizePanel.setSize(700,300);
        showResizeButton=new JButton("result");
        JLabel widthText=new JLabel("width :");
        resizePanel.add(widthText);
         widthTextField=new JTextField("");
        resizePanel.add(widthTextField);
        JLabel heightText=new JLabel("heigh :");
        resizePanel.add(heightText);
         heightTextField=new JTextField("");
        resizePanel.add(heightTextField);

        resizePanel.add(showResizeButton);
        backButton.addActionListener(this);
        resizePanel.add(backButton);
        showResizeButton.addActionListener(this);

        this.getContentPane().removeAll();
        this.add(resizePanel);
        this.repaint();
        this.revalidate();
    }
    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        backButton.addActionListener(this);
        brightnessPanel.setSize(700,300);
        JLabel f=new JLabel("Enter f(must be between 0 and 1)");
        f.setBounds(50,100,300,50);
        brightnessPanel.add(f);
        f.setBounds(100,120,300,50);
        brightnessTextField=new JTextField("");
        brightnessTextField.setBounds(335,123,200,50);
        brightnessPanel.add(brightnessTextField);
        backButton.setBounds(130,180,100,36);
        brightnessPanel.add(backButton);
        showBrightnessButton=new JButton("Result");
        showBrightnessButton.setBounds(397,180,100,38);
        brightnessPanel.add(showBrightnessButton);
        this.getContentPane().removeAll();
        this.repaint();
        this.revalidate();
        this.add(brightnessPanel);
        showBrightnessButton.addActionListener(this);
    }

    public void chooseFileImage(){
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(ImageViewerGUI.this);

          }

    public void showOriginalImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
       JLabel picturelabel=new JLabel();
       file = fileChooser.getSelectedFile();
        ImageIcon imageIcon=new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(1000,800,Image.SCALE_DEFAULT));
         picturelabel.setIcon(imageIcon);
         tempPanel.setLayout(null);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        picturelabel.setBounds(200,0,1700,900);
        tempPanel.add(picturelabel);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage() throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel pic=new JLabel();

        file=fileChooser.getSelectedFile();
        BufferedImage bufferedImage= ImageIO.read(file);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage image = op.filter(bufferedImage, null);
        ImageIcon imageIcon=new ImageIcon(image);
        pic.setIcon(imageIcon);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempPanel.add(pic);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showResizeImage(int w, int h) throws IOException {

        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel pic=new JLabel();
        file=fileChooser.getSelectedFile();
        BufferedImage bufferedImage= ImageIO.read(file);
        Image scaleImage = bufferedImage.getScaledInstance(w, h,Image.SCALE_DEFAULT);
        ImageIcon icon=new ImageIcon(scaleImage);
        pic.setIcon(icon);
        tempPanel.add(pic);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showBrightnessImage(float f) throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel pic=new JLabel();
        file = fileChooser.getSelectedFile();
        BufferedImage image = ImageIO.read(file);

        RescaleOp op = new RescaleOp(brightenFactor, 0, null);
        image = op.filter(image, image);
        ImageIcon icon=new ImageIcon(image);
        pic.setIcon(icon);


        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempPanel.add(pic);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            resizePanel();

        }else if(e.getSource()== showImageButton){
            showOriginalImage();

        }else if(e.getSource()==grayscaleButton){
            try {
                grayScaleImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()== showResizeButton){
             h=  Integer.parseInt(heightTextField.getText());
             w= Integer.parseInt(widthTextField.getText());
            try {
                showResizeImage(w,h);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()==brightnessButton){

            brightnessPanel();

        }else if(e.getSource()== showBrightnessButton){
            brightenFactor=Float.parseFloat(brightnessTextField.getText());
            try {
                showBrightnessImage(brightenFactor);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()== selectFileButton){
          chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}
