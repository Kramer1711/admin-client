package com.kit.app1.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
* @author qiukai
* @date 2018年8月17日 上午9:45:43
*/
@RestController
@RequestMapping("app")
public class Controller {
    
    private static Logger logger = LoggerFactory.getLogger(Controller.class);
    
    @GetMapping("hello")
    public String hello(HttpServletRequest request)  {
        
        logger.info("Hello,logger!");
        
        return "Hello,Spring cloud!";
    }
    
    private static String DIR = "/data";
    
    @GetMapping("put")
    public String put(HttpServletRequest request) {
        String filename = (String)request.getParameter("filename");
        System.out.println(filename);
        String filePath = DIR+"/"+filename;
        File file = new File(filePath);
        if(file.exists()) {
            System.out.println("创建单个文件" + filePath + "失败，目标文件已存在！");
            return "error";
        }
        if (filePath.endsWith(File.separator)) {
            System.out.println("创建单个文件" + filePath + "失败，目标文件不能为目录！");
            return "error";
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return "error";
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + filePath + "成功！");
                return "success";
            } else {
                System.out.println("创建单个文件" + filePath + "失败！");
                return "error";
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + filePath + "失败！" + e.getMessage());
            return "error";
        }
    }

}
