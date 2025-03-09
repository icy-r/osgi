package coursecontent_producer;

public interface CourseContentService {
    void addContent(String courseId, String content);
    void updateContent(String courseId, String content);
    void deleteContent(String courseId);
    String searchLecturesByCourseId(String courseId);
}