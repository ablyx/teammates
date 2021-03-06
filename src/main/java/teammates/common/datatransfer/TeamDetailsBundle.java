package teammates.common.datatransfer;

import teammates.common.datatransfer.attributes.StudentAttributes;

import java.util.ArrayList;
import java.util.List;

/** 
 * Represents details of students in a team.
 * <br> Contains:
 * <br> * The team name .
 * <br> * {@link StudentAttributes} objects for all students in the team.
 */
public class TeamDetailsBundle {
    
    public String name;
    public List<StudentAttributes> students = new ArrayList<StudentAttributes>();

}
