package lta.commonproject.data.entity;

import java.util.List;

/**
 * @author: Administrator
 * @ClassName:
 * @Description:
 * @date: 2016/8/24
 */
public class ObjectToJsonEntity {
    private String title;
    private String answer;
    private List<TestEntity> testEntities;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<TestEntity> getTestEntities() {
        return testEntities;
    }

    public void setTestEntities(List<TestEntity> testEntities) {
        this.testEntities = testEntities;
    }
}
