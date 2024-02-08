package ru.antonio.cognition;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Aspect
public class TrackTeacherActionAspect {
    private final String pathname = "./regiser.txt";

    @AfterReturning(pointcut = "@annotation(ru.antonio.cognition.annotations.TrackTeacherAction)")
    public void registerTeacherAction (JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        if(methodName.equals("getListTeachers")) {
            saveActionToFile("пользователь получил список учителей");
        } else {
            saveActionToFile(methodName);
        }
        return;
    }

    private void saveActionToFile (String message) {
        File file = new File(pathname);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)){
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd - hh:mm:ss");
            String total = date.format(formatter) + ": " + message + "\n";
            fileOutputStream.write(total.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
