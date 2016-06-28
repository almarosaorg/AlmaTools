/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package desxmlscielo;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author Magda
 */


public class RegistoXml {

    /**
     * @param args the command line arguments
     */

public static void main (String[] args) throws IOException {
String nombre;
String nobreSubDir;
String nombreArchivo;
int numArchivo = 0;
int numDir = 0;
int numSubDir = 0;
String nobreSubSubDir;
String dir = "C:\\home\\xmls";
FileWriter fichero = null;
PrintWriter pw = null;

 fichero = new FileWriter("C:\\home\\prueba.txt");
BufferedWriter bw = new BufferedWriter(fichero);
 PrintWriter salida = new PrintWriter(bw);    
File directorio = new File (dir);
File[] listadir = directorio.listFiles();
String directorios[] = new String[3];
for(File file:listadir)
{
 //numXml++;
 directorios[numDir] = file.getName();
 //System.out.println ("Directorio::: "+directorios[numDir]+"  i:::"+numDir);
 numDir++;
}
//System.out.println ("Numero de directorios: "+numDir);


//System.out.println(directorios.length);
for (int i = 0; i<3;i++){
    numSubDir = 0;
    //System.out.println (nombre);
    nobreSubDir= "";
    nobreSubDir = dir+"\\"+directorios[i];
    salida.println("\n\n\n"+directorios[i]+"\n");
    File subdirectorios = new File (nobreSubDir);
    File[] subdirectorio = subdirectorios.listFiles();
    //String subdirectorio[] = new String[10];

     for(File filed:subdirectorio)
        {
        //numSubDir++;
        nombre = filed.getName();
        numSubDir ++;
        salida.println(directorios[i]+" Subdirectorio "+numSubDir+": "+nombre+"\n\n\n");
        nobreSubSubDir = nobreSubDir+"\\"+nombre;
        File archivos = new File (nobreSubSubDir);
        File[] arch = archivos.listFiles();
    //String subdirectorio[] = new String[10];
        numArchivo = 0;
         for(File file:arch)
            {
        //numSubDir++;

             nombreArchivo = file.getName();
             numArchivo ++;
             salida.println(directorios[i]+" "+numArchivo+": "+nombreArchivo);

         }
        salida.println("Numero de archivos en "+nombre+": "+numArchivo+"\n\n\n");
      }
    //System.out.println ("Numero de subdirectorios: "+numSubDir);

   }//aqui termina el for



}

}
