package com.tingyu.antimicrobial.monitor.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author essionshy
 * @Create 2021/7/16 18:33
 * @Version kjyjcxt
 */
@Slf4j
public class ExcelReader {


    private Map<String, Object> map = new HashMap<>();
    private MultipartFile file;
    private List<Object> rowList = new ArrayList<>();
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public ExcelReader(MultipartFile file) {
        this.file = file;
    }
    public Map<String, Object> getMap() {
        return map;
    }
    public synchronized void read() throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
      /*  DocumentSummaryInformation information = workbook.getDocumentSummaryInformation();
        CustomProperties customProperties = information.getCustomProperties();
        String templateCode = customProperties.get("templateCode") == null ? "" : (String) customProperties.get("templateCode");// 获取模板编码
        map.put("templateCode", templateCode);*/
        HSSFSheet sheet = workbook.getSheetAt(0);
        this.doRead(sheet);


    }

    private synchronized void doRead(HSSFSheet sheet) throws InterruptedException {
        HSSFRow row = sheet.getRow(0);
        readRow(row);
       // CountDownLatch countDownLatch = new CountDownLatch(sheet.getPhysicalNumberOfRows() - 1);
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            HSSFRow dataRow = sheet.getRow(i); // 数据行
            int finalI = i;
            readRow(dataRow);
         /*   threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "执行读取数据行" + String.valueOf(finalI));
               // countDownLatch.countDown();

            });*/

        }
        map.put("rowList", rowList);
       // countDownLatch.await();
        log.info("主线程执行完成...");

    }

    /**
     * 构建表头
     *
     * @param row
     */
    private synchronized void readRow(HSSFRow row) {
        Map<String, Object> headers = new HashMap<>();
        log.info("{getRowNum}", row.getRowNum());
        log.info("{getPhysicalNumberOfCells}", row.getPhysicalNumberOfCells());
        log.info("{getLastCellNum}", row.getLastCellNum());

        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = row.getCell(i);

        }

    }

}
