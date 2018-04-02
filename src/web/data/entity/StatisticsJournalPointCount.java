package web.data.entity;

public class StatisticsJournalPointCount {
    private Long id;

    private String date;

    private String jpCount;

    private Long time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getJpCount() {
        return jpCount;
    }

    public void setJpCount(String jpCount) {
        this.jpCount = jpCount == null ? null : jpCount.trim();
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}