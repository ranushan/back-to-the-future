package com.ranushan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class AbstractTest {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }


    String getActualResult() {
        return outContent.toString().substring(outContent.toString().indexOf("A"), outContent.toString().indexOf("€") + 1);
    }

    static void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    static String createTempFile(Path tempDir, String filename, List<String> content) throws IOException {
        Path path = tempDir.resolve(filename);
        var writeFile = Files.write(path, content);
        return writeFile.toAbsolutePath().toString();
    }

}
