package edu.neu.ccs.cs5010;

import java.io.IOException;

public class Test {
    public static void main(String[] args)
            throws IOException {
        SkierFileEditor fe = new SkierFileEditor("skier.dat");
        LiftFileEditor fe1 = new LiftFileEditor("lifts.dat");
        HourFileEditor fe2 = new HourFileEditor("hours.dat");
        LiftRideFileEditor fe3 = new LiftRideFileEditor("liftrides.dat");
//        fe.insertRecord(new SkierRecord(36371, 13, 9000, 0));
//        fe.insertRecord(new SkierRecord(28151, 12, 8900, 0));
//        fe.insertRecord(new SkierRecord(12350, 11, 8700, 0));

        System.out.println(fe.getRecord(31).toString());
        System.out.println(fe.getRecord(46).toString());
        System.out.println(fe.getRecord(54).toString());
        System.out.println(fe.getRecord(58).toString());
        System.out.println(fe.getRecord(1409).toString());

//        System.out.println(fe1.getRecord(1).toString());
//        System.out.println(fe1.getRecord(15).toString());
//        System.out.println(fe1.getRecord(40).toString());
//
//        System.out.println(fe2.getRecord(1).toString());
//        System.out.println(fe2.getRecord(2).toString());
//        System.out.println(fe2.getRecord(3).toString());

        System.out.println(fe3.getRecord(31).toString());
        //System.out.println(fe3.getRecord(3).getRideInfo());
        System.out.println(fe3.getRecord(54).toString());
        System.out.println(fe3.getRecord(39888).toString());
//        System.out.println(fe3.getRecord(1409).toString());

//        FileEditor fe = new FileEditor("people.dat");
//
//        fe.insertRecord(new PersonRecord(1, "Brian", "Sullivan",
//                "bs@hotmail.com"));
//        fe.insertRecord(new PersonRecord(2, "Randal", "Wallace",
//                "rw@hotmail.com"));
//        fe.insertRecord(new PersonRecord(3, "Eric", "Bloch",
//                "eb@hotmail.com"));
//        fe.insertRecord(new PersonRecord(4, "Kapil", "Ansari",
//                "ka@hotmail.com"));
//        System.out.println(fe.getRecord(3).toString());
//        fe.showAllRecords();
//        fe.updateRecord(new PersonRecord(4,"Tony","Li",
//                "kapil@somemail.com"));
//        fe.showAllRecords();
//        fe.deleteRecord(new PersonRecord(1,"Brian","Sullivan",
//                "bs@hotmail.com"));
//        fe.showAllRecords();
//        fe.close();
    }
}
