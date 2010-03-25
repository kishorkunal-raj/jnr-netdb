package jnr.netdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

final class NetDBIterator implements java.util.Iterator<NetDBEntry> {

    private final BufferedReader reader;
    private NetDBEntry next = null;

    public NetDBIterator(Reader r) {
        this.reader = r instanceof BufferedReader ? (BufferedReader) r : new BufferedReader(r);
    }

    NetDBEntry readNextEntry() {
        try {
            while (true) {
                String s = reader.readLine();
                if (s == null) {
                    throw new NoSuchElementException("No more elements");
                }
                String[] line = s.split("#", 2);
                // Skip empty lines, or lines that are all comment
                if (line.length < 0 || line[0].isEmpty()) {
                    continue;
                }
                String[] fields = line[0].trim().split("\\s+");
                if (fields.length < 2 || fields[0] == null || fields[1] == null) {
                    continue;
                }
                String serviceName = fields[0];
                String data = fields[1];
                List<String> aliases;
                if (fields.length > 2) {
                    aliases = new ArrayList<String>(fields.length - 2);
                    for (int i = 2; i < fields.length; ++i) {
                        if (fields[i] != null) {
                            aliases.add(fields[i]);
                        }
                    }
                } else {
                    aliases = Collections.emptyList();
                }
                return new NetDBEntry(serviceName, data, aliases);
            }
        } catch (IOException ex) {
            throw new NoSuchElementException(ex.getMessage());
        }
    }

    public boolean hasNext() {
        try {
            return next != null || (next = readNextEntry()) != null;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public NetDBEntry next() {
        NetDBEntry s = next != null ? next : readNextEntry();
        next = null;
        return s;
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
