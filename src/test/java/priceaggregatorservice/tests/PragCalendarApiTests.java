package priceaggregatorservice.tests;

import helpers.ListInfo;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import priceaggregatorservice.model.Calendar;
import priceaggregatorservice.model.CalendarSpecialDay;
import priceaggregatorservice.model.WorkDays;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static priceaggregatorservice.utils.PragCalendarController.*;

public class PragCalendarApiTests {
    Boolean isManagedExternally=false;
    Integer idForGet=7;
    String name= "Autotest";
    @Description("List calendar")
    @Test
    void successGetListCalendar() {
        ListInfo<Calendar> calendarList= getCalendarList();
        assertThat(calendarList).isNotNull();
    }
    @Description("List calendar by params")
    @Test
    void successGetListCalendarByParams() {
        ListInfo<Calendar> calendarList= getCalendarListByParams("limit","15");
        assertThat(calendarList).isNotNull();
    }
    @Description("Calendar by id")
    @Test
    void successGetCalendarById() {
        Calendar calendar= getCalendarById(idForGet);
        assertThat(calendar).isNotNull();
        assertThat(calendar.getId()).isEqualTo(idForGet);
    }
    @Description("Create calendar")
    @Test
    void successesCreateCalendar() {
        name =name+" 7 дневная неделя "+ java.time.LocalDateTime.now();
        WorkDays workDays = new WorkDays();
        workDays.WorkDaysSevenDays();
        Calendar createCalendar=createCalendar(name,isManagedExternally,null,workDays);
        assertThat(createCalendar).isNotNull();
        assertThat(createCalendar.getName()).isEqualTo(name);
        assertThat(createCalendar.getWorkDays()).isEqualTo(workDays);
    }
    @Description("Edit calendar add special days")
    @Test
    void successesEditCalendarAddSpecialDays() {
        name =name+" 5 дневная неделя "+ java.time.LocalDateTime.now();
        WorkDays workDays = new WorkDays();
        workDays.WorkDaysFiveDays();
        Calendar calendar=createCalendar(name,isManagedExternally,null,workDays);
        // делаем рабочее воскресенье и нерабочим день через неделю
        CalendarSpecialDay csd1=new CalendarSpecialDay(true,LocalDate.now().plusDays(7-LocalDate.now().getDayOfWeek().getValue()).toString(),"Autotest Sunday is workday");
        CalendarSpecialDay csd2=new CalendarSpecialDay(false,LocalDate.now().plusDays(7).toString(),"Autotest dayoff");
        List<CalendarSpecialDay> calendarSpecialDays=new ArrayList<CalendarSpecialDay>();
        calendarSpecialDays.add(csd1);
        calendarSpecialDays.add(csd2);
        Calendar editCalendar=editCalendar(calendar.getId(),name,isManagedExternally,calendarSpecialDays,workDays);
        assertThat(editCalendar).isNotNull();
        assertThat(editCalendar.getId()).isEqualTo(calendar.getId());
        assertThat(editCalendar.getCalendarSpecialDays().size()).isEqualTo(2);
        assertThat(editCalendar.getCalendarSpecialDays().get(0)).isEqualTo(csd1);
        assertThat(editCalendar.getCalendarSpecialDays().get(1)).isEqualTo(csd2);
    }
    @Description("Edit calendar change name")
    @Test
    void successesEditCalendarChangeName(){
        name =name+" 5 дневная неделя "+ java.time.LocalDateTime.now();
        WorkDays workDays = new WorkDays();
        workDays.WorkDaysFiveDays();
        Calendar calendar=createCalendar(name,isManagedExternally,null,workDays);
        String changeName= name+ " edited";
        Calendar editCalendar=editCalendar(calendar.getId(),changeName,isManagedExternally,null,workDays);
        assertThat(editCalendar).isNotNull();
        assertThat(editCalendar.getId()).isEqualTo(calendar.getId());
        assertThat(editCalendar.getName()).isEqualTo(changeName);
    }
    @Description("Edit calendar change work days")
    @Test
    void successesEditCalendarChangeWorkDays() {
        name = name + " 5 дневная неделя " + java.time.LocalDateTime.now();
        WorkDays workDays = new WorkDays();
        workDays.WorkDaysFiveDays();
        Calendar calendar = createCalendar(name, isManagedExternally, null, workDays);
        workDays.WorkDaysSevenDays();
        String changeName = name + " edited to seven work days";
        Calendar editCalendar = editCalendar(calendar.getId(), changeName, isManagedExternally, null, workDays);
        assertThat(editCalendar).isNotNull();
        assertThat(editCalendar.getId()).isEqualTo(calendar.getId());
        assertThat(editCalendar.getWorkDays().getSa()).isEqualTo(true);
        assertThat(editCalendar.getWorkDays().getSu()).isEqualTo(true);
    }
    @Description("Delete calendar")
    @Test
    void successesDeleteCalendar(){
        name = name + " 5 дневная неделя " + java.time.LocalDateTime.now();
        WorkDays workDays = new WorkDays();
        workDays.WorkDaysFiveDays();
        Calendar calendar = createCalendar(name, isManagedExternally, null, workDays);
        Calendar deleteCalendar=deleteCalendar(calendar.getId());
        assertThat(deleteCalendar).isNotNull();
        assertThat(deleteCalendar.getId()).isEqualTo(null);
    }
}
