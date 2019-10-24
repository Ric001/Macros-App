package com.ricks.utils.ricksio.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class ReadUtils {

    private ReadUtils() {
    }

    public final static String BUFFERED_READER_ERROR_MESSAGE = "TRYING TO READ FROM AN EMPTY BUFFER READER";

    private static void assertFileIsNotInvalid(final File file) throws IOException {
        if (Objects.isNull(file))
            throw new NullPointerException("THE FILE IS NULL");
        if (!file.exists())
            throw new FileNotFoundException("FILE DOES NOT EXISTS");
        if (file.isDirectory())
            throw new IOException("TRYING TO READ A DIRECTORY AND NOT A FILE");
    }

    private static void assertInputStreamIsNotInvalid(final InputStream inputStream) {
        if (Objects.isNull(inputStream))
            throw new NullPointerException("Trying to Read From a Null InputStream");
    }

    public static String readToTheEnd(final File file) throws SQLException, IOException {
        assertFileIsNotInvalid(file);
        String line = "";
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (hasNextLine(bufferedReader)) {
            line += bufferedReader.readLine();
        }
        return line;
    }

    public static String readToTheEnd(final BufferedReader bReader) throws IOException {

        final StringBuilder content = new StringBuilder();
        if (Objects.isNull(bReader))
            return content.append(BUFFERED_READER_ERROR_MESSAGE).toString();
        while (hasNextLine(bReader))
            content.append(bReader.readLine());

        return content.toString();
    }

    public static Properties readToEndOnProperties(final BufferedReader bReader) throws IOException {

        final Properties properties = new Properties();
        if (Objects.isNull(bReader))
            throw new NullPointerException(BUFFERED_READER_ERROR_MESSAGE);
        properties.load(bReader);
        return properties;
    }

    public static Properties readToEndOnProperties(final InputStream inputFlow) throws IOException {
        assertInputStreamIsNotInvalid(inputFlow);
        final Properties dotPropertiesFileValues = new Properties();
        dotPropertiesFileValues.load(inputFlow);
        return dotPropertiesFileValues;
    }

    public static boolean hasNextLine(BufferedReader bReader) throws IOException {
        if (Objects.nonNull(bReader.readLine()))
            return true;
        return false;
    }
}