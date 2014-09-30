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
            BufferedReader myFileSymbol = new BufferedReader(new FileReader(myFile));
            String str;

            File fXmlFile = new File("src/data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getElementsByTagName()


           /* NodeList nList = doc.getElementsByTagName("ingredient");
            for (int i = 0; i< nList.getLength(); i++)
            {
                Node nNode = nList.item(i);
                Ingredient ingredient = new Ingredient();
                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) nNode;
                    ingredient.setIngredientName(element.getAttribute("name"));
                    ingredient.setIngredientType(element.getElementsByTagName("type").item(0).getTextContent());
                    ingredient.setCalories(convertFromStringToInteger(element.getElementsByTagName("calories").item(0).getTextContent()));
                    ingredient.setProteins(convertFromStringToInteger(element.getElementsByTagName("proteins").item(0).getTextContent()));
                    ingredient.setFats(convertFromStringToInteger(element.getElementsByTagName("fats").item(0).getTextContent()));
                    ingredient.setCarbs(convertFromStringToInteger(element.getElementsByTagName("carbs").item(0).getTextContent()));
                }
                ingredients.add(ingredient);
            }*/

            while ((str = myFileSymbol.readLine()) != null) {

                if (str.contains("<amount>") && str.contains("</amount>")) {
                    str = str.replace("<amount>", "").replace("</amount>", "");
                    amount = Integer.parseInt(str);
                }

            }
            myFileSymbol.close();


        } catch (IOException e) {
            System.out.println("Error while working with file!");

        }
        catch (NullPointerException e)
        {
            System.out.println("File is not found");

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

    public static long sout(List<Toy> toyList){         //этот метод не только выводит что нам нужно, но и возвращает время поиска по массиву!!!

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
