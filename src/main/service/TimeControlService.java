package main.service;

public class TimeControlService {
    private static TimeControlService timeControlService=null;
    private  boolean canSelect;
    private  boolean canDelete;
    private   int year;
    private  int semester;

    private TimeControlService() {

    }
    public static TimeControlService getInstance() {
        if(timeControlService==null) {
            timeControlService=new TimeControlService();
            (timeControlService).setCanDelete(false);
            (timeControlService).setCanSelect(false);
            (timeControlService).setYear(2019);
            (timeControlService).setSemester(1);
        }
        return timeControlService;
    }

    public boolean isCanSelect() {
        return canSelect;
    }

    public void setCanSelect(boolean canSelect) {
        this.canSelect = canSelect;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
