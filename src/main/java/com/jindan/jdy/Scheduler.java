package com.jindan.jdy;

import com.jindan.jdy.common.dto.JdyClassroomDto;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblemsReult;
import com.jindan.jdy.common.pojo.JdyClassroom;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodSafetyProblemsReultService;
import com.jindan.jdy.service.foodsafety.JdyAppletFootSafetyPersonService;
import com.jindan.jdy.service.sys.JdyClassroomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class Scheduler {

        @Autowired
        JdyAppletFoodSafetyProblemsReultService jdyAppletFoodSafetyProblemsReultService;

        @Autowired
        JdyAppletFootSafetyPersonService jdyAppletFootSafetyPersonService;

        @Autowired
        JdyClassroomService jdyClassroomService;

        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        @Scheduled(cron = "0 05 03 ? * *")
        public void testTasks1(){
                log.info("定时任务启动 0 05 03 ? * *");
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
                String str = sdf.format(d);
                JdyClassroomDto jdyClassroomDto =new JdyClassroomDto();
                jdyClassroomDto.setEndtime(str+"点");
                List<JdyClassroom> seletelist = jdyClassroomService.seleteAlllist(jdyClassroomDto);
                List<String> lis =new ArrayList<>();
                for (int i =0;i< seletelist.size();i++){
                        lis.add(seletelist.get(i).getId());
                }
                if(lis.size() > 0){
                        boolean b = jdyClassroomService.removeByIds(lis);
                }
        }



        @Scheduled(cron = "0 05 05 ? * *")
        public void testTas1() {
            log.info("定时任务启动 0 05 05 ? * *");

            Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
                String str = sdf.format(d);
                List<JdyAppletFoodSafetyProblemsReult> seletelist =  jdyAppletFoodSafetyProblemsReultService.seleteProList();
        }


    @Scheduled(cron = "0 05 03 ? * *")
    public void testAppletFlldSalety() {
        log.info("定时任务启动 0 05 03 ? * *");

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        String str = sdf.format(d);
        JdyClassroomDto jdyClassroomDto =new JdyClassroomDto();
        jdyClassroomDto.setEndtime(str+"点");
        List<JdyClassroom> seletelist = jdyClassroomService.seleteAlllist(jdyClassroomDto);
        List<String> lis =new ArrayList<>();
        for (int i =0;i< seletelist.size();i++){
            lis.add(seletelist.get(i).getId());
        }
        if(lis.size() > 0){
            boolean b = jdyClassroomService.removeByIds(lis);
        }
    }

}