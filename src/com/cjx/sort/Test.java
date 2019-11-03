package com.cjx.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
       /* List<Integer> l = new ArrayList<>();
        l.add( 1 );
        l.add( 2 );
        l.add( 3 );
        l.add( 4 );
        l.add( 5 );
        Iterator<Integer> it = l.iterator();
        while ( it.hasNext() ) {
            Integer i = it.next();
            if ( i == 2){
                it.remove();
                System.out.println( "==" + it.next() );
            }
            System.out.println( i );
        }*/
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH/mm/ss");
        System.out.println( now.format( format ) );//效果一样
        System.out.println( format.format( now ) );
        String s = "2019/10/29 11/15/01";
        LocalDateTime dateTime = LocalDateTime.parse(s, format);
        System.out.println( dateTime );
    }
}
