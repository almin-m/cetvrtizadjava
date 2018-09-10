
package javaapplication12;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.*;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class JavaApplication12 {

  
    public static void main(String[] args) throws IOException  {

        
         System.out.println("Ponudjene operacije su: List, Info, Create_Dir, Rename, Copy, Move, Delete"  );
        System.out.println("Molimo da unesete zeljenu operaciju: ");
        
        Scanner scan = new Scanner (System.in);
        
        switch (scan.nextLine()) {
            
        
            case "List":
                System.out.println("Molimo unesite putanju foldera: ");
                
                File path = new File (scan.next());
                if(path.exists() && path.isDirectory())
                {
                    String [] strings = path.list();
             int i = 0;
                    for (i=0; i<strings.length; i++ )
                    System.out.println(strings[i]);
                }
        break;

            case "Info": 
                System.out.println("Molimo unesite putanju foldera");
                
                 File path1 = new File (scan.nextLine());
                    
               BasicFileAttributes attr = Files.readAttributes(path1.toPath(), BasicFileAttributes.class);
               
                System.out.println("Name = " + path1.getName());
                System.out.println("Absolute path = " + path1.getAbsolutePath());
                System.out.println("Size = " + path1.length());
                System.out.println("Time created = " + attr.creationTime());
                System.out.println("Last modified = " + Instant.ofEpochMilli(path1.lastModified()).atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss")));
                
                break;

            case "Create_Dir":
                System.out.println("Molimo unesite putanju foldera koji zelite kreirati:");
                File testDirectory = new File(scan.nextLine());
                try {
                    if(!testDirectory.exists())
                {
                    testDirectory.mkdir();
                System.out.println("Created a directory called " + testDirectory.getName());
                }
                else
                {
                System.out.println("Directory called " + testDirectory.getName() + " already exists.                ");
                }                   
             
                } catch (Exception e) {
                System.out.println("Couldn't create a directory called "
                    + testDirectory.getName());
                } 
                break;

            case "Rename":
             System.out.println("Molimo unesite putanju foldera kojem zelite promjeniti naziv:");
                
                File oldfile = new File(scan.nextLine());
                
               System.out.println("Molimo unesite novi naziv foldera skupa sa putanjom:");
                File newfile = new File(scan.nextLine());
                    if(!oldfile.exists())
                {
                     System.out.println("File doesn't exist!");
                    return;
                }
                    if(newfile.exists())
                {
                    System.out.println("File with desired name already exists!");
                    return;
                }
                    
                    if(oldfile.renameTo(newfile))
                {
                    System.out.println("Rename succesful");
                }
                    else
                {
                    System.out.println("Rename failed");
                }
                break;
                
            case "Copy":
                 System.out.println("Molimo unesite putanju fajla koji zelite kopirati:");
                File afile = new File(scan.nextLine());
                
                System.out.println("Molimo unesite putanju na koju zelite kopirati zeljeni fajl:");
                File bfile = new File(scan.nextLine());
 
         try (FileInputStream inStream = new FileInputStream(afile);
               FileOutputStream outStream = new FileOutputStream(bfile);) {
 
               byte[] buffer = new byte[1024];
 
          int length;
          
         while ((length = inStream.read(buffer)) > 0) {
 
             outStream.write(buffer, 0, length);
 
               }
 
 
               System.out.println("File is copied successfuly!");
 
             } catch (IOException exc) {
                 System.out.println(exc);
               }
     
            return;
            
            case "Move":

                String source = "";
                String destination = "";
         
        try(BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in)))
        {
            System.out.println("Enter file path: ");
            source = bufferRead.readLine();
             
            System.out.println("Enter destination folder: ");
            destination = bufferRead.readLine();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
         
        File gfile = new File(source);
        File hfile = new File(destination + "\\" + gfile.getName());
         
        try(FileInputStream inStream = new FileInputStream(gfile);
            FileOutputStream outStream = new FileOutputStream(hfile))
        {
            byte[] buffer = new byte[1024];
            int length;
            while((length = inStream.read(buffer)) > 0)
                outStream.write(buffer, 0, length);
            System.out.println("File is moved successfuly!");
             
            inStream.close();
            outStream.close();
             
            gfile.delete();
        }
        catch(IOException exc)
        {
            System.out.println(exc);
        }
         return;
                
            case "Delete":
                System.out.println("Molimo unesite putanju fajla koji zelite obrisati:");
                File file = new File(scan.nextLine());
                if(file.exists())
                {
                    file.delete();
                    System.out.println("File successfully deleted!");
                } 
                else
                {
                    System.out.println("Cannot delete " + file.getName() + " because " + file.getName() + " does not exist.");
                }
         
                break;
                
            default:
                System.out.println("Odabrali ste nepostojecu operaciju. Molimo odaberite jednu od ponudjenih.");
        }
        
        

        
    }
    }

