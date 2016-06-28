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
 */
public class MainZip {

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
           String carpeta="descargaxml";
            zipDir("/"+carpeta+"/xmls/Chile");
           Path uno=Paths.get("/"+carpeta+"/xmls/Chile");
           Path dos=Paths.get("/"+carpeta+"/xmls/Chile");
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void  zipDir(String directorio) throws IOException
    {
                Files.walkFileTree(Paths.get(directorio) , new SimpleFileVisitor<Path>()
            {
             
               @Override
		        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                   
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
                        
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if(dir.getFileName().toString().contains("NUM-"))
                {
                    System.out.println("entra a zipear");
                    zipear(dir,dir);
                }
                return FileVisitResult.CONTINUE;
                }
            });
     }
    public static void zipear(final Path folder, final Path zipFilePath) throws FileNotFoundException, IOException
    {
            final Path zipFile = Paths.get(zipFilePath.toString().concat(folder.getFileName()+".zip"));
            FileOutputStream fos = new FileOutputStream(zipFile.toFile());
            final ZipOutputStream zos = new ZipOutputStream(fos);
          Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("archivo _ " + file.toString());
                            
                zos.putNextEntry(new ZipEntry(folder.relativize(file).toString()));
                Files.copy(file, zos);
                zos.closeEntry();
                return FileVisitResult.CONTINUE;
            }
          
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("archivo _ $$$$$$$$$$$$$$$$$$$$$" );
                
                zos.putNextEntry(new ZipEntry(folder.relativize(dir).toString() + "/"));
                zos.closeEntry();
               
                return FileVisitResult.CONTINUE;
                
            }
        });
    }
   
}






