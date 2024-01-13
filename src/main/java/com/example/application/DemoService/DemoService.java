package com.example.application.DemoService;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class DemoService {

    Map<String,String> data;
    List<Map<Map<String,String>,Map<String, String>>> Orders;
    List<String> tables;

    public boolean inputs(List<Map<Map<String, String>, Map<String,String>>> orders) {
        for(Map<Map<String,String>, Map<String,String>> order:orders) {
            for(Map<String,String> table:order.keySet()) {
                tables.add(table.toString());
            }
            Orders.add(order);
        }
        try (
                FileWriter fileWriter = new FileWriter(String.format("/Orders/%s", orders.get(0).toString()));
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                data.put(tables.toString(), orders.toString());
                bufferedWriter.write(data.toString());
                return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
