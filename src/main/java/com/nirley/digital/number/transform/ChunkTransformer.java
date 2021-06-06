package com.nirley.digital.number.transform;

import com.nirley.digital.number.chunk.Chunk;
import com.nirley.digital.number.response.ResponseWrapper;

import java.util.HashMap;
import java.util.Map;

import static com.nirley.digital.number.constants.Constants.ILLEGAL_CHAR_MASK;

public class ChunkTransformer {

    static Map<String,Character> alphaMap = new HashMap<>();

    // move to config file in future
    static {
        alphaMap.put(" _ | ||_|",'0');
        alphaMap.put("     |  |",'1');
        alphaMap.put(" _  _||_ ",'2');
        alphaMap.put(" _  _| _|",'3');
        alphaMap.put("   |_|  |",'4');
        alphaMap.put(" _ |_  _|",'5');
        alphaMap.put(" _ |_ |_|",'6');
        alphaMap.put(" _   |  |",'7');
        alphaMap.put(" _ |_||_|",'8');
        alphaMap.put(" _ |_| _|",'9');
    }

     public static ResponseWrapper transform(Chunk chunk) {
        if(alphaMap.containsKey(chunk.getSource()))
            return new ResponseWrapper(false,alphaMap.get(chunk.getSource()));
        return new ResponseWrapper(true,ILLEGAL_CHAR_MASK);
     }


}
