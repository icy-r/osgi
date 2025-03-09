
package coursecontent_producer;

import java.util.HashMap;
import java.util.Map;

public class CourseContentServiceImpl implements CourseContentService {
	private Map<String, String> contentMap = new HashMap<>();

	public void addContent(String courseId, String content) {
		contentMap.put(courseId, content);
	}

	public void updateContent(String courseId, String content) {
		contentMap.put(courseId, content);
	}

	public void deleteContent(String courseId) {
		contentMap.remove(courseId);
	}

	public String searchLecturesByCourseId(String courseId) {
		return contentMap.get(courseId);
	}
}
