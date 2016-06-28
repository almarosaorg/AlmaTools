/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desxmlscielo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Josimar
 * 
 
 
 
 
 s
 s
 s
 s
 s*/
public class Main {

    private static BufferedReader in;
    private static String inputLine;
    private static String inputText = "";
    private static BufferedReader in_rev_num;
    private static String inputLine_rev_num;
    private static String inputText_rev_num = "";
    private static BufferedReader in_xml;
    private static String inputLine_xml;
    private static String inputText_xml = "";
    private static BufferedReader in_descargar;
    private static String inputText_descargar;
    private static String inputLine_descargar;
    private static BufferedReader br;
    private static String sTexto;
    private static String issn;
    private static BufferedReader br_issn;

    public static void main(String[] args) {
        try {
            String urls[] = new String[13];
            String pais[] = new String[13];
            //final String buscar[] = {"1645-0086","0871-9187","1646-107X","1645-3794","0873-6561","0874-0283","1645-4464","0872-3419"	,	"","","2182-2972","2182-7400","2182-2891","2182-2882","","2182-9691"};
            final String buscar[] = {"0327-1676","0327-5566","0325-8203","0327-1471","0325-1918","0329-3807","0329-2665","1514-6006","1667-9261","0328-6169","1666-485X","1151-209X","1667-4162","1669-2381","","0328-1205","1668-0030","0328-9702","1515-2413","1666-2105","0327-5841","0325-1217","0327-3776","1515-6125","1514-8327","1666-0714","","1668-5628","1666-4124","1666-244X","0326-1301","1666-0579","","1669-7634","0327-4063","0329-5885","1666-6186","0327-5094","0327-5477","0325-4194","0524-9767","1514-3333","0325-1195","1514-9935","0327-5752","1515-1115","","0556-5960","1514-5158","0326-8373","1515-9485","1668-0227","1669-8568","1669-7456","0328-8773","0327-6147","1515-6877","0328-3445","1666-1508","1666-7883","0326-9795","","","0325-0725","1514-0105","1668-8090","1851-1716","1668-7027","1668-8104","1853-1784","1851-4669","1851-2879","1669-2721","1669-3248","1669-5704","1668-723X","1851-9601","1669-9041","1851-8265","1515-5994","1851-779X","1850-0013","","1851-1694","1850-373X","1851-1732","1851-9628","1850-275X","1852-9615","1851-1740","1853-984X","1850-6666","2314-2634","1851-3727","1852-7310","1851-9636","1851-3123","1853-9912","1668-8708","2313-951X","1851-1686","1853-3655","1850-2628","1667-782X","1853-6379","1850-2563","1851-1724","1850-2717","1853-3175","1851-3751","1852-4478","1851-9504","2314-1549","1853-0400","1852-4265","2313-9927","1853-3523","2313-9277","1853-1296","1853-001X","1852-4508","1851-8893","1852-8562","1852-0499","1853-1970","1853-7081","1514-6871","1995-7785","1852-7353","2314-2561"};
            final List<String> lista = Arrays.asList(buscar);  
            
//=======================================URL donde se encuentran todas las revistas online
            int url_pais = 5;
            String carpeta="descargarXML";

            /**/ pais[0] = "Argentina";
            /**/ urls[0] = "http://www.scielo.org.ar/scielo.php?script=sci_alphabetic&lng=en&nrm=iso";

            pais[1] = "Brasil";
            urls[1] = "http://www.scielo.br/scielo.php?script=sci_alphabetic&lng=en&nrm=iso";

            pais[2] = "Chile";
            urls[2] = "http://www.scielo.cl/scielo.php?script=sci_alphabetic&lng=es&nrm=iso";

            pais[3] = "Colombia";
            urls[3] = "http://www.scielo.org.co/scielo.php?script=sci_alphabetic&lng=en&nrm=iso";

            pais[4] = "Cuba";
            urls[4] = "http://scielo.sld.cu/scielo.php?script=sci_alphabetic&lng=es&nrm=iso";

            pais[5] = "Espana";
            urls[5] = "http://scielo.isciii.es/scielo.php?script=sci_alphabetic&lng=es&nrm=iso";

            pais[6] = "Mexico";
            urls[6] = "http://www.scielo.org.mx/scielo.php?script=sci_alphabetic&lng=es&nrm=iso";

            pais[7] = "Portugal";
            urls[7] = "http://www.scielo.mect.pt/scielo.php?script=sci_alphabetic&lng=pt&nrm=i";

            pais[8] = "Venezuela";
                urls[8] = "http://www.scielo.org.ve/scielo.php?script=sci_alphabetic&lng=es&nrm=iso";

            pais[9] = "Costa_Rica";
            urls[9] = "http://www.scielo.sa.cr/scielo.php?script=sci_alphabetic&lng=pt&nrm=iso";
            
            pais[10] = "Bolivia";
            urls[10] = "http://www.scielo.org.bo/scielo.php?script=sci_alphabetic&lng=pt&nrm=iso";
            
            pais[11] = "Peru";
            urls[11] = "http://www.scielo.org.pe/scielo.php?script=sci_alphabetic&lng=es&nrm=iso";

            pais[12] = "Uruguay";
            urls[12] = "http://www.scielo.edu.uy/scielo.php?script=sci_alphabetic&lng=es&nrm=iso";

//                br = new BufferedReader(new InputStreamReader(System.in));
            boolean selectPais = false;
            int a = 0;
            do {
                System.out.print("Escribe el numero del pais para descargar \n1.- Argentina\n2.- Brasil\n3.- Chile\n4.- Colombia\n5.- Cuba\n6.- Espana\n7.- Mexico\n8.- Portugal\n9.- Venezuela\n10.- Costa_Rica\n11.- Bolivia\n12.- Peru:\n13.- Uruguay:\n\n");
                br = new BufferedReader(new InputStreamReader(System.in));
                sTexto = br.readLine();

                try {
                    url_pais = Integer.parseInt(sTexto) - 1;
                    if (url_pais >= 0) {
                        selectPais = true;
                    } else {
                        selectPais = false;
                    }
                } catch (Exception r) {
                    selectPais = false;
                }

                System.out.println("\n\nObteniendo revistas... \n\n");

            } while (!selectPais);
            ;

            URL url = new URL(urls[url_pais]);

//=======================================Opteniendo contenido de la pagina
            try {
                in = new BufferedReader(new InputStreamReader(url.openStream()));
            } catch (Throwable t) {
                System.out.print("ERROR-->" + t);
            }

            while ((inputLine = in.readLine()) != null) {
                inputText = inputText + inputLine;
            }
//=======================================Eliminando codigo innecesario, dejando solo link y nombre de revista
            String rev = "", todas_rev = "";
            int cont_rev = 1;
            if (pais[8].equals("Venezuela")) {
                inputText = inputText.substring(inputText.indexOf("<font class=\"linkado\">"), inputText.lastIndexOf("</font>") + 7);

                while (inputText.indexOf("<font class=\"linkado\">") != -1) {
                    inputText = inputText.substring(inputText.indexOf("<font class=\"linkado\">") + 22, inputText.length());
                    rev = inputText.substring(0, inputText.indexOf("</a>"));
                    inputText = inputText.substring(rev.length(), inputText.length());
                    todas_rev = todas_rev + rev + "\n\n\n";
                    System.out.println(cont_rev + ".- " + rev.substring(rev.indexOf("pid=") + 4, rev.indexOf("pid=") + 13) + " " + rev.substring(rev.indexOf(">") + 1, rev.length()));
                    cont_rev++;

                }


            } else {
                inputText = inputText.replaceAll("<font class=\"linkado\">", "");

                inputText = inputText.substring(inputText.indexOf("<ul>"), inputText.lastIndexOf("</ul>"));

                while (inputText.indexOf("<li>") != -1) {
                    inputText = inputText.substring(inputText.indexOf("<li>") + 4, inputText.length());
                    rev = inputText.substring(4, inputText.indexOf("</li>"));
                    rev = inputText.substring(0, inputText.indexOf("</a>"));
                    inputText = inputText.substring(rev.length(), inputText.length());

                    todas_rev = todas_rev + rev + "\n\n\n";
                    System.out.println(cont_rev + ".- " + rev.substring(rev.indexOf(">") + 1, rev.length()));
                    cont_rev++;

                }

            }

            //remplasando sci_serial por sci_issues para obtener pagina donde se encuentran los numeros, y &amp; por &
            todas_rev = todas_rev.replaceAll("sci_serial", "sci_issues").replaceAll("&amp;", "&").replaceAll("scielo.php/script_sci_serial/pid_", "scielo.php?script=sci_issues&pid=").replaceAll("/lng_es/nrm_", "&lng=es&nrm=");


            //separando revistas
            String revistas[] = todas_rev.split("<a");



            System.out.print("\nEscribe el ISSN(s) que se van a descargar separandolos con una ',' ejemplo: 1234-5678 ó 1234-5678,8765-4321\n o escribe 'todo' para descargar todas las revistas\n\n");
            br_issn = new BufferedReader(new InputStreamReader(System.in));
            issn = br_issn.readLine();
            // System.out.println(issn);


            String issns[] = null;
            if (issn.indexOf(",") != -1) {
                issns = issn.split(",");
            } else {
                issn = issn + ",";
                issns = issn.split(",");
            }


            for (int u = 0; u < issns.length; u++) {

                for (int i = 1; i < revistas.length; i++) {
                    String revista=revistas[i].substring(revistas[i].indexOf(">")+1).trim();
                    System.out.println("revista " + revistas[i]);
                    
                if(lista.contains(revistas[i].substring(revistas[i].indexOf("pid=") + 4, revistas[i].indexOf("pid=") + 13)))  
                  {    
                    if (revistas[i].indexOf(issns[u]) > 0 || issns[u].equalsIgnoreCase("todo")) {
                        //System.out.println(revistas[i]);

                        String urlnumeros = revistas[i].substring(revistas[i].indexOf("href") + 6, revistas[i].indexOf(">") - 2);
                        System.out.println(urlnumeros + "\n\n");

                        URL url_rev_num = new URL(urlnumeros);
                        //System.out.print(urlnumeros);

//=======================================Opteniendo contenido de la pagina
                        try {
                            in_rev_num = new BufferedReader(new InputStreamReader(url_rev_num.openStream()));

                        } catch (Throwable t) {
                        }
                        inputText_rev_num = "";

                        while ((inputLine_rev_num = in_rev_num.readLine()) != null) {
                            inputText_rev_num = inputText_rev_num + inputLine_rev_num;
                        }


                        if (inputText_rev_num.indexOf("Vol.") != -1) {
                            inputText_rev_num = inputText_rev_num.substring(inputText_rev_num.indexOf("Vol.") + 4, inputText_rev_num.length());

                        }


                        inputText_rev_num = inputText_rev_num.substring(0, inputText_rev_num.indexOf("TABLE"));

                        //separa filas
                        String rev2 = "", todas_rev2 = "", aux_pid = "";
                        while (inputText_rev_num.indexOf("<TR>") != -1) {
                            inputText_rev_num = inputText_rev_num.substring(inputText_rev_num.indexOf("<TR>") + 4, inputText_rev_num.length());


                            rev2 = inputText_rev_num.substring(0, inputText_rev_num.indexOf("</TR>"));

                            //Separa columnas

                            String datos_columnas = "";
                            int con_col = 0;
                            while (rev2.indexOf("<") != -1) {
                                if (rev2.indexOf("<") >= 0) {

                                    // System.out.print("\n\n"+rev2+"\n\n");

                                    String link = "";
                                    try {
                                        link = rev2.substring(rev2.indexOf("href"), rev2.indexOf(">"));
                                    } catch (Exception e) {
                                    }
                                    //si se quita solo quedan los links
                                    datos_columnas += rev2.substring(0, rev2.indexOf("<"));
                                    if (datos_columnas.trim().length() > 1) {
                                        datos_columnas += link + "\n ";
                                    }
                                }

                                rev2 = rev2.substring(rev2.indexOf(">") + 1, rev2.length());

                                con_col++;


                            }
                            datos_columnas = datos_columnas.replaceAll("&amp;", "&");
                            todas_rev2 += datos_columnas;
                            inputText_rev_num = inputText_rev_num.substring(rev2.length(), inputText_rev_num.length());
                        }



                        todas_rev2 = todas_rev2.replaceAll("\n", "");

                        String pid_xml = "", url_xml = "";
                        while (todas_rev2.indexOf("pid=") != -1) {

                            // System.out.print("\n------");
                            //System.out.print("\n"+todas_rev2.substring(todas_rev2.indexOf("pid="),todas_rev2.length())+"\n");
                            try {
                                pid_xml = todas_rev2.substring(todas_rev2.indexOf("pid=") + 4, todas_rev2.indexOf("pid=") + 21);

                                url_xml = todas_rev2.substring(todas_rev2.indexOf("http"), todas_rev2.length());
                                url_xml = url_xml.substring(0, url_xml.indexOf("\""));
                                // System.out.println("\n\n\n\n---" + pid_xml + "---" + url_xml);

                            } catch (Exception e) {
                            }

                            todas_rev2 = todas_rev2.substring(todas_rev2.indexOf("pid=") + 4, todas_rev2.length());
                            URL url_text_xml = new URL(url_xml);
                            System.out.print("\n===>" + url_xml + "<===\n");

//=======================================Opteniendo contenido de la pagina
                            try {
                                in_xml = new BufferedReader(new InputStreamReader(url_text_xml.openStream()));
                            } catch (Throwable t) {
                            }
                            inputText_xml = "";

                            while ((inputLine_xml = in_xml.readLine()) != null) {
                                inputText_xml = inputText_xml + inputLine_xml;
                            }

                            //System.out.println("\n\nCONTADOR--->" + inputText_xml+"\n\n");
                            int contador_xmls = 0;


                            while (inputText_xml.indexOf("sci_arttext&amp;pid=S" + pid_xml) != -1) {
                                inputText_xml = inputText_xml.substring(inputText_xml.indexOf("sci_arttext&amp;pid=S" + pid_xml) + pid_xml.length(), inputText_xml.length());
                                //System.out.print(inputText_xml.substring(0, 20));
                                contador_xmls++;
                            }

                            System.out.println("\n<---------- Descargar ---------->");
                            for (int conti = 0; conti < contador_xmls; conti++) {
                                String add_ceros = "0000" + (conti + 1);
                                if (add_ceros.length() > 5) {
                                    add_ceros = add_ceros.substring(add_ceros.length() - 5, add_ceros.length());
                                }



                                String aux_url_des = "" + urls[url_pais].substring(7, urls[url_pais].length());
                                aux_url_des = aux_url_des.substring(0, aux_url_des.indexOf("/"));
                                System.out.print("Descargando xml...");

//=============================================================================
                                try {


                                    URL url_descargar = new URL("http://" + aux_url_des + "/scieloOrg/php/articleXML.php?pid=S" + pid_xml + add_ceros + "&lang=en");
                                    //URL url_descargar = new URL("http://artigos.scielo.sa.cr/" + pid_xml + add_ceros + ".xml");

                                    // establecemos conexion
                                    URLConnection urlCon = url_descargar.openConnection();
                                    System.out.println("\n-->" + url_descargar.toString() + "<--\n");


                                    InputStream is = urlCon.getInputStream();
                                    String dirDirectorio="/"+carpeta+"/xmls/" + quitarCEspeciales(pais[url_pais]) + "/S" + pid_xml.substring(0, 9) + "_"+quitarCEspeciales(revista)+"/"+ pid_xml.substring(9,13)+"/NUM-"+ pid_xml.substring(13,17)+"";
                                    File directorio = new File(dirDirectorio);
                                    if (directorio.mkdirs()) {
                                        System.out.println("--Se ha creado directorio--");
                                    } else {
                                    }

                                    FileOutputStream fos = new FileOutputStream(dirDirectorio+ "/S" + pid_xml + add_ceros + ".xml");


                                    byte[] array = new byte[1000];
                                    int leido = is.read(array);
                                    while (leido > 0) {
                                        fos.write(array, 0, leido);
                                        leido = is.read(array);
                                    }
                                    System.out.println(dirDirectorio+ "/S" + pid_xml + add_ceros + ".xml");
                                    // cierre de conexion
                                    is.close();
                                    fos.close();
                                    fos.flush();


                                    try {
                                        Runtime runtime = Runtime.getRuntime();
                                        Process proceso = runtime.exec("chmod 7777  " + dirDirectorio + "/S" + pid_xml + add_ceros + ".xml");
                                        proceso = runtime.exec("chown  josimar:josimar " + dirDirectorio + "/S" + pid_xml + add_ceros + ".xml");
                                    } catch (Exception r) {
                                        r.printStackTrace();
                                        
                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                    }
                } 
                }
                System.out.println("ISSN->" + issns[u]);
            }
           // zipDir("/"+carpeta+"/xmls/" + pais[url_pais]  );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static String quitarCEspeciales(String input) {
    // Cadena de caracteres original a sustituir.
    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
    // Cadena de caracteres ASCII que reemplazarán los originales.
    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
    String output = input;
    for (int i=0; i<original.length(); i++) {
        // Reemplazamos los caracteres especiales.
        output = output.replace(original.charAt(i), ascii.charAt(i));
    }//for i
    return output;
}//remove1
    public static void  zipDir(String directorio) throws IOException
    {
                Files.walkFileTree(Paths.get(directorio) , new SimpleFileVisitor<Path>()
            {
             
               @Override
		        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                   
                            System.out.println("archivo _ " + file.getFileName().toString());
                            File directory = file.toFile();
                            
                            if(directory.isDirectory())
                            {
		            if(file.getFileName().toString().contains("NUM-"))
                            {
                                Path destinationPath =  file;
                                File directorio = destinationPath.toFile();
                                   
                                    if(!directorio.exists())
                                    {
                                        if (directorio.mkdirs()) {
                                        System.out.println("--Se ha creado directorio--");
                                        } 
                                    }
                                    
                            zipear(file,destinationPath);
                            }
                            }
                            return FileVisitResult.CONTINUE;
                            
		        } 
          
            });
     }
    public static void zipear(final Path folder, final Path zipFilePath) throws FileNotFoundException, IOException
    {
         
            FileOutputStream fos = new FileOutputStream(zipFilePath.toFile());
            final ZipOutputStream zos = new ZipOutputStream(fos);
          Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                zos.putNextEntry(new ZipEntry(folder.relativize(file).toString()));
                Files.copy(file, zos);
                zos.closeEntry();
                return FileVisitResult.CONTINUE;
            }
          
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                zos.putNextEntry(new ZipEntry(folder.relativize(dir).toString() + "/"));
                zos.closeEntry();
                return FileVisitResult.CONTINUE;
            }
        });
    }
   
}






