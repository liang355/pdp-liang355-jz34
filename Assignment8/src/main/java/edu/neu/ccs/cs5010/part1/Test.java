//package edu.neu.ccs.cs5010.part1;
//
//import edu.neu.ccs.cs5010.part1.hours.HourFileEditor;
//import edu.neu.ccs.cs5010.part1.liftData.LiftRideFileEditor;
//import edu.neu.ccs.cs5010.part1.lifts.LiftFileEditor;
//import edu.neu.ccs.cs5010.part1.skiers.SkierFileEditor;
//
//import java.io.IOException;
//
//public class Test {
//    public static void main(String[] args)
//            throws IOException {
//        SkierFileEditor fe = new SkierFileEditor("skier.dat");
//        LiftFileEditor fe1 = new LiftFileEditor("lifts.dat");
//        HourFileEditor fe2 = new HourFileEditor("hours.dat");
//        LiftRideFileEditor fe3 = new LiftRideFileEditor("liftrides.dat");
//
//
//        System.out.println(fe.getRecord(31).toString());
//        System.out.println(fe.getRecord(46).toString());
//        System.out.println(fe.getRecord(54).toString());
//        System.out.println(fe.getRecord(58).toString());
//        System.out.println(fe.getRecord(1409).toString());
//
//        System.out.println(fe1.getRecord(1).toString());
//        System.out.println(fe1.getRecord(15).toString());
//        System.out.println(fe1.getRecord(40).toString());
//
//        System.out.println(fe2.getRecord(1).toString());
//        System.out.println(fe2.getRecord(2).toString());
//        System.out.println(fe2.getRecord(3).toString());
//
//        System.out.println(fe3.getRecord(31).toString());
//        System.out.println(fe3.getRecord(3).getRideInfo());
//        System.out.println(fe3.getRecord(54).toString());
//        System.out.println(fe3.getRecord(39888).toString());
////        System.out.println(fe3.getRecord(1409).toString());
//
//    }
//}
