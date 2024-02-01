package com.ranushan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ranushan.v4.service.analyse.Scan;
import com.ranushan.v4.service.analyse.ScanFile;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackToTheFutureTest extends AbstractTest {

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("parameters")
    void test(TestCase testCase, @TempDir Path tempDir) throws IOException {
        var absolutePath = createTempFile(tempDir, testCase.filename(), testCase.content());
        provideInput(absolutePath);
        Scan scan = new ScanFile();
        var movies = scan.process();
        scan.finish(movies);
        assertEquals(testCase.expected(), getActualResult());
    }

    public static Stream<Arguments> parameters() throws Exception {
        return Stream.of(new ObjectMapper().reader()
                        .readValue(BackToTheFutureTest.class
                                        .getResourceAsStream("test-cases.json"),
                                TestCase[].class))
                .map(Arguments::of);
    }

    public record TestCase(String filename, List<String> content, String expected) {}

}