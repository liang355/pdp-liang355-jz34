package edu.neu.ccs.cs5010;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonBuilderWriterTest {
    @Test
    public void skierBuildWrite() throws Exception {

    }

    @Test
    public void getVertical() throws Exception {
        CommonBuilderWriter commonBuilderWriter = new CommonBuilderWriter();
        assertEquals(200, commonBuilderWriter.getVertical("10"));
        assertEquals(300, commonBuilderWriter.getVertical("20"));
        assertEquals(400, commonBuilderWriter.getVertical("30"));
        assertEquals(500, commonBuilderWriter.getVertical("40"));
    }

    @Test
    public void liftBuildWrite() throws Exception {
    }

    @Test
    public void hourBuildWrite() throws Exception {
    }

}