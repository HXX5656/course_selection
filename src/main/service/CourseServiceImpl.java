package main.service;

import main.entity.Section;
import main.entity.Student;

public class CourseServiceImpl implements CourseService {
    public CourseServiceImpl() {
    }

    /*
    * 实现学生选退课函数
    * 注意点：表之间的约束关系
    * 测试例子：学生1选上课A，又退了A，又选上A
    * 预期结果：第一步操作之后选课表有学生1和a，第二步操作后退课表里有，选课表里没有，第三步操作后选课表里有，退课表里没有
    * 上面的课指的是开课实例而不是课程
    * 实现思路：根据逻辑调用选课和退课的dao*/
    @Override
    public int select_or_cancel(Student student, Section section) {
        //TODO:COMPLETE
        return 0;
    }
}
