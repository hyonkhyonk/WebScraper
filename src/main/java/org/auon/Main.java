package org.auon;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int i = 2;
        while(i == 2) {
            System.out.print("Stock Name (Abbreviation or company name:");
            String stockName = input.nextLine();
            try {

                Document doc = Jsoup
                        .connect("https://www.google.com/search?q=" + stockName + "+stock+price")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                        .header("Accept-Language", "*")
                        .get();

                Element stockPrice = doc.getElementsByClass("IsqQVc NprOob wT3VGc").first();
                Element mktCap = doc.getElementsByClass("PZPZlf").get(9);
                Element paysdiv = doc.getElementsByClass("PZPZlf").get(11);
                Element mktSummary = doc.getElementsByClass("oPhL2e").first();
                Element name = doc.getElementsByClass("iAIpCb PZPZlf").first();


                System.out.println(mktSummary.text());
                System.out.println(name.text());
                System.out.println("Stock Price: "+stockPrice.text());
                System.out.println("Stock Market Cap: "+mktCap.text());
                if(paysdiv.text().equals("-")){
                    System.out.println("Dividend Yield: None");
                }else{
                    System.out.println("Dividend Yield: " + paysdiv.text());
                }



            }catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}