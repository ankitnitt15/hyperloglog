import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import net.agkn.hll.HLL;

import java.util.stream.LongStream;

public class HLLTest {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        HashFunction hashFunction = Hashing.murmur3_128();
        long numberOfElements = 1000000;
        HLL hll = new HLL(5,6);

        LongStream.range(0, numberOfElements).forEach(elem -> {

            long hashed = hashFunction.newHasher().putLong(elem).hash().asLong();
            //System.out.println(Long.toBinaryString(hashed));
            hll.addRaw(hashed);
            }
        );
        long startTime2 = System.currentTimeMillis();

        long cardinality = hll.cardinality();
        System.out.println(cardinality);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Time taken for cardinality: " + (endTime - startTime2) + "ms");
    }
}
