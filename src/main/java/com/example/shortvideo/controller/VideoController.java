package com.example.shortvideo.controller;

import com.example.shortvideo.dto.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@RestController
@RequestMapping("video")
public class VideoController {
    @PostMapping("videoUpload")
    public Result uploadFiled2(@RequestParam("file")MultipartFile multipartFile, final HttpServletResponse response,
                               final HttpServletRequest request){
        String rootPath = "D:\\data\\ShortVideo\\src\\main\\resources\\static";
        File fileDir = new File(rootPath);
        if(!fileDir.exists() &&!fileDir.isDirectory()){
            fileDir.mkdirs();
        }
        try{
            String path = saveIcon(multipartFile,rootPath);
            return Result.ok(path);
        } catch (Exception e){
            return Result.fail("视频上传失败");
        }
    }

    @GetMapping("delete")
    public Result delete(@RequestParam("name") String name){
        String filePath = "D:\\data\\ShortVideo\\src\\main\\resources\\static\\" + name;

        File file = new File(filePath);
        boolean isDeleted = file.delete();

        if (isDeleted) {
            return Result.ok("文件删除成功");
        } else {
            return Result.fail("文件删除失败");
        }
    }


    private String saveIcon(MultipartFile multipartFile,String rootPath) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String filePath = rootPath + File.separator + fileName;

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new IOException("无法保存视频文件", e);
        }

        return filePath;
    }
}
