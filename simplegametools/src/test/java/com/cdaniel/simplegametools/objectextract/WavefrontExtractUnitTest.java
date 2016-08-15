package com.cdaniel.simplegametools.objectextract;

import com.cdaniel.simplegametools.objectextractor.ObjectExtract;
import com.cdaniel.simplegametools.objectextractor.ObjectExtractException;
import com.cdaniel.simplegametools.objectextractor.ObjectExtractor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class WavefrontExtractUnitTest {

    private String cubeData =

            "# Blender v2.77 (sub 0) OBJ File: 'Cube'\n"     +
                    "# www.blender.org\n"     +
                    "v 1.000000 -1.000000 -1.000000\n"     +
                    "v 1.000000 -1.000000 1.000000\n"     +
                    "v -1.000000 -1.000000 1.000000\n"     +
                    "v -1.000000 -1.000000 -1.000000\n"     +
                    "v 1.000000 1.000000 -0.999999\n"     +
                    "v 0.999999 1.000000 1.000001\n"     +
                    "v -1.000000 1.000000 1.000000\n"     +
                    "v -1.000000 1.000000 -1.000000\n"     +
                    "vn 0.0000 -1.0000 0.0000\n"     +
                    "vn 0.0000 1.0000 0.0000\n"     +
                    "vn 1.0000 -0.0000 0.0000\n"     +
                    "vn 0.0000 -0.0000 1.0000\n"     +
                    "vn -1.0000 -0.0000 -0.0000\n"     +
                    "vn 0.0000 0.0000 -1.0000\n"     +
                    "s off\n"     +
                    "f 2//1 4//1 1//1\n"     +
                    "f 8//2 6//2 5//2\n"     +
                    "f 5//3 2//3 1//3\n"     +
                    "f 6//4 3//4 2//4\n"     +
                    "f 3//5 8//5 4//5\n"     +
                    "f 1//6 8//6 5//6\n"     +
                    "f 2//1 3//1 4//1\n"     +
                    "f 8//2 7//2 6//2\n"     +
                    "f 5//3 6//3 2//3\n"     +
                    "f 6//4 7//4 3//4\n"     +
                    "f 3//5 7//5 8//5\n"     +
                    "f 1//6 4//6 8//6" ;

    @Test
    public void testUnpackFunction(){

        ObjectExtractor extractor = new ObjectExtractor(ObjectExtractor.ObjectType.WAVEFRONT);
        ObjectExtract extract;
        try {
            extract = extractor.extract(cubeData);
        }
        catch (ObjectExtractException e){
            throw new RuntimeException("Shouldnt hit here: extract error");
        }

        assertEquals(extract.getVertices().size()  , (8*3)  );
        assertEquals(extract.getNormals().size()   , (6*3)  );
        assertEquals(extract.getTextures().size()  , (0)  );
        assertEquals(extract.getDrawVerticeOrder().size() , (12*3)  );
    }
}
