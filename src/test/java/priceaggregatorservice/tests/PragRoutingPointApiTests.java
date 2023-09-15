package priceaggregatorservice.tests;

import helpers.ListInfo;
import helpers.Ref;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import priceaggregatorservice.model.RoutingPoint;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static priceaggregatorservice.utils.PragRoutingPointController.*;

public class PragRoutingPointApiTests {
    Boolean isManagedExternally=false;
    Integer idForGet=10351;
    String name= "Autotest";

    String longitude=null;
    String latitude=null;
    Boolean isTransferPoint=true;
    Ref ownerAgent= new Ref("Agent",10411);
    Ref inWorkSchedule= new Ref("WorkSchedule",10559);
    Ref outWorkSchedule= new Ref("WorkSchedule",10559);
    @Description("List Routing Point")
    @Test
    void successGetListRoutingPoint() {
        ListInfo<RoutingPoint> RoutingPointList= getRoutingPointList();
        assertThat(RoutingPointList).isNotNull();
    }
    @Description("List Routing Point by params")
    @Test
    void successGetListRoutingPointByParams() {
        ListInfo<RoutingPoint> RoutingPointList= getRoutingPointListByParams("limit","15");
        assertThat(RoutingPointList).isNotNull();
    }
    @Description("Routing Point by id")
    @Test
    void successGetRoutingPointById() {
        RoutingPoint RoutingPoint= getRoutingPointById(idForGet);
        assertThat(RoutingPoint).isNotNull();
        assertThat(RoutingPoint.getId()).isEqualTo(idForGet);
    }
    @Description("Create RoutingPoint")
    @Test
    void successesCreateRoutingPoint() {
        name =name+" "+ java.time.LocalDateTime.now();

        RoutingPoint createRoutingPoint=createRoutingPoint(name,isManagedExternally,longitude,latitude,isTransferPoint,ownerAgent,inWorkSchedule,outWorkSchedule);
        assertThat(createRoutingPoint).isNotNull();
        assertThat(createRoutingPoint.getName()).isEqualTo(name);

    }
    /*@Description("Edit RoutingPoint add special days")
    @Test
    void successesEditRoutingPointAddSpecialDays() {
        name =name+" 5 дневная неделя "+ java.time.LocalDateTime.now();
        WorkDays workDays = new WorkDays();
        workDays.WorkDaysFiveDays();
        RoutingPoint RoutingPoint=createRoutingPoint(name,isManagedExternally,null,workDays);
        // делаем рабочее воскресенье и нерабочим день через неделю
        RoutingPointSpecialDay csd1=new RoutingPointSpecialDay(true, LocalDate.now().plusDays(7-LocalDate.now().getDayOfWeek().getValue()).toString(),"Autotest Sunday is workday");
        RoutingPointSpecialDay csd2=new RoutingPointSpecialDay(false,LocalDate.now().plusDays(7).toString(),"Autotest dayoff");
        List<RoutingPointSpecialDay> RoutingPointSpecialDays=new ArrayList<RoutingPointSpecialDay>();
        RoutingPointSpecialDays.add(csd1);
        RoutingPointSpecialDays.add(csd2);
        RoutingPoint editRoutingPoint=editRoutingPoint(RoutingPoint.getId(),name,isManagedExternally,RoutingPointSpecialDays,workDays);
        assertThat(editRoutingPoint).isNotNull();
        assertThat(editRoutingPoint.getId()).isEqualTo(RoutingPoint.getId());
        assertThat(editRoutingPoint.getRoutingPointSpecialDays().size()).isEqualTo(2);
        assertThat(editRoutingPoint.getRoutingPointSpecialDays().get(0)).isEqualTo(csd1);
        assertThat(editRoutingPoint.getRoutingPointSpecialDays().get(1)).isEqualTo(csd2);
    }
    @Description("Edit RoutingPoint change name")
    @Test
    void successesEditRoutingPointChangeName(){
        name =name+" 5 дневная неделя "+ java.time.LocalDateTime.now();
        WorkDays workDays = new WorkDays();
        workDays.WorkDaysFiveDays();
        RoutingPoint RoutingPoint=createRoutingPoint(name,isManagedExternally,null,workDays);
        String changeName= name+ " edited";
        RoutingPoint editRoutingPoint=editRoutingPoint(RoutingPoint.getId(),changeName,isManagedExternally,null,workDays);
        assertThat(editRoutingPoint).isNotNull();
        assertThat(editRoutingPoint.getId()).isEqualTo(RoutingPoint.getId());
        assertThat(editRoutingPoint.getName()).isEqualTo(changeName);
    }
    @Description("Edit RoutingPoint change work days")
    @Test
    void successesEditRoutingPointChangeWorkDays() {
        name = name + " 5 дневная неделя " + java.time.LocalDateTime.now();
        WorkDays workDays = new WorkDays();
        workDays.WorkDaysFiveDays();
        RoutingPoint RoutingPoint = createRoutingPoint(name, isManagedExternally, null, workDays);
        workDays.WorkDaysSevenDays();
        String changeName = name + " edited to seven work days";
        RoutingPoint editRoutingPoint = editRoutingPoint(RoutingPoint.getId(), changeName, isManagedExternally, null, workDays);
        assertThat(editRoutingPoint).isNotNull();
        assertThat(editRoutingPoint.getId()).isEqualTo(RoutingPoint.getId());
        assertThat(editRoutingPoint.getWorkDays().getSa()).isEqualTo(true);
        assertThat(editRoutingPoint.getWorkDays().getSu()).isEqualTo(true);
    }
    @Description("Delete RoutingPoint")
    @Test
    void successesDeleteRoutingPoint(){
        name = name + " 5 дневная неделя " + java.time.LocalDateTime.now();
        WorkDays workDays = new WorkDays();
        workDays.WorkDaysFiveDays();
        RoutingPoint RoutingPoint = createRoutingPoint(name, isManagedExternally, null, workDays);
        RoutingPoint deleteRoutingPoint=deleteRoutingPoint(RoutingPoint.getId());
        assertThat(deleteRoutingPoint).isNotNull();
        assertThat(deleteRoutingPoint.getId()).isEqualTo(null);
    }*/
}
