package orestes.bloomfilter.test;

import com.google.gson.JsonElement;
import orestes.bloomfilter.BloomFilter;
import orestes.bloomfilter.FilterBuilder;
import orestes.bloomfilter.HashProvider;
import orestes.bloomfilter.json.BloomFilterConverter;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class ConverterTest {
    @Test
    public void testCorrectJSON() throws Exception {
        BloomFilter<String> bf = new FilterBuilder().expectedElements(50).falsePositiveProbability(0.1).buildBloomFilter();
        bf.add("Ululu");
        JsonElement json = BloomFilterConverter.toJson(bf);
        BloomFilter<String> otherBf = BloomFilterConverter.fromJson(json);
        assertTrue(otherBf.contains("Ululu"));
    }

    @Test
    public void testCorrectJSONfromCountingFilter() throws Exception {
        BloomFilter<String> bf = new FilterBuilder().expectedElements(50).falsePositiveProbability(0.1).buildCountingBloomFilter();
        bf.add("Ululu");
        JsonElement json = BloomFilterConverter.toJson(bf);
        BloomFilter<String> otherBf = BloomFilterConverter.fromJson(json);
        assertTrue(otherBf.contains("Ululu"));
    }

    @Ignore
    @Test
    public void testMurmur3() throws Exception {
        byte[] test1 = "Erik".getBytes(FilterBuilder.defaultCharset());
        byte[] test2 = "Witt".getBytes(FilterBuilder.defaultCharset());

        System.out.println(HashProvider.murmur3(0, test1));
        System.out.println(HashProvider.murmur3(0, test2));
    }
}
