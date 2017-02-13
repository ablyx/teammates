package teammates.ui.automated;

import java.util.ArrayList;
import java.util.List;

import teammates.common.datatransfer.attributes.CommentAttributes;
import teammates.common.datatransfer.attributes.FeedbackResponseCommentAttributes;
import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.datatransfer.attributes.StudentAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.util.Assumption;
import teammates.common.util.Const;

/**
 * Task queue worker action: Puts searchable documents for entities in a specified course
 */
public class SearchableDocumentsProductionWorkerAction extends AutomatedAction {
    
    @Override
    protected String getActionDescription() {
        return null;
    }
    
    @Override
    protected String getActionMessage() {
        return null;
    }
    
    @Override
    public void execute() {
        
        String courseId = getRequestParamValue(Const.ParamsNames.COURSE_ID);
        Assumption.assertNotNull(courseId);
        
        String instructorEmail = getRequestParamValue(Const.ParamsNames.INSTRUCTOR_EMAIL);
        Assumption.assertNotNull(instructorEmail);

        
        List<CommentAttributes> comments = new ArrayList<CommentAttributes>();
        try {
            comments = logic.getCommentsForGiver(courseId, instructorEmail);
            
        } catch (EntityDoesNotExistException e) {
            log.severe("Comments for " + instructorEmail
                    + " in course " + courseId + " does not exist");
        }
        
        List<FeedbackResponseCommentAttributes> frComments =
                logic.getFeedbackResponseCommentForGiver(courseId, instructorEmail);
        List<StudentAttributes> students = logic.getStudentsForCourse(courseId);
        List<InstructorAttributes> instructors = logic.getInstructorsForCourse(courseId);
        
        for (CommentAttributes comment : comments) {
            logic.putDocument(comment);
        }
        
        for (FeedbackResponseCommentAttributes comment : frComments) {
            logic.putDocument(comment);
        }
        for (StudentAttributes student : students) {
            logic.putDocument(student);
        }
        for (InstructorAttributes instructor : instructors) {
            logic.putDocument(instructor);
        }
    }
    
}
