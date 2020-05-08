package br.com.bcp;

import java.sql.Timestamp;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {
        System.out.println(TimeZone.getDefault());
        System.out.println("Horário verão 18/19: 0h de 4 de novembro de 2018, à 0h de 17 de fevereiro de 2019 \n");

	    String data1 = "2019-01-04T00:00:00-0200";
        String data2 = "2019-01-04T00:00:00-0300";
	    String data3 = "2019-02-19T00:00:00-0300";

	    System.out.println("String: " + data1);
        System.out.println("Timestamp: " + Timestamp.from(DateUtil.parse(data1).toInstant()));
        System.out.println();
        System.out.println("String: " + data2);
        System.out.println("Timestamp: " + Timestamp.from(DateUtil.parse(data2).toInstant()));
        System.out.println();
        System.out.println("String: " + data3);
        System.out.println("Timestamp: " + Timestamp.from(DateUtil.parse(data3).toInstant()));
    }
}
