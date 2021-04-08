package Assignment5;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
public class Main {

    public static void main(String[] args) {
        List<MonitoredData> data = new ArrayList<> ();
        try {

            List<String>file;
            try (Stream<String> lines = Files.lines(Paths.get("Activity.txt" ))) {
                file = lines.collect(Collectors.toList());
            }
            for (String string:file) {
                Scanner sc=new Scanner (string);
                //read the data
                while (sc.hasNext ()) {
                    Day day = new Day ( sc.next () );
                    Hour hour = new Hour ( sc.next () );
                    DayHour start = new DayHour ( day , hour );
                    Day day1 = new Day ( sc.next () );
                    Hour hour1 = new Hour ( sc.next () );
                    DayHour end = new DayHour ( day1 , hour1 );
                    data.add ( new MonitoredData ( start , end , sc.next () ) );
                }
            }


        //task 1
        File out = new File ( "Task_1.txt" );
        PrintWriter print = new PrintWriter ( out );
        data.stream ().forEach ( print::println );
        print.close ();
        //--------------------------------------
        //task 2
        out = new File ( "Task_2.txt" );
        print = new PrintWriter ( out );
        List<Day> task2;
        task2 = data.stream ()
                .map ( n -> n.start.day )
                .collect ( toList () );
        List<Day> dayList = task2.stream ().distinct ().collect ( toList () );
        print.println ( dayList.size () );
        print.close ();
        //---------------------------------
        //task3
        out = new File ( "Task_3.txt" );
        print = new PrintWriter ( out );
        Map<String, Integer> task3 = data.stream()
                .collect(
                        Collectors.groupingBy(
                                MonitoredData::getActivity,
                                Collectors.collectingAndThen(
                                        Collectors.mapping(e-> e.getActivity() , Collectors.counting()), Long::intValue)));
        print.println ( task3.toString () );
        print.close ();
        //---------------------------------
        //task4
            out = new File ( "Task_4.txt" );
            print = new PrintWriter ( out );
        Map<String, Map<String, Integer>> task4 = data.stream()
                    .collect(
                            Collectors.groupingBy(
                                    MonitoredData::getDay,
                                    Collectors.groupingBy(
                                            MonitoredData::getActivity,
                                            Collectors.collectingAndThen(
                                                    Collectors.mapping(MonitoredData::getActivity, Collectors.counting()), Long::intValue))));
            PrintWriter finalPrint = print;
            task4.forEach ((day, map )->{
                finalPrint.println ( day+":" );
                finalPrint.println ( map.toString () );
                        });

            print.close ();

        //---------------------------------
        //task5
            out = new File ( "Task_5.txt" );
            print = new PrintWriter ( out );
            Map<String, Double> task5 = data.stream()
                    .collect(
                            Collectors.groupingBy(
                                    MonitoredData::getActivity,
                                    Collectors.collectingAndThen(
                                            Collectors.mapping(MonitoredData::durationInMinutes, Collectors.summingDouble (e -> e )), e-> e )
                            ));

            print.println ( task5.toString () );
            print.close ();
            //---------------------------------
            //task6
            out = new File ( "Task_6.txt" );
            print = new PrintWriter ( out );
            List<String> task6=data.stream()
                    .collect(Collectors.groupingBy(
                            MonitoredData::getActivity,
                            Collectors.toSet())).entrySet().stream()
                    .map(activity -> {
                        int shortAction = 0;
                        for(MonitoredData m: activity.getValue()) {
                            if(m.duration().minute < 5 &&m.duration ().hour==0) shortAction++;
                        }
                        if(shortAction > activity.getValue().size()*90/100) return activity.getKey();
                        return null;
                    })
                    .filter(e -> e != null)
                    .collect(Collectors.toList());
            task6.stream ().forEach ( print::println );
            print.close ();
            System.out.println ("done!");
    }
        catch (FileNotFoundException e){
            System.out.println ("file not found!");
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }
}
