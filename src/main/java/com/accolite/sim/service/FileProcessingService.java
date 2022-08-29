package com.accolite.sim.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileProcessingService {
    private static Logger logger = LoggerFactory.getLogger(FileProcessingService.class);

    @Autowired
    SalesService salesService;

    public void processFile(InputStream inputStream) throws IOException, ParseException {
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            int vehicleId = (int) row.getCell(0).getNumericCellValue();
            String username = row.getCell(1).getStringCellValue();
            String orderDateString = row.getCell(2).getStringCellValue();
            SimpleDateFormat formatter = new SimpleDateFormat("dd%MM%yyyy h%mm%ss%a");
            Date date = formatter.parse(orderDateString);
            logger.info("VehicleId: " + vehicleId + " username: " + username + " orderDate: " + date);
            this.salesService.makeSale(vehicleId, username, date);
        }

    }
}