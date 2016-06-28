/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desxmlscielo;
/**
 *
 * @author lgm
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
public class xmlRepositorio {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        int i = 1;
        int m = 0;
        String vol = "";
        int numXvol = 0;
        int sinLeer = 0;
        int numArchivos = 0;
        String dirAux = "";
        int totArt = 0;
        // normalize text representation
        File directorio = new File("C:\\home\\xmls");
        File[] losDirectorios = directorio.listFiles();

        File f2 = new File("C:\\home\\logRevistas.txt");
        FileWriter writer2 = new FileWriter(f2);

        for (File dir : losDirectorios) {
            System.out.println(" ------>>>>>>>>>>>>>     directorio  " + dir.getName());
            File f1 = new File("C:\\home\\" + dir.getName() + ".csv");
            System.out.println("String de creacion de F1  C:\\home\\" + dir.getName() + ".csv");
            FileWriter writer = new FileWriter(f1);
            String sd = "C:\\home\\xmls\\" + dir.getName();
            File SubDirectorio = new File(sd);
            File[] losSubDirectorios = SubDirectorio.listFiles();

            for (File subdir : losSubDirectorios) {
                //System.out.println(subdir.getName());
                //String ssd = sd+subdir.getName();
                File[] archivos = subdir.listFiles();
                String nombreRedXml = "";
                String nombreRedSif = "";
                i = 1;
                numArchivos = 0;
                for (File file : archivos) {
                    //System.out.println("********************" + file.getName());
                    nombreRedXml = file.getName();
                    nombreRedSif = nombreRedXml.substring(0, nombreRedXml.indexOf("."));
                    String registro = null;
                    registro = sd + "\\" + subdir.getName() + "\\" + nombreRedXml;
                    //System.out.println("Registro     " + registro);
                    File tmp = new File(sd + "\\" + subdir.getName() + "\\" + nombreRedXml);

                    try {
                        numArchivos++;
                        Document doc = docBuilder.parse(tmp);
                        doc.getDocumentElement().normalize();
                        i++;
                        //System.out.println("leyendo (" + i++ + "\\" + archivos.length + ") " + nombreRedSif);
                        NodeList listConnectorJournal = doc.getElementsByTagName("journal-meta");
                        NodeList listConnectorArticle = doc.getElementsByTagName("article-meta");
                        //System.out.println("Articulos  " + listConnectorArticle.getLength());
                        m = 0;
                        Node journal = listConnectorJournal.item(m);
                        Element journalElement = (Element) journal;
                        Node id = journalElement.getElementsByTagName("journal-id").item(0);
                        Node title = journalElement.getElementsByTagName("journal-title").item(0);
                        Node issn = journalElement.getElementsByTagName("issn").item(0);

                        Node titulorev = listConnectorArticle.item(m);
                        Element articleElement = (Element) titulorev;
                        Node volume = articleElement.getElementsByTagName("volume").item(0);
                        Node year = articleElement.getElementsByTagName("year").item(0);
                        Node numero = articleElement.getElementsByTagName("numero").item(0);

                        if (volume.getTextContent().equals(vol) || vol.equals("")) {
                            numXvol++;
                            //System.out.println(volume.getTextContent() + "   " + numXvol + " Archivo  " + registro);
                        } else if (!volume.getTextContent().equals(vol)) {
                            writer.write(numero.getTextContent() + "," + vol + "," + year.getTextContent() + "," + numXvol + "\n");
                            //System.out.println(numero.getTextContent() + "," + vol + "," + year.getTextContent() + "," + numXvol);
                            //writer.write("***** i ***"+i+"   Volumen, " + vol + ",Numero," + numero.getTextContent() + ",Anio," + year.getTextContent() + "," + vol + ",Articulos \n");
                            //System.out.println("Archivo leido " + sd + "\\" + subdir.getName() + "\\" + nombreRedXml);
                            // System.out.println("***** i ***" + i + "   Volumen " + vol + "    Numero de volumenes " + numXvol + "   issn  " + id.getTextContent());
                            //System.out.println(" ------>>>>>>>>>>>>>     directorio  " + dir.getName());
                            vol = volume.getTextContent();
                            if (!dir.getName().equals(dirAux)) {
                                //ver si se puede vaidar aqui
                                System.out.println("Cambio el nombre del directorio");
                                System.out.println("anterior " + dirAux);
                                System.out.println("nuevo " + dir.getName());
                                System.out.println(sd + "\n");                                
                            }
                            totArt += numXvol;
                            numXvol = 1;
                            //System.out.println(volume.getTextContent() + "   " + numXvol + " Archivo  " + registro);
                        }
                        if (i == 2) {
                            if (totArt != 0) {

                                writer.write(",,," + totArt + "," + sinLeer+"\n");
                                totArt = 0;
                                sinLeer = 0;
                            }
                            writer.write("\nRevista," + title.getTextContent() + "\n");
                            writer.write("ISSN," + issn.getTextContent() + "\n");
                            writer.write("Numero,Volumen,Anio,Total de Articulos,Arch daniados\n");
                            /*System.out.println("\nRevista," + title.getTextContent());
                            System.out.println("ISSN," + issn.getTextContent());
                            System.out.println("Numero,Volumen,Anio,Total de Articulos,Arch daniados");
                             * 
                             */
                        }
                        vol = volume.getTextContent();

                    } catch (SAXException saxe) {
                       
                        writer2.write(nombreRedXml + "\n");
                        sinLeer++;
                        System.err.println(saxe);
                    } catch (IOException ioe) {
                        //System.err.println(ioe);
                        writer2.write(nombreRedXml + "\n");
                        sinLeer++;
                        System.err.println(ioe);
                    } catch (Exception e) {
                       // System.err.println(e);
                        writer2.write(nombreRedXml + "\n");
                        sinLeer++;
                        System.err.println(e);
                    }
                    //int tmp = 0;
                    // writer.write("\n");
                    dirAux = dir.getName();
                }

            }

            writer.close();
        }
        writer2.close();

    }
}
