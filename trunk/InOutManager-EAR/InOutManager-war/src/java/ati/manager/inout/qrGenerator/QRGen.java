/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.qrGenerator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author gustavo
 */
public class QRGen {

    private final String FORMATO_IMAGEN = "gif";
    private final String RUTA_IMAGEN = System.getProperty("user.home") + "/qrZxing.gif";
    private final int ancho = 300;
    private final int alto = 300;
    private String datos = "";
    private static QRGen instance;


    private QRGen(){
        
    }

    public static QRGen getInstance(){
        if(instance == null){
            instance = new QRGen();
        }
        return instance;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public void generate(String datos){

        this.datos = datos;

        FileOutputStream qrCode = null;
            try {
                BitMatrix bm;
                Writer writer = new QRCodeWriter();
                bm = writer.encode(datos, BarcodeFormat.QR_CODE, ancho, alto);
                BufferedImage image = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                for (int y = 0; y < ancho; y++) {
                    for (int x = 0; x < alto; x++) {
                        int grayValue = (bm.get(x, y) ? 1 : 0) & 0xff;
                        image.setRGB(x, y, grayValue == 0 ? 0 : 0xFFFFFF);
                    }
                }
                image = invertirColores(image);
                qrCode = new FileOutputStream(RUTA_IMAGEN);
                ImageIO.write(image, FORMATO_IMAGEN, qrCode);
            } catch (IOException ex) {
                Logger.getLogger(QRGenMain.class.getName()).log(Level.SEVERE, null, ex);

            } catch (WriterException ex) {
                Logger.getLogger(QRGenMain.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    qrCode.close();
                } catch (IOException ex) {
                    Logger.getLogger(QRGenMain.class.getName()).log(Level.SEVERE, null, ex);
                }

            }


            Desktop d = Desktop.getDesktop();
            try {
                d.open(new File(RUTA_IMAGEN));
                System.out.println(RUTA_IMAGEN);
            } catch (IOException ex) {
                Logger.getLogger(QRGenMain.class.getName()).log(Level.SEVERE, null, ex);
            }

    }


    private BufferedImage invertirColores(BufferedImage imagen) {
        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < alto; y++) {
                int rgb = imagen.getRGB(x, y);
                if (rgb == -16777216) {
                    imagen.setRGB(x, y, -1);
                } else {
                    imagen.setRGB(x, y, -16777216);
                }
            }
        }
        return imagen;
    }

}
