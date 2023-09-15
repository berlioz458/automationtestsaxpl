package priceaggregatorservice.tests;

import helpers.ListInfo;
import helpers.Ref;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import priceaggregatorservice.model.WorkSchedule;
import priceaggregatorservice.model.WorkScheduleDatePatternItem;
import priceaggregatorservice.model.WorkScheduleDayPatternItem;
import priceaggregatorservice.model.WorkScheduleRegularPatternItem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static priceaggregatorservice.utils.PragWorkScheduleController.*;
import static priceaggregatorservice.utils.PragWorkScheduleController.createWorkSchedule;

public class PragWorkScheduleApiTests {

    Boolean isManagedExternally=false;
    Integer idForGet=10005;
    String name= "Autotest";
    String delay="PT0S";
    String timeZone="Asia/Yekaterinburg";
    List<WorkScheduleRegularPatternItem> workScheduleRegularPatternItems= new ArrayList<>();
    List<WorkScheduleDatePatternItem> workScheduleDatePatternItems=new ArrayList<>();
    List<WorkScheduleDayPatternItem> workScheduleDayPatternItems=new ArrayList<>();
    Ref ownerAgent= new Ref("Agent",10008);
    Ref calendar = new Ref("Calendar",7);

    @Description("List WorkSchedule")
    @Test
    void successGetListWorkSchedule() {
        ListInfo<WorkSchedule> workScheduleList= getWorkScheduleList();
        assertThat(workScheduleList).isNotNull();
    }
    @Description("List WorkSchedule by params")
    @Test
    void successGetListWorkScheduleByParams() {
        ListInfo<WorkSchedule> workScheduleList= getWorkScheduleListByParams("limit","15");
        assertThat(workScheduleList).isNotNull();
    }
    @Description("WorkSchedule by id")
    @Test
    void successGetWorkScheduleById() {
        WorkSchedule workSchedule= getWorkScheduleById(idForGet);
        assertThat(workSchedule).isNotNull();
        assertThat(workSchedule.getId()).isEqualTo(idForGet);
    }
    @Description("Create WorkSchedule")
    @Test
    void successesCreateWorkSchedule() {
        name =name+" "+ java.time.LocalDateTime.now();
        WorkScheduleRegularPatternItem workScheduleRegularPatternItem = new WorkScheduleRegularPatternItem("09:00:00","PT10H");
        workScheduleRegularPatternItems.add(workScheduleRegularPatternItem);
        WorkSchedule createWorkSchedule=createWorkSchedule(name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        assertThat(createWorkSchedule).isNotNull();
        assertThat(createWorkSchedule.getName()).isEqualTo(name);
        assertThat(createWorkSchedule.getWorkScheduleRegularPatternItems().get(0)).isEqualTo(workScheduleRegularPatternItem);
        assertThat(createWorkSchedule.getWorkScheduleDayPatternItems().size()).isEqualTo(0);
        assertThat(createWorkSchedule.getWorkScheduleDatePatternItems().size()).isEqualTo(0);
    }
    @Description("Edit WorkSchedule add day pattern")
    @Test
    void successesEditWorkScheduleAddDayPattern() {
        name =name+" "+ java.time.LocalDateTime.now();
        WorkScheduleRegularPatternItem workScheduleRegularPatternItem = new WorkScheduleRegularPatternItem("09:00:00","PT10H");
        workScheduleRegularPatternItems.add(workScheduleRegularPatternItem);
        WorkSchedule workSchedule=createWorkSchedule(name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        WorkScheduleDayPatternItem workScheduleDayPatternItem=new WorkScheduleDayPatternItem(3,false,"09:00:00","PT0S");
        WorkScheduleDayPatternItem workScheduleDayPatternItem2=new WorkScheduleDayPatternItem(6,true,"09:00:00","PT5H");
        workScheduleDayPatternItems.add(workScheduleDayPatternItem);
        workScheduleDayPatternItems.add(workScheduleDayPatternItem2);
        WorkSchedule editWorkSchedule=editWorkSchedule(workSchedule.getId(),name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,workScheduleDayPatternItems,null);
        assertThat(editWorkSchedule).isNotNull();
        assertThat(editWorkSchedule.getId()).isEqualTo(workSchedule.getId());
        assertThat(editWorkSchedule.getWorkScheduleDayPatternItems().size()).isEqualTo(2);
        assertThat(editWorkSchedule.getWorkScheduleDayPatternItems().get(0)).isEqualTo(workScheduleDayPatternItem);
        assertThat(editWorkSchedule.getWorkScheduleDayPatternItems().get(1)).isEqualTo(workScheduleDayPatternItem2);
        assertThat(editWorkSchedule.getWorkScheduleDatePatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleRegularPatternItems().get(0)).isEqualTo(workScheduleRegularPatternItem);
    }
    @Description("Edit WorkSchedule add date pattern")
    @Test
    void successesEditWorkScheduleAddDatePattern() {
        name =name+" "+ java.time.LocalDateTime.now();
        WorkScheduleRegularPatternItem workScheduleRegularPatternItem = new WorkScheduleRegularPatternItem("09:00:00","PT10H");
        workScheduleRegularPatternItems.add(workScheduleRegularPatternItem);
        WorkSchedule workSchedule=createWorkSchedule(name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        WorkScheduleDatePatternItem workScheduleDatePatternItem=new WorkScheduleDatePatternItem(LocalDate.now().toString(),false,"09:00:00","PT0S");
        WorkScheduleDatePatternItem workScheduleDatePatternItem2=new WorkScheduleDatePatternItem(LocalDate.now().plusDays(7-LocalDate.now().getDayOfWeek().getValue()).toString(),true,"09:00:00","PT5H");
        workScheduleDatePatternItems.add(workScheduleDatePatternItem);
        workScheduleDatePatternItems.add(workScheduleDatePatternItem2);
        WorkSchedule editWorkSchedule=editWorkSchedule(workSchedule.getId(),name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,workScheduleDatePatternItems);
        assertThat(editWorkSchedule).isNotNull();
        assertThat(editWorkSchedule.getId()).isEqualTo(workSchedule.getId());
        assertThat(editWorkSchedule.getWorkScheduleDatePatternItems().size()).isEqualTo(2);
        assertThat(editWorkSchedule.getWorkScheduleDatePatternItems().get(0)).isEqualTo(workScheduleDatePatternItem);
        assertThat(editWorkSchedule.getWorkScheduleDatePatternItems().get(1)).isEqualTo(workScheduleDatePatternItem2);
        assertThat(editWorkSchedule.getWorkScheduleDayPatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleRegularPatternItems().get(0)).isEqualTo(workScheduleRegularPatternItem);
    }
    @Description("Edit WorkSchedule change name")
    @Test
    void successesEditWorkScheduleChangeName(){
        name =name+" "+ java.time.LocalDateTime.now();
        WorkScheduleRegularPatternItem workScheduleRegularPatternItem = new WorkScheduleRegularPatternItem("09:00:00","PT10H");
        workScheduleRegularPatternItems.add(workScheduleRegularPatternItem);
        WorkSchedule workSchedule=createWorkSchedule(name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        String changeName= name+ " edited";
        WorkSchedule editWorkSchedule=editWorkSchedule(workSchedule.getId(),changeName,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        assertThat(editWorkSchedule).isNotNull();
        assertThat(editWorkSchedule.getId()).isEqualTo(workSchedule.getId());
        assertThat(editWorkSchedule.getName()).isEqualTo(changeName);
        assertThat(editWorkSchedule.getWorkScheduleDayPatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleDatePatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleRegularPatternItems().get(0)).isEqualTo(workScheduleRegularPatternItem);
    }
    @Description("Edit WorkSchedule change timeZone")
    @Test
    void successesEditWorkScheduleChangeTimeZone(){
        name =name+" "+ java.time.LocalDateTime.now();
        WorkScheduleRegularPatternItem workScheduleRegularPatternItem = new WorkScheduleRegularPatternItem("09:00:00","PT10H");
        workScheduleRegularPatternItems.add(workScheduleRegularPatternItem);
        WorkSchedule workSchedule=createWorkSchedule(name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        String changeName= name+ " edited timeZone";
        timeZone="Asia/Omsk";
        WorkSchedule editWorkSchedule=editWorkSchedule(workSchedule.getId(),changeName,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        assertThat(editWorkSchedule).isNotNull();
        assertThat(editWorkSchedule.getId()).isEqualTo(workSchedule.getId());
        assertThat(editWorkSchedule.getTimeZone()).isEqualTo(timeZone);
        assertThat(editWorkSchedule.getWorkScheduleDayPatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleDatePatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleRegularPatternItems().get(0)).isEqualTo(workScheduleRegularPatternItem);
    }
    @Description("Edit WorkSchedule change delay")
    @Test
    void successesEditWorkScheduleChangeDelay(){
        name =name+" "+ java.time.LocalDateTime.now();
        WorkScheduleRegularPatternItem workScheduleRegularPatternItem = new WorkScheduleRegularPatternItem("09:00:00","PT10H");
        workScheduleRegularPatternItems.add(workScheduleRegularPatternItem);
        WorkSchedule workSchedule=createWorkSchedule(name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        String changeName= name+ " edited timeZone";
        delay="PT1H";
        WorkSchedule editWorkSchedule=editWorkSchedule(workSchedule.getId(),changeName,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        assertThat(editWorkSchedule).isNotNull();
        assertThat(editWorkSchedule.getId()).isEqualTo(workSchedule.getId());
        assertThat(editWorkSchedule.getDelay()).isEqualTo(delay);
        assertThat(editWorkSchedule.getWorkScheduleDayPatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleDatePatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleRegularPatternItems().get(0)).isEqualTo(workScheduleRegularPatternItem);
    }
    @Description("Edit WorkSchedule change qwner agent")
    @Test
    void successesEditWorkScheduleChangeOwnerAgent(){
        name =name+" "+ java.time.LocalDateTime.now();
        WorkScheduleRegularPatternItem workScheduleRegularPatternItem = new WorkScheduleRegularPatternItem("09:00:00","PT10H");
        workScheduleRegularPatternItems.add(workScheduleRegularPatternItem);
        WorkSchedule workSchedule=createWorkSchedule(name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        String changeName= name+ " edited timeZone";
        ownerAgent.setId(10100);
        WorkSchedule editWorkSchedule=editWorkSchedule(workSchedule.getId(),changeName,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        assertThat(editWorkSchedule).isNotNull();
        assertThat(editWorkSchedule.getId()).isEqualTo(workSchedule.getId());
        assertThat(editWorkSchedule.getOwnerAgent().getId()).isEqualTo(ownerAgent.getId());
        assertThat(editWorkSchedule.getWorkScheduleDayPatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleDatePatternItems().size()).isEqualTo(0);
        assertThat(editWorkSchedule.getWorkScheduleRegularPatternItems().get(0)).isEqualTo(workScheduleRegularPatternItem);
    }

    @Description("Delete WorkSchedule")
    @Test
    void successesDeleteWorkSchedule(){
        name =name+" "+ java.time.LocalDateTime.now();
        WorkScheduleRegularPatternItem workScheduleRegularPatternItem = new WorkScheduleRegularPatternItem("09:00:00","PT10H");
        workScheduleRegularPatternItems.add(workScheduleRegularPatternItem);
        WorkSchedule workSchedule=createWorkSchedule(name,isManagedExternally,delay,timeZone,ownerAgent,calendar,workScheduleRegularPatternItems,null,null);
        WorkSchedule deleteWorkSchedule=deleteWorkSchedule(workSchedule.getId());
        assertThat(deleteWorkSchedule).isNotNull();
        assertThat(deleteWorkSchedule.getId()).isEqualTo(null);
    }
}
