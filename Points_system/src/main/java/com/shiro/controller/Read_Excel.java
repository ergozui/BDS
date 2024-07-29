package com.shiro.controller;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author yang
 * @version 1.0
 * @date 2022/1/6 14:07
 */
@Controller
@RequestMapping("myController")
public class Read_Excel {
    @PostMapping("/uploadExcel")
    public ResponseEntity<Message> handleExcelUpload(@RequestParam("excelFile") MultipartFile excelFile) {
        // 处理上传的Excel文件逻辑
        if (!excelFile.isEmpty()) {
            try {
                // 获取Excel文件的输入流
                InputStream inputStream = excelFile.getInputStream();

                // 使用Apache POI来获取Excel文件中的文本数据
                Workbook workbook = WorkbookFactory.create(inputStream);
                Map<String, List<Integer>> myMap=new HashMap<>();
                List<String> youList = new ArrayList<>();
                String stringValue= String.valueOf(' ');
                // 遍历每个工作表和单元格
                for (Sheet sheet : workbook) {
                    for (Row row : sheet) {
                        List<Integer> myList = new ArrayList<>();
                        for (Cell cell : row) {
                            // 获取单元格的文本内容并添加到StringBuilder
                            if (cell.getCellType() == CellType.NUMERIC) {
                                // 处理 NUMERIC 类型的单元格
                                double numericValue = cell.getNumericCellValue();
                                System.out.println(numericValue);
                                if(row.getRowNum()>=1){
                                    int intValue = (int) numericValue;
                                    myList.add(intValue);
                                }
                            } else if (cell.getCellType() == CellType.STRING) {
                                // 处理 STRING 类型的单元格
                                stringValue = cell.getStringCellValue();
                                if(row.getRowNum()==1){
                                    youList.add(stringValue);
                                }
                                System.out.println(stringValue);
                            }

                        }
                        if(row.getRowNum()>=1){
                        myMap.put(stringValue,myList);}

                    }
                }
                // 使用entrySet遍历Map
                for (Map.Entry<String, List<Integer>> entry : myMap.entrySet()) {
                    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
                }
                System.out.println(youList);

                // 关闭工作簿和输入流
                workbook.close();
                inputStream.close();

                // 处理成功的消息
                Message successMessage = new Message("Excel file uploaded and processed successfully");
                return ResponseEntity.ok(successMessage);
            } catch (IOException | EncryptedDocumentException e) {
                e.printStackTrace();
                // 处理异常的消息
                Message errorMessage = new Message("Error processing the uploaded Excel file");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
            }
        } else {
            // 空文件的消息
            Message emptyFileMessage = new Message("Uploaded Excel file is empty");
            return ResponseEntity.badRequest().body(emptyFileMessage);
        }
    }

    static class Message {
        private final String message;

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
