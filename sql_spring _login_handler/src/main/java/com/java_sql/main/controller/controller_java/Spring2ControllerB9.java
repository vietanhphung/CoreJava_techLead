package com.java_sql.main.controller.controller_java;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Controller
@RequestMapping("spring2/b9/upload")
public class Spring2ControllerB9 {

    // Display the file upload form
    @GetMapping
    public String showUploadPage() {
        return "upload"; // Refers to upload.html in resources/templates
    }

    // Handle file upload
    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "upload";
        }

        try {
            // Save the uploaded file
            File tempFile = File.createTempFile("uploaded-", "-" + file.getOriginalFilename());
            file.transferTo(tempFile);
            Map<String, Integer> wordCount = wordCount(tempFile);

            // Add success message
            model.addAttribute("message", "File uploaded successfully: " + file.getOriginalFilename());
            model.addAttribute("wordCount", wordCount);
            model.addAttribute("fileSize", file.getSize() + " bytes");

            tempFile.deleteOnExit(); 
        } catch (IOException e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
        }

        return "upload";
    }


    public static Map<String, Integer>  wordCount(File file){
        Map<String, Integer> wordCount = new HashMap<String, Integer>();
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                System.out.println("Read word: " + scanner.next());
                String word = scanner.next();
                if(wordCount.containsKey(word)){
                    wordCount.put(word, wordCount.get(word) + 1);
                }else{
                    wordCount.put(word, 1);
                }
            }
            scanner.close();
        }catch(Exception e){
            e.printStackTrace();  
        }
        return wordCount;
    }
}
