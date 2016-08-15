package com.cdaniel.simplegameengine.utils;

import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class VertexCollectionUnitTest {

    int numVertices = 20;
    VertexCollection vc;
    float[] rawVc;
    float[] rawXY;

    @Before
    public void before() {

        vc = new VertexCollection();

        rawVc = new float[3 * numVertices];
        rawXY = new float[2 * numVertices];

        int rawIndex = 0;
        int xyIndex = 0;
        for (int j = 0; j < numVertices; j++) {

            float randX = (float) Math.random() * 20;
            float randY = (float) Math.random() * 20;
            float randZ = (float) Math.random() * 10;
            vc.push(randX, randY, randZ);

            rawVc[rawIndex] = randX;
            rawIndex++;
            rawVc[rawIndex] = randY;
            rawIndex++;
            rawVc[rawIndex] = randZ;
            rawIndex++;

            rawXY[xyIndex] = randX;
            xyIndex++;
            rawXY[xyIndex] = randY;
            xyIndex++;


        }
    }

    @Test
    public void testVertexSizeCalc() throws Exception {

        assertEquals(vc.getVertexCount(), numVertices);
    }

    @Test
    public void testCoordinates() {

        float[] shouldBe = vc.getCoordinateArray();
        assertArrayEquals(rawVc, shouldBe, .02f);
    }

    @Test
    public void testXYCoordinates() {

        float[] shouldBe = vc.getCoordinateArray(true, true, false);
        assertArrayEquals(rawXY, shouldBe, .02f);
    }

    @Test
    public void testIterator() {

        int fromIndex = 0;
        float[] fromIterator = new float[rawVc.length];

        vc.iterator.reset();
        while (vc.iterator.next()) {
            fromIterator[fromIndex] = vc.iterator.x;
            fromIndex++;
            fromIterator[fromIndex] = vc.iterator.y;
            fromIndex++;
            fromIterator[fromIndex] = vc.iterator.z;
            fromIndex++;
        }

        assertArrayEquals(vc.getCoordinateArray(), rawVc, .02f);
        assertArrayEquals(rawVc, fromIterator, .02f);
    }

    @Test
    public void testCopyAndTransform() {

        //~~~~~~~~~~~~~~~~~~~~~~~~//
        //Copy
        //~~~~~~~~~~~~~~~~~~~~~~~~//
        VertexCollection copy = vc.copy();
        assertArrayEquals(copy.getCoordinateArray(), vc.getCoordinateArray(), .02f);
        assertArrayEquals(copy.getCoordinateArray(), this.rawVc, .02f);
        assertEquals(copy.getVertexCount(), vc.getVertexCount());
        assertEquals(copy.getVertexCount(), this.numVertices);

        //~~~~~~~~~~~~~~~~~~~~~~~~//
        //Transform
        //~~~~~~~~~~~~~~~~~~~~~~~~//
        int transformedIndex = 0;
        float[] rawTransformed = new float[copy.getVertexCount() * 3];

        copy.iterator.reset();
        while (copy.iterator.next()) {

            float randX = (float) Math.random() * 20;
            float randY = (float) Math.random() * 20;
            float randZ = (float) Math.random() * 10;
            copy.iterator.transform(randX, randY, randZ);

            rawTransformed[transformedIndex] = randX;
            transformedIndex++;
            rawTransformed[transformedIndex] = randY;
            transformedIndex++;
            rawTransformed[transformedIndex] = randZ;
            transformedIndex++;
        }

        assertArrayEquals(rawTransformed, copy.getCoordinateArray(), .02f);
    }

    @Test
    public void testCopyFrom() {

        //~~~~~~~~~~~~~~~~~~~~~~~~//
        //Create a New VC copy from our original VC
        //~~~~~~~~~~~~~~~~~~~~~~~~//
        VertexCollection newVc = new VertexCollection();
        newVc.copyValuesFrom(this.vc, 0f, 0f, 0f);
        assertArrayEquals(newVc.getCoordinateArray(), vc.getCoordinateArray(), .02f);
        assertArrayEquals(newVc.getCoordinateArray(), this.rawVc, .02f);
        assertEquals(newVc.getVertexCount(), vc.getVertexCount());
        assertEquals(newVc.getVertexCount(), this.numVertices);

        //~~~~~~~~~~~~~~~~~~~~~~~~//
        //Make some changes and make sure things didnt change
        //~~~~~~~~~~~~~~~~~~~~~~~~//
        while (newVc.iterator.next()) {

            float randX = (float) Math.random() * 20;
            float randY = (float) Math.random() * 20;
            float randZ = (float) Math.random() * 10;
            newVc.iterator.transform(randX, randY, randZ);
        }
        assertArrayEquals(rawVc, vc.getCoordinateArray(), .02f);
    }
}