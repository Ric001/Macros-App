package com.ricks.utils.ricksio.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.ricks.utils.string.Strings;

public class RWriting {

    private RWriting() {
    }

    public static Boolean writeToTheEnd(final Appendable content, final File file) throws IOException {
        if (Strings.isNullOrEmpty(content) && Objects.isNull(file) && !file.exists())
            return false;
        return write(content, file);
    }

    public static Boolean writeToTheEnd(final Optional<List<File>> filesWrapper, List<StringBuilder> contentList)
            throws IOException {

        if (Objects.isNull(filesWrapper) || !filesWrapper.isPresent())
            return false;
        if (Objects.isNull(contentList) || contentList.isEmpty())
            return false;
        if (contentList.size() != filesWrapper.get().size())
            return false;
            
        final List<File> files = filesWrapper.get();
        for (int i = 0; i < filesWrapper.get().size(); i++) {
            write(contentList.get(i), files.get(i));
        }

        return true;
    }

    public static Boolean writeToEnd(final Appendable content, final Socket socket) throws IOException {
        if (Strings.isNullOrEmpty(content))
            return false;
        if (Objects.isNull(socket) || socket.isClosed())
            return false;
        
        if (socket.isConnected()) {
           return writeToEnd(content, socket.getOutputStream());
        }
        return false;
    }

    public static Boolean writeToEnd(final String content, final Socket socket) throws IOException {
        if (Strings.isNullOrEmpty(content))
            return false;
        if (Objects.isNull(socket) || socket.isClosed())
            return false;
        if (socket.isConnected()) {
            return writeToEnd(new StringBuffer(content), socket.getOutputStream());
        }

        return true;
    }

    public Boolean writeToEnd(final String content, final File file) throws IOException {
        if (Strings.isNullOrEmpty(content))
            return false;
        if (Objects.isNull(file) || !file.exists() || !file.isFile())
            return false;
        return write(new StringBuffer(content), file);
    }

    
    private static Boolean writeToEnd(final Appendable content, final OutputStream outputStream) {
        if (Strings.isNullOrEmpty(content))
            return false;
        if (Objects.isNull(outputStream))
            return false;
        if (Objects.nonNull(outputStream)) {
            final PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(content);
            printWriter.flush();
            printWriter.close();
            return true;
        }
        return false;
    }

    private static Boolean write(final Appendable content, final File file) throws IOException {
        final PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.println(content);
        writer.flush();
        writer.close();
        return true;
    }

}