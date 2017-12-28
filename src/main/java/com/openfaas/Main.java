package com.openfaas;

import com.openfaas.function.Parser;
import com.openfaas.http.HeaderReader;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        DataInputStream dataStream = new DataInputStream(System.in);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        HeaderReader headerReader = new HeaderReader(dataStream);

        Parser parser = new Parser();

        while (true) {
            parser.acceptIncoming(dataStream, out, headerReader);
        }
    }
}
