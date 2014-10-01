package com.epam.cdp.byta2014.module4.lecture2.demo.runner;

import com.epam.cdp.byta2014.module4.lecture2.demo.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.epam.cdp.byta2014.module4.lecture2.demo.exception.PerformanceException;
import org.w3c.dom.Document;
//import sun.plugin.dom.core.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Pavlo_Kamyshov on 9/15/2014.
 */
public class Utils {


    static int numberInPlay = 0;
    static int amount = 1000;


    public static long initiateList(List<Toy> toyList) {
        long startTime = System.currentTimeMillis();


        File myFile = new File("src/data.xml");
        if (!myFile.exists()) {
            System.out.println();
            System.out.println("File with toys amount is NOT found!!!");

        }
        if (!myFile.canRead() || !myFile.canWrite()) {
            System.out.println("File with toys amount can NOT be read/written");

        }

        try {
            //BufferedReader myFileSymbol = new BufferedReader(new FileReader(myFile));
           // String str;

            File fXmlFile = new File("src/data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            amount = Integer.parseInt(doc.getElementsByTagName("amount").item(0).getTextContent());  //doc... это Нода, в которой судя по всему может быть много найденных тегов.




        } catch (IOException e) {
            System.out.println("Error while working with file!");

        }
        catch (NullPointerException e)
        {
            System.out.println("File is not found");

        }
        catch (NumberFormatException e)
        {
            System.out.println("Incorrect amount parameter set in the file!");
            System.out.println("Working with file was skipped, default value taken");

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

        for (int i = 0; i < amount; i++) {
            toyList.add(new Car());
        }

        //int ballsAmount = 5000;//(int) ((Math.random() * 50) + 1);
        for (int i = 0; i < amount; i++) {
            toyList.add(new Ball());
        }

        //int dollsAmount = 5000;//(int) ((Math.random() * 50) + 1);
        for (int i = 0; i < amount; i++) {
            toyList.add(new Cubic());
        }

        //int cubicsAmount = 5000;//(int) ((Math.random() * 50) + 1);
        for (int i = 0; i < amount; i++) {
            toyList.add(new Doll());
        }
        return System.currentTimeMillis()-startTime;

    }

    public static long sout(List<Toy> toyList){         //этот метод не только выводит что нам нужно, но и возвращает время поиска по массиву

        System.out.println("Here are the prices of " + toyList.size() + " toys in the room:");

        System.out.print("Sorted prices are: ");
        for (Toy elem : toyList) {


            String s = "$" + String.format("%1.2f", elem.getPrice());
            System.out.print(s + " ");
        }
        System.out.println();

        long searchArray = countInPlay(toyList);

        System.out.println("Amongst them in play - " + numberInPlay);
        return searchArray;
    }

    public static long countInPlay(List<Toy> toyList) {

        long startTime = System.currentTimeMillis();
        numberInPlay = 0;

        for (Toy elem : toyList) {

            if (elem.getPlayed()) {
                numberInPlay++;
            }
        }

        long diff = System.currentTimeMillis() - startTime;

        return diff;
    }

    public static long countDiff(long searchArray, long searchLinked) throws PerformanceException {
        long differ = searchArray-searchLinked;
        if (differ < (long) 0) {
            throw new PerformanceException(String.valueOf(differ));
        }
        return differ;
    }

    public static long deleteElement(List<Toy> toyList){
        long startTime = System.currentTimeMillis();

        for (Iterator<Toy> iter = toyList.iterator(); iter.hasNext(); ) {
            Toy elem = iter.next();
            if (elem.getAgeGroup() != 1) {
                iter.remove();
            }
        }
        return System.currentTimeMillis() - startTime;
    }


}
