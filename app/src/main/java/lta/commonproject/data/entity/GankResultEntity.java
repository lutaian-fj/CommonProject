package lta.commonproject.data.entity;

import java.util.List;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/12/26
 */

public class GankResultEntity {
    private String error;
    private List<ResultEntity> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ResultEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultEntity> results) {
        this.results = results;
    }
}
