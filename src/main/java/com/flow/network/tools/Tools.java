package com.flow.network.tools;

import cn.hutool.json.JSONObject;
import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import org.springframework.web.multipart.MultipartFile;
import oshi.hardware.CentralProcessor;
import oshi.util.GlobalConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.StringTokenizer;

public class Tools {
    public static String SUCCESS="SUCCESS";
    public static String ERROR="ERROR";

    public static String storeFile(MultipartFile file, String path) throws IllegalStateException, IOException {
        // 获取原始文件名
        String fileName = file.getOriginalFilename();
        // // 获取文件后缀
        // String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
        // //文件的保存重新按照时间戳命名
        // String newFileName = System.currentTimeMillis() + suffixName;
        // 最终保存的文件
        File filePath = new File(path + File.separator + fileName);
        // 判断文件夹是否存在
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        // 保存文件
        file.transferTo(filePath);
        // 如果成功，会返回文件的绝对路径，方便下载
        //logger.info("文件已保存至" +filePath.toString());
        return filePath.toString();
    }
    public static void main(String args[]){
        //recordCpuInfo();
        System.out.println("cpu使用率"+recordCpuInfo());
        System.out.println("内存使用率"+getMemoryInfo());
        System.out.println("磁盘使用率"+getDiskUsed());
    }

    /**
     * 获取硬盘使用量
     */
    public static Double getDiskUsed(){
        File win = new File("/");
        if (win.exists()) {
            long total = win.getTotalSpace();
            long freeSpace = win.getFreeSpace();
            //System.out.println("磁盘总量：" + total/1024/1024/1024);
            //System.out.println("磁盘剩余总量：" + freeSpace/1024/1024/1024);
            //System.out.println("磁盘已用总量：" + (total - freeSpace)/1024/1024/1024);
            return (total - freeSpace)*100.0/total;
        }
        return 10.0;
    }

    public static Double getMemoryInfo(){
        //System.out.println("内存总量：" + OshiUtil.getMemory().getTotal()/1024/1024);
        //System.out.println("已用内存：" + OshiUtil.getMemory().getAvailable()/1024/1024);
        return  100-(OshiUtil.getMemory().getAvailable()*100.0/OshiUtil.getMemory().getTotal());
    }

    public static Double recordCpuInfo(){
        GlobalConfig.set(GlobalConfig.OSHI_OS_WINDOWS_CPU_UTILITY, true);

        CpuInfo cpuInfo = OshiUtil.getCpuInfo();
        CentralProcessor processor = OshiUtil.getProcessor();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cpuTotal",cpuInfo.getToTal());
        jsonObject.put("cpuSys",cpuInfo.getSys());
        jsonObject.put("cpuUser",cpuInfo.getUser());
        jsonObject.put("cpuWait",cpuInfo.getWait());
        jsonObject.put("cpuFree",cpuInfo.getFree());
        jsonObject.put("cpuUsed",cpuInfo.getUsed());
        jsonObject.put("cpuId",processor.getProcessorIdentifier().getProcessorID());
        jsonObject.put("serialNumber",OshiUtil.getSystem().getSerialNumber());
        //System.out.println("cpu使用率"+cpuInfo.getFree());
        return (100-cpuInfo.getFree());
    }

    /**
     * 将字节容量转化为GB
     */
    public static String transformation(long size){
        return (float) size / 1024 / 1024 / 1024 + "GB"+"   ";
    }
    public static String  isWindowsOrLinux() {
        String osName = System.getProperty("os.name");
        String sysName = "";
        if (osName.toLowerCase().startsWith("windows")) {
            sysName = "windows";
        } else if (osName.toLowerCase().startsWith("linux")) {
            sysName = "linux";
        }
        return sysName;
    }
    private static String isWindowsOrLinux = isWindowsOrLinux();
    public static String getNetworkThroughput() {
        String throughput = "";
        if (isWindowsOrLinux.equals("windows")) { // 判断操作系统类型是否为：windows
            throughput = getNetworkThroughputForWindows(); // 查询windows系统的磁盘读写速率
        } else {
            throughput = getNetworkThroughputForLinux(); // 查询linux系统的磁盘读写速率
        }
        return throughput;
    }

    private static final int SLEEP_TIME = 1000 * 60 * 9;
    /**
     * 获取Windows环境下网口的上下行速率
     *
     * @return
     */
    public static String getNetworkThroughputForWindows() {
        Process pro1 = null;
        Process pro2 = null;
        Runtime r = Runtime.getRuntime();
        BufferedReader input = null;
        String rxPercent = "";
        String txPercent = "";
        JSONObject jsonObject = new JSONObject();
        try {
            String command = "netstat -e";
            pro1 = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
            String result1[] = readInLine(input, "windows");
            Thread.sleep(SLEEP_TIME);
            pro2 = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            String result2[] = readInLine(input, "windows");
            rxPercent = formatNumber((Long.parseLong(result2[0]) - Long.parseLong(result1[0]))
                    / (float) (1024 *128* 1024 * (SLEEP_TIME / 1000))); // 上行速率(MB/s)
            txPercent = formatNumber((Long.parseLong(result2[1]) - Long.parseLong(result1[1]))
                    / (float) (1024 *128* 1024 * (SLEEP_TIME / 1000))); // 下行速率(MB/s)
            input.close();
            pro1.destroy();
            pro2.destroy();
        } catch (Exception e) {
          e.printStackTrace();
        }
        jsonObject.put("rxPercent", rxPercent); // 下行速率
        jsonObject.put("txPercent", txPercent); // 上行速率
        return rxPercent;
    }

    /**
     * 格式化浮点数(float 和 double)，保留两位小数
     *
     * @param obj
     * @return
     */
    public static  String formatNumber(Object obj) {
        String result = "";
        if (obj.getClass().getSimpleName().equals("Float")) {
            result = new Formatter().format("%.2f", (float) obj).toString();
        } else if (obj.getClass().getSimpleName().equals("Double")) {
            result = new Formatter().format("%.2f", (double) obj).toString();
        }
        return String.valueOf(obj);
    }
    public static List<String> getPastSevenDays() {
        List<String> dates = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 6; i >=0; i--) {
            String date = currentDate.minusDays(i).format(formatter);
            dates.add(date);
        }

        return dates;
    }
    public static List<String> getPastSevenDaysInChinese() {
        List<String> dates = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM月dd日");

        for (int i = 6; i >=0; i--) {
            String date = currentDate.minusDays(i).format(formatter);
            dates.add(date);
        }

        return dates;
    }
    public static  String[] readInLine(BufferedReader input, String osType) {
        String rxResult = "";
        String txResult = "";
        StringTokenizer tokenStat = null;
        try {
            if (osType.equals("linux")) { // 获取linux环境下的网口上下行速率
                String result[] = input.readLine().split(" ");
                int j = 0, k = 0;
                for (int i = 0; i < result.length; i++) {
                    if (result[i].indexOf("RX") != -1) {
                        j++;
                        if (j == 2) {
                            rxResult = result[i + 1].split(":")[1];
                        }
                    }
                    if (result[i].indexOf("TX") != -1) {
                        k++;
                        if (k == 2) {
                            txResult = result[i + 1].split(":")[1];
                            break;
                        }
                    }
                }

            } else { // 获取windows环境下的网口上下行速率
                input.readLine();
                input.readLine();
                input.readLine();
                input.readLine();
                tokenStat = new StringTokenizer(input.readLine());
                tokenStat.nextToken();
                rxResult = tokenStat.nextToken();
                txResult = tokenStat.nextToken();
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
        String arr[] = { rxResult, txResult };
        return arr;
    }

    /**
     * 获取Linux环境下网口的上下行速率
     *
     * @return
     */
    public static String getNetworkThroughputForLinux() {
        Process pro1 = null;
        Process pro2 = null;
        Runtime r = Runtime.getRuntime();
        BufferedReader input = null;
        String rxPercent = "";
        String txPercent = "";
        JSONObject jsonObject = new JSONObject();
        try {
            String command = "watch ifconfig";
            pro1 = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro1.getInputStream()));

            String result1[] = readInLine(input, "linux");
            Thread.sleep(SLEEP_TIME);
            pro2 = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            String result2[] = readInLine(input, "linux");
            rxPercent = formatNumber((Long.parseLong(result2[0]) - Long.parseLong(result1[0]))
                    / (float) (1024 *128* 1024 * (SLEEP_TIME / 1000))); // 下行速率(MB/s)
            txPercent = formatNumber((Long.parseLong(result2[1]) - Long.parseLong(result1[1]))
                    / (float) (1024*128 * 1024 * (SLEEP_TIME / 1000))); // 上行速率(MB/s)
            input.close();
            pro1.destroy();
            pro2.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("rxPercent", rxPercent); // 下行速率
        jsonObject.put("txPercent", txPercent); // 上行速率
        return rxPercent;
    }

}
