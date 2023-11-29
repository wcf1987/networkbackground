package com.flow.network.tools;

import cn.hutool.json.JSONObject;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
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
    /**
     * 获取内存使用情况
     */
    public static Double getCPU() throws IOException {

        Double cpuUsage = 0.0;
        try {
            OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            //String osJson = JSON.toJSONString(operatingSystemMXBean);
            Double a=operatingSystemMXBean.getSystemCpuLoad();
            System.out.println("cpu:"+a);

            Double value = (Double) a;

            // value为-1表示无法获取CPU使用情况
            if (value == -1) {
                return 0.0;
            }

            cpuUsage = ((int) (value * 1000) / 10.0);
            System.out.println(cpuUsage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpuUsage;
    }


    public static float getMemory() throws IOException {
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 获取内存总容量
        long totalMemorySize = mem.getTotalPhysicalMemorySize();
        // 获取可用内存容量(剩余物理内存）
        long freeMemorySize = mem.getFreePhysicalMemorySize();
        // 空闲的交换容量
        long freeSwapSpaceSize = mem.getFreeSwapSpaceSize();

        float usedRAM = (float)(((totalMemorySize - freeMemorySize) * 1.0 / totalMemorySize) * 100);

        //System.out.println("物理内存总容量totalMemorySize：" + transformation(totalMemorySize) );
        //System.out.println("剩余物理内存可用容量freeMemorySize：" + transformation(freeMemorySize));
       // System.out.println("usedRAM：" + usedRAM);
        //System.out.println("空闲的交换容量:" + transformation(freeSwapSpaceSize));

        Runtime runtime = Runtime.getRuntime();
        // java虚拟机中的内存总量，可用内存空间 单位为byte，默认为系统的1/64
        long totalMemory = runtime.totalMemory();
        // java虚拟机试图使用的最大内存量 最大可用内存空间 单位byte，默认为系统的1/4
        long maxMemory = runtime.maxMemory();
        // java 虚拟机中的空闲内存量 空闲空间 单位byte， 默认为系统的1/4
        long freeMemory = runtime.freeMemory();
        float usedRAMJava = (float)(((totalMemory - freeMemory) * 1.0 / totalMemory) * 100);
        //System.out.println("java虚拟机中的内存总量:" + totalMemory / 1024 / 1024 + "MB" );
        //System.out.println("java虚拟机试图使用的最大内存量:" + maxMemory / 1024 / 1024 + "MB" );
        //System.out.println("java虚拟机中的空闲内存量:" + freeMemory / 1024 / 1024 + "MB" );
        //System.out.println("java虚拟机中的剩余内存占总量:" + usedRAMJava + "%" );
        return usedRAMJava;

    }
    public static Double getDisk() throws IOException {
        DecimalFormat df = new DecimalFormat("#0.00");
        File[] disks = File.listRoots();
        for (File file : disks) {
            // 获取盘符
            System.out.print(file.getCanonicalPath() + "   ");
            // 获取总容量
            long totalSpace = file.getTotalSpace();
            // 获取剩余容量
            long usableSpace = file.getUsableSpace();
            // 获取已经使用的容量
            long freeSpace = totalSpace - usableSpace;
            // 获取使用率
            float useRate = (float)((freeSpace * 1.0 / totalSpace) * 100);
            System.out.print("总容量： " + transformation(totalSpace));
            System.out.print("已经使用： " + transformation(freeSpace));
            System.out.print("剩余容量： " + transformation(usableSpace));
            System.out.println("使用率： " + Double.parseDouble(df.format(useRate)) + "%   ");
            return Double.parseDouble(df.format(useRate));
        }
        return 0.0;
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
