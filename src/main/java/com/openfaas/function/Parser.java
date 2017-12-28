package com.openfaas.function;

import com.openfaas.http.HeaderReader;
import com.openfaas.http.HttpHeader;
import com.openfaas.http.HttpResponse;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;

public class Parser {
    public void acceptIncoming(DataInputStream dataStream, BufferedWriter out, HeaderReader parser) throws IOException {

        StringBuffer rawHeader = parser.readHeader();
        System.err.println(rawHeader);

        HttpHeader header = new HttpHeader(rawHeader.toString());

        if (header.getMethod() != null) {
            System.err.println(header.getMethod() + " method");
            System.err.println(header.getContentLength() + " bytes");

            byte[] body = header.readBody(dataStream);

            Handler handler = new Handler();
            String functionResponse = handler.function(new String(body), header.getMethod());

            HttpResponse response = new HttpResponse();
            response.setStatus(200);
            response.setContentType("text/plain");
            StringBuffer outBuffer = response.serialize(functionResponse);

            out.write(outBuffer.toString(), 0, outBuffer.length());
            out.flush();
        }
    }
}
