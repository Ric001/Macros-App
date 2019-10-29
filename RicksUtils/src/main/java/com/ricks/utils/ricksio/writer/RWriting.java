package com.ricks.utils.ricksio.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import com.ricks.utils.string.Strings;

public class RWriting {

    private RWriting() {
    }

    public static Boolean writeToTheEnd(final Appendable content, final File file) throws IOException {
        if (Strings.isNullOrEmpty(content) && Objects.isNull(file) && !file.exists())
            return false;
        return write(content, file);
    }

    private static Boolean write(final Appendable content, final File file) throws IOException {
        final PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.println(content);
        writer.flush();
        writer.close();
        return true;
    }
    
}