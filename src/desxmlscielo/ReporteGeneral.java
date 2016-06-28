/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desxmlscielo;

/**
 *
 * @author usuario
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReporteGeneral {


    public static int getTotalArchivos(File rutaDominio){

       
       File[] lasRevistas = rutaDominio.listFiles();

       int contador=0;

       
       for(File revista:lasRevistas ){

           File[] arc = revista.listFiles();

           contador = contador + arc.length;

       }

        return contador;
    }

    public static int getTotalDeNumeros(File rutaDominio) throws SAXException, ParserConfigurationException, IOException{
         DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

         int contador=0;
         String nombreCompleto="";
    
         File[] lasRevistas = rutaDominio.listFiles();
         String auxissn = "";
         String auxnumvol = "";
         int contNum=0;

         int contFinal =0;
         String numvol = "";

         try{
         for(File revista:lasRevistas ){

           File[] arc = revista.listFiles();

             for (File file : arc) {

                 nombreCompleto = file.getName();
                // System.out.println("****** " + file.getName());
                 

                 Document doc = docBuilder.parse(new File(rutaDominio + "/" +  revista.getName()+ "/" + nombreCompleto));
                 doc.getDocumentElement().normalize();

                  NodeList listConnectorJournal = doc.getElementsByTagName("journal-meta");
                  NodeList listConnectorArticle = doc.getElementsByTagName("article-meta");




                        for (int m = 0; m < listConnectorJournal.getLength(); m++) {

                            Node journal = listConnectorJournal.item(m);
                            Element journalElement = (Element) journal;
                            Node id = journalElement.getElementsByTagName("journal-id").item(0);
                            Node title = journalElement.getElementsByTagName("journal-title").item(0);
                            Node issn = journalElement.getElementsByTagName("issn").item(0);

                            if (!auxissn.equals(issn.getTextContent())) {
                                auxissn = issn.getTextContent();

                                Node articulo = listConnectorArticle.item(0);
                                Element articleElement = (Element) articulo;
                                Node volumen = articleElement.getElementsByTagName("volume").item(0);
                                Node numero = articleElement.getElementsByTagName("numero").item(0);

                                auxnumvol = numero.getTextContent() + "_" + volumen.getTextContent();
                               // System.out.println("auxnumvol **** " + auxnumvol);
                                contNum ++ ;
                               // System.out.println("contador " + contNum);
                            } else {
                            }
                        }

                        //writer.write("\n");




                        for (int m = 0; m < listConnectorArticle.getLength(); m++) {

                           Node articulo = listConnectorArticle.item(m);
                           Element articleElement = (Element) articulo;

                           Node volumen = articleElement.getElementsByTagName("volume").item(0);
                           Node issue = articleElement.getElementsByTagName("issue").item(0);
                           Node numero = articleElement.getElementsByTagName("numero").item(0);
                           Node year = articleElement.getElementsByTagName("year").item(0);

                            if (numero.getTextContent() != null || volumen.getTextContent() != null) {
                                numvol = numero.getTextContent() + "_" + volumen.getTextContent();
                            }


                           // System.out.println("auxnumvol " + auxnumvol + " numvol " + numvol);
                           // System.out.println("numvol** " + numvol);

                            if (!auxnumvol.equals(numvol)) {

                              //   System.out.println("auxnumvol " + auxnumvol + " numvol " + numvol);
                                //System.out.println("diferente");
                                contFinal = contNum;
                                contador = 0;
                               // contNum=0;
                                contador++;
                                contNum++;

                                 auxnumvol = numvol;
                            }//end if
                            else {
                           
                                contador++;
                            
                            }
                        }
   
             }

       }

         } catch (SAXException saxe) {
                        System.err.println(saxe);
                     System.out.println("No se pudo leer el archivo "+ nombreCompleto + "\n");
         }
        

      return contNum;
    }


      public static int getTotalArchivosDaniados(File rutaDominio,String path) throws SAXException, ParserConfigurationException, IOException{
         DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();


          File f2 = new File(path + "\\log_revistas.txt");
          FileWriter writer2 = new FileWriter(f2);

         int contador=0;
         String nombreCompleto="";

         File[] lasRevistas = rutaDominio.listFiles();
         String auxissn = "";
         String auxnumvol = "";
         int contNum=0;
         int contDaniados=0;

         String numvol = "";

         try{
         for(File revista:lasRevistas ){

           File[] arc = revista.listFiles();

             for (File file : arc) {

                 nombreCompleto = file.getName();
                // System.out.println("****** " + file.getName());


                 Document doc = docBuilder.parse(new File(rutaDominio + "/" +  revista.getName()+ "/" + nombreCompleto));
                 doc.getDocumentElement().normalize();

                  NodeList listConnectorJournal = doc.getElementsByTagName("journal-meta");
                  NodeList listConnectorArticle = doc.getElementsByTagName("article-meta");




                        for (int m = 0; m < listConnectorJournal.getLength(); m++) {

                            Node journal = listConnectorJournal.item(m);
                            Element journalElement = (Element) journal;
                            Node id = journalElement.getElementsByTagName("journal-id").item(0);
                            Node title = journalElement.getElementsByTagName("journal-title").item(0);
                            Node issn = journalElement.getElementsByTagName("issn").item(0);

                            if (!auxissn.equals(issn.getTextContent())) {
                                auxissn = issn.getTextContent();

                                Node articulo = listConnectorArticle.item(0);
                                Element articleElement = (Element) articulo;
                                Node volumen = articleElement.getElementsByTagName("volume").item(0);
                                Node numero = articleElement.getElementsByTagName("numero").item(0);

                                auxnumvol = numero.getTextContent() + "_" + volumen.getTextContent();
                               // System.out.println("auxnumvol **** " + auxnumvol);
                                contNum ++ ;
                               // System.out.println("contador " + contNum);
                            } else {
                            }
                        }

                        //writer.write("\n");




                        for (int m = 0; m < listConnectorArticle.getLength(); m++) {

                           Node articulo = listConnectorArticle.item(m);
                           Element articleElement = (Element) articulo;

                           Node volumen = articleElement.getElementsByTagName("volume").item(0);
                           Node issue = articleElement.getElementsByTagName("issue").item(0);
                           Node numero = articleElement.getElementsByTagName("numero").item(0);
                           Node year = articleElement.getElementsByTagName("year").item(0);

                            if (numero.getTextContent() != null || volumen.getTextContent() != null) {
                                numvol = numero.getTextContent() + "_" + volumen.getTextContent();
                            }


                           // System.out.println("auxnumvol " + auxnumvol + " numvol " + numvol);
                           // System.out.println("numvol** " + numvol);

                            if (!auxnumvol.equals(numvol)) {

                                contador = 0;
                               // contNum=0;
                                contador++;
                                contNum++;

                                 auxnumvol = numvol;
                            }//end if
                            else {

                                contador++;

                            }
                        }

             }

       }

         } catch (SAXException saxe) {
                     System.err.println(saxe);
                     System.out.println("No se pudo leer el archivo "+ nombreCompleto + "\n");
                     writer2.write(nombreCompleto+ "\n");
                     contDaniados++;
         }


      return contDaniados;
    }
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        int cont = 0;
        String path ="C:\\Users\\sistemas14\\Desktop\\DescargaScielo";
        String fullPath="";

        fullPath = path + "\\General";

        System.out.println("Full " + fullPath);

        File directorio = new File(fullPath);
        File[] losDirectorios = directorio.listFiles();
        System.out.println(losDirectorios.length);
        File f1 = new File(path +"\\general.csv");
        FileWriter writer = new FileWriter(f1);
        int TotalArticulos=0;
        int TotalNumeros=0;
        int TotalDaniados=0;

        writer.write(", , , ,Tot Rev,TotNum, TotArt, Arc Dañ\n");

        for (File dir : losDirectorios) {


            TotalArticulos= 0;
            System.out.println(" ------>>>>>>>>>>>>>     directorio  " + dir.getName() + " ---------------------- " + cont++);
            
            File losArchivos =new File(fullPath+"\\"+dir.getName());
            TotalArticulos  = getTotalArchivos(losArchivos);
            TotalNumeros = getTotalDeNumeros(losArchivos);
            TotalDaniados = getTotalArchivosDaniados(losArchivos, path);
            File[] archivos = dir.listFiles();
            writer.write(", , ,"+dir.getName()+","+archivos.length +","+TotalNumeros+" ,"+TotalArticulos+","+TotalDaniados+"\n");

        }

        writer.close();

    }
}
